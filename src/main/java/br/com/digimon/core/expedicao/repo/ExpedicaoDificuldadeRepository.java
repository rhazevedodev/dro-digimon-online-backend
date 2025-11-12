package br.com.digimon.core.expedicao.repo;

import br.com.digimon.core.expedicao.domain.ExpedicaoDificuldade;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedicaoDificuldadeRepository extends JpaRepository<ExpedicaoDificuldade, Long> {
    ExpedicaoDificuldade findByExpedicaoIdAndDificuldade(Long id, DificuldadeExpedicao dificuldade);
}

