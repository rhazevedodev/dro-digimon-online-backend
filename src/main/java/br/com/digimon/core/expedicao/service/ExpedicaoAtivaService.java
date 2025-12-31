package br.com.digimon.core.expedicao.service;

import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.dto.ExpedicaoAtivaDTO;
import br.com.digimon.core.expedicao.mapper.ExpedicaoAtivaMapper;
import br.com.digimon.core.expedicao.repo.ExpedicaoAtivaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpedicaoAtivaService {

    private final ExpedicaoAtivaRepository ativaRepo;

    public List<ExpedicaoAtivaDTO> listarPorDigimon(Long digimonId) {
        var expedicoesAtivas = ativaRepo.findByDigimonIdAndFimAfter(digimonId, Instant.now()).stream().map(ExpedicaoAtivaMapper::toDTO).toList();
        for (ExpedicaoAtivaDTO expedicao : expedicoesAtivas) {
            long totalSegundos = java.time.Duration.between(
                    Instant.now(),
                    Instant.parse(expedicao.getFim())
            ).getSeconds();
            long minutosRestantes = totalSegundos / 60;
            long segundosRestantes = totalSegundos % 60;
            expedicao.setTempoRestante(minutosRestantes + " minutos e " + segundosRestantes + " segundos");
        }
        return expedicoesAtivas;
    }
}
