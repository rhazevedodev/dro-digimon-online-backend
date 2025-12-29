package br.com.digimon.core.expedicao.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.expedicao.domain.Expedicao;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.domain.ExpedicaoDificuldade;
import br.com.digimon.core.expedicao.dto.ExpedicaoDTO;
import br.com.digimon.core.expedicao.dto.ExpedicaoRecompensaDTO;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.expedicao.mapper.ExpedicaoMapper;
import br.com.digimon.core.expedicao.repo.ExpedicaoAtivaRepository;
import br.com.digimon.core.expedicao.repo.ExpedicaoDificuldadeRepository;
import br.com.digimon.core.expedicao.repo.ExpedicaoRepository;
import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.repo.ItemInventarioRepository;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpedicaoService {

    private final ExpedicaoRepository expedicaoRepo;
    private final ExpedicaoAtivaRepository ativaRepo;
    private final JogadorRepository jogadorRepository;
    private final DigimonRepository digimonRepository;
    private final ItemInventarioRepository itemInventarioRepository;
    private final ExpedicaoDificuldadeRepository expedicaoDificuldadeRepository;

    public List<ExpedicaoDTO> listarExpedicoesDisponiveis(Long digimonid) {
        var digimon = digimonRepository.findById(digimonid)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        var expedicoes = expedicaoRepo.findAll().stream()
                .filter(exp -> exp.isDesbloqueadaPadrao() || digimonPossuiItem(digimon, exp.getItemRequerido()))
                .map(ExpedicaoMapper::toDTO)
                .toList();

        if (expedicoes.isEmpty())
            throw new RuntimeException("Nenhuma expedição disponível para o jogador");

        return expedicoes;
    }

    public ExpedicaoAtiva iniciarExpedicao(Long digimonId, Long expedicaoId, DificuldadeExpedicao dificuldade) {
        Digimon digimon = digimonRepository.findById(digimonId)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        Expedicao exp = expedicaoRepo.findById(expedicaoId)
                .orElseThrow(() -> new RuntimeException("Expedição não encontrada"));

        ExpedicaoAtiva ativa = new ExpedicaoAtiva();
        ativa.setDigimon(digimon);
        ativa.setExpedicao(exp);
        ativa.setDificuldade(dificuldade);
        ativa.setInicio(Instant.now());
        ativa.setFim(Instant.now().plus(getDuracao(exp, dificuldade), ChronoUnit.HOURS));

        return ativaRepo.save(ativa);
    }

    public ExpedicaoRecompensaDTO coletarRecompensa(Long ativaId) {
        ExpedicaoAtiva ativa = ativaRepo.findById(ativaId)
                .orElseThrow(() -> new RuntimeException("Expedição ativa não encontrada"));

        if (Instant.now().isBefore(ativa.getFim())) {
            throw new RuntimeException("A expedição ainda não foi concluída.");
        }

        if (ativa.isConcluida()) {
            throw new RuntimeException("A recompensa desta expedição já foi coletada.");
        }

        ativa.setConcluida(true);
        ativaRepo.save(ativa);

        Digimon digimon = ativa.getDigimon();

        // Busca os dados da dificuldade correspondente
        ExpedicaoDificuldade dificuldade = expedicaoDificuldadeRepository
                .findByExpedicaoIdAndDificuldade(
                        ativa.getExpedicao().getId(),
                        ativa.getDificuldade()
                );

        if (dificuldade == null) {
            throw new RuntimeException("Dificuldade da expedição não encontrada.");
        }

        // 🎯 Calcula recompensas
        int expGanha = dificuldade.getExpBase();
        int bitsGanho = dificuldade.getBitsBase();

        digimon.setExperiencia(digimon.getExperiencia() + expGanha);
        digimon.setBits(digimon.getBits() + bitsGanho);
        digimonRepository.save(digimon);

        // 🎁 Item recompensa
        String nomeItem = null;
        int qtdItem = 0;
        if (dificuldade.getItemRecompensa() != null) {
            nomeItem = dificuldade.getItemRecompensa().getNome();
            qtdItem = dificuldade.getQuantidadeItemRecompensa();
//            adicionarItemAoInventario(digimon, dificuldade.getItemRecompensa(), qtdItem);
        }

        log.info("Recompensa coletada: {} EXP, {} Bits, {}x {}",
                dificuldade.getExpBase(),
                dificuldade.getBitsBase(),
                dificuldade.getQuantidadeItemRecompensa(),
                dificuldade.getItemRecompensa().getNome());

        // 🧾 Monta DTO de retorno
        return ExpedicaoRecompensaDTO.builder()
                .nomeExpedicao(ativa.getExpedicao().getNome())
                .dificuldade(dificuldade.getDificuldade().name())
                .experienciaGanha(expGanha)
                .bitsGanho(bitsGanho)
                .nomeItemRecompensa(nomeItem)
                .quantidadeItemRecompensa(qtdItem)
                .build();
    }

    private boolean digimonPossuiItem(Digimon digimon, Item item) {
        if (digimon == null || digimon.getId() == null || item == null || item.getId() == null) return false;
        return itemInventarioRepository.existsByDigimonIdAndItemId(digimon.getId(), item.getId());
    }

    private long getDuracao(Expedicao exp, DificuldadeExpedicao dificuldade) {
        return exp.getDificuldades().stream()
                .filter(d -> d.getDificuldade() == dificuldade)
                .mapToLong(ExpedicaoDificuldade::getDuracaoHoras)
                .findFirst()
                .orElse(1);
    }
}
