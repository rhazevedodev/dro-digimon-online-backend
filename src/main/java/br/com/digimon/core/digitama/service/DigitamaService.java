package br.com.digimon.core.digitama.service;

import br.com.digimon.core.digimon.domain.*;
import br.com.digimon.core.digimon.enumerator.DigimonPersonality;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.digimon.service.DigimonSpeciesService;
import br.com.digimon.core.digitama.config.DigitamaProperties;
import br.com.digimon.core.digitama.domain.Digitama;
import br.com.digimon.core.digitama.dto.ChocarDigitamaRequestDTO;
import br.com.digimon.core.digitama.dto.ChocarDigitamaResponseDTO;
import br.com.digimon.core.estado.domain.EstadoJogo;
import br.com.digimon.core.estado.service.EstadoJogoService;
import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DigitamaService {

    private final DigitamaProperties properties;
    private final EstadoJogoService estadoJogoService;
    private final DigimonSpeciesService digimonSpeciesService;
    private final JogadorRepository jogadorRepository;
    private final DigimonRepository digimonRepository;

    public List<Digitama> listarDigitamas() {
        return properties.getList().stream()
                .map(d -> new Digitama(
                        d.getId(),
                        d.getNome(),
                        d.getImagem(),
                        d.getPossiveis()
                ))
                .collect(Collectors.toList());
    }

    public Digitama buscarPorId(Long id) {
        return listarDigitamas().stream()
                .filter(d -> d.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Digitama não encontrada"));
    }

    public ChocarDigitamaResponseDTO chocarDigitama(ChocarDigitamaRequestDTO dto, String username) {
        // Busca jogador logado
        Jogador jogador = jogadorRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        // 🔹 Busca estado atual do jogo
        EstadoJogo estado = estadoJogoService.getOuCriarEstado(jogador);

        // 🔒 Valida se o jogador tem uma Digitama selecionada
        if (!estado.getDigitamaSelecionada()) {
            throw new IllegalStateException("Nenhuma Digitama foi selecionada para chocar.");
        }

        // 🔒 Impede chocar mais de uma vez
        if (estado.getDigitamaChocada()) {
            throw new IllegalStateException("O jogador já possui uma Digitama chocada.");
        }

        // Busca digitama escolhida
        var digitama = properties.getList().stream()
                .filter(d -> d.getId().equals(dto.getDigitamaId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Digitama inválida"));

        // Sorteia um Digimon possível
        var possiveis = digitama.getPossiveis();
        var sorteado = possiveis.get(new Random().nextInt(possiveis.size()));
        DigimonSpecies especie = digimonSpeciesService.findByName(sorteado);

        // Cria novo Digimon
        Digimon novo = new Digimon();
        novo.setNome(sorteado);
        novo.setNivel(1);
        novo.setEstagio(especie.getEstagio());
        novo.setJogador(jogador);
        novo.setSpecies(especie);

        DigimonAtributosBasicos atributosBasicos = new DigimonAtributosBasicos();
        atributosBasicos.setDigimon(novo);
        // Atributos iniciais herdados da espécie
        atributosBasicos.setHp(especie.getBaseHp());
        atributosBasicos.setAtk(especie.getBaseAtk());
        atributosBasicos.setDef(especie.getBaseDef());
        atributosBasicos.setIntValue(especie.getBaseInt());
        atributosBasicos.setSpd(especie.getBaseSpd());
        novo.setAtributosBasicos(atributosBasicos);

        novo.setEnergia(50); // pode ser fixo para começar

        DigimonAtributosExtras atributosExtras = new DigimonAtributosExtras();
        atributosExtras.setDigimon(novo);
        atributosExtras.setCritRate(0);
        atributosExtras.setCritDamage(0);
        atributosExtras.setAccuracy(0);
        atributosExtras.setEvade(0);
        atributosExtras.setBond(0);
        novo.setAtributosExtras(atributosExtras);

        // 🔹 Gera IVs aleatórios
        DigimonIV novoIV = new DigimonIV();
        novoIV.setDigimon(novo);
        novoIV.gerarIVs();

        // 🔹 Define personalidade
        novo.setPersonality(DigimonPersonality.values()[new Random().nextInt(DigimonPersonality.values().length)]);

        digimonRepository.save(novo);

        // 🔹 Atualiza o primeiro acesso
        if (!jogador.isPrimeiroAcesso()) {
            jogador.setPrimeiroAcesso(true);
            jogadorRepository.save(jogador);
        }

        estadoJogoService.marcarDigitamaChocada(jogador);
        estadoJogoService.finalizarSelecaoDigitamaParaSlot(jogador);

        return ChocarDigitamaResponseDTO.builder()
                .digimonId(especie.getId())
                .nome(sorteado)
                .imagem(buscarImagemPorNome(sorteado))
                .tipo(especie.getTipo().toString())
                .level(1)
                .elemento(especie.getElemento().toString())
                .personalidade(novo.getPersonality().toString())
                .ivs(new java.util.HashMap<>() {{
                    put("hp", novoIV.getIvHp());
                    put("atk", novoIV.getIvAtk());
                    put("def", novoIV.getIvDef());
                    put("int", novoIV.getIvInt());
                    put("spd", novoIV.getIvSpd());
                    put("total", novoIV.getIvTotal());
                }})
                .build();
    }

    private String buscarImagemPorNome(String nome) {
        return "../images/digimons/baby1/" + nome.toLowerCase() + ".jpg";
    }
}