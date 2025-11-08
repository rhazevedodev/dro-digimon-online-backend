package br.com.digimon.core.expedicao.service;

import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.expedicao.domain.Expedicao;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.domain.ExpedicaoDificuldade;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
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
    private final JogadorRepository jogadorRepo;
    private final DigimonRepository digimonRepo;

    public List<Expedicao> listarExpedicoesDisponiveis(Long jogadorId) {
        Jogador jogador = jogadorRepo.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        return expedicaoRepo.findAll().stream()
                .filter(exp -> exp.isDesbloqueadaPadrao() || jogadorPossuiItem(jogador, exp.getItemRequerido()))
                .toList();
    }

    public ExpedicaoAtiva iniciarExpedicao(Long jogadorId, Long expedicaoId, DificuldadeExpedicao dificuldade) {
        Jogador jogador = jogadorRepo.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        Expedicao exp = expedicaoRepo.findById(expedicaoId)
                .orElseThrow(() -> new RuntimeException("Expedição não encontrada"));

        ExpedicaoAtiva ativa = new ExpedicaoAtiva();
        ativa.setJogador(jogador);
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
        jogadorRepo.save(jogador);

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
