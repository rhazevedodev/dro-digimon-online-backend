package br.com.digimon.core.expedicao.repo;

import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpedicaoAtivaRepository extends JpaRepository<ExpedicaoAtiva, Long> {
    List<ExpedicaoAtiva> findByJogadorId(Long jogadorId);
}

