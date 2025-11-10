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

    public List<ExpedicaoAtivaDTO> listarPorJogador(Long jogadorId) {
        return ativaRepo.findByJogadorIdAndFimAfter(jogadorId, Instant.now())
                .stream()
                .map(ExpedicaoAtivaMapper::toDTO)
                .toList();
    }
}
