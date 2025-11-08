package br.com.digimon.core.expedicao.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.expedicao.domain.Expedicao;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.domain.ExpedicaoDificuldade;
import br.com.digimon.core.expedicao.dto.ExpedicaoDTO;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.expedicao.mapper.ExpedicaoMapper;
import br.com.digimon.core.expedicao.repo.ExpedicaoAtivaRepository;
import br.com.digimon.core.expedicao.repo.ExpedicaoRepository;
import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpedicaoService {

    private final ExpedicaoRepository expedicaoRepo;
    private final ExpedicaoAtivaRepository ativaRepo;
    private final JogadorRepository jogadorRepository;
    private final DigimonRepository digimonRepository;

    public List<ExpedicaoDTO> listarExpedicoesDisponiveis(Long jogadorId) {
        var jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        var expedicoes = expedicaoRepo.findAll().stream()
                .filter(exp -> exp.isDesbloqueadaPadrao() || jogadorPossuiItem(jogador, exp.getItemRequerido()))
                .map(ExpedicaoMapper::toDTO)
                .toList();

        if (expedicoes.isEmpty())
            throw new RuntimeException("Nenhuma expedição disponível para o jogador");

        return expedicoes;
    }

    public ExpedicaoAtiva iniciarExpedicao(Long digimonId, Long expedicaoId, DificuldadeExpedicao dificuldade) {
        // Busca o Digimon enviado
        Digimon digimon = digimonRepository.findById(digimonId)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        // O jogador é derivado do Digimon
        Jogador jogador = digimon.getJogador();

        if (jogador == null) {
            throw new RuntimeException("O Digimon não está vinculado a nenhum jogador");
        }

        // Busca a expedição
        Expedicao exp = expedicaoRepo.findById(expedicaoId)
                .orElseThrow(() -> new RuntimeException("Expedição não encontrada"));

        // Cria a expedição ativa
        ExpedicaoAtiva ativa = new ExpedicaoAtiva();
        ativa.setJogador(jogador);
        ativa.setDigimonEnviado(digimon);
        ativa.setExpedicao(exp);
        ativa.setDificuldade(dificuldade);
        ativa.setInicio(Instant.now());
        ativa.setFim(Instant.now().plus(getDuracao(exp, dificuldade), ChronoUnit.HOURS));

        return ativaRepo.save(ativa);
    }

    public boolean coletarRecompensa(Long jogadorId, Long ativaId) {
        ExpedicaoAtiva ativa = ativaRepo.findById(ativaId)
                .orElseThrow(() -> new RuntimeException("Expedição ativa não encontrada"));

        if (Instant.now().isBefore(ativa.getFim())) return false;

        ativa.setConcluida(true);
        ativaRepo.save(ativa);

        Jogador jogador = ativa.getJogador();
//        jogador.adicionarItem(ativa.getExpedicao().getItemRecompensa());
        jogadorRepository.save(jogador);

        return true;
    }

    private boolean jogadorPossuiItem(Jogador jogador, Item item) {
        return jogador.getInventario().stream()
                .anyMatch(i -> i.getItem().equals(item));
    }

    private long getDuracao(Expedicao exp, DificuldadeExpedicao dificuldade) {
        return exp.getDificuldades().stream()
                .filter(d -> d.getDificuldade() == dificuldade)
                .mapToLong(ExpedicaoDificuldade::getDuracaoHoras)
                .findFirst()
                .orElse(1);
    }
}
