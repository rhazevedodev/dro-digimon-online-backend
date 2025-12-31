package br.com.digimon.core.expedicao.repo;

import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ExpedicaoAtivaRepository extends JpaRepository<ExpedicaoAtiva, Long> {
    List<ExpedicaoAtiva> findByDigimonIdAndFimAfter(Long digimonId, Instant agora);

    ExpedicaoAtiva findByDigimonIdAndExpedicaoIdAndDificuldadeAndConcluidaFalse(Long digimonId, Long expedicaoId, DificuldadeExpedicao dificuldade);
}

