package br.com.digimon.core.digitama.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digitama.config.DigitamaProperties;
import br.com.digimon.core.digitama.domain.Digitama;
import br.com.digimon.core.digitama.dto.ChocarDigitamaRequestDTO;
import br.com.digimon.core.digitama.dto.ChocarDigitamaResponseDTO;
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
    private final JogadorRepository jogadorRepository;
    private final EstadoJogoService estadoJogoService;

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

        // Busca digitama escolhida
        var digitama = properties.getList().stream()
                .filter(d -> d.getId().equals(dto.getDigitamaId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Digitama inválida"));

        // Sorteia um Digimon possível
        var possiveis = digitama.getPossiveis();
        var sorteado = possiveis.get(new Random().nextInt(possiveis.size()));

        // Cria novo Digimon
//        Digimon novo = new Digimon();
//        novo.setNome(sorteado);
//        novo.setLevel(1);
//        novo.setTipo("Baby I");
//        novo.setJogador(jogador);
//        novo.setAtivo(true);
//        novo.setImagem(buscarImagemPorNome(sorteado)); // opcional, se quiser associar imagem automática
//        digimonRepository.save(novo);

        // 🔹 Atualiza o primeiro acesso
        if (!jogador.isPrimeiroAcesso()) {
            jogador.setPrimeiroAcesso(true);
            jogadorRepository.save(jogador);
        }

        estadoJogoService.marcarDigitamaChocada(jogador);

        return ChocarDigitamaResponseDTO.builder()
                .digimonId(Long.valueOf("1"))
                .nome(sorteado)
                .imagem(buscarImagemPorNome(sorteado))
                .tipo("novo.getTipo()")
                .level(1)
                .build();
    }

    private String buscarImagemPorNome(String nome) {
        return "../images/digimons/baby1/" + nome.toLowerCase() + ".jpg";
    }
}