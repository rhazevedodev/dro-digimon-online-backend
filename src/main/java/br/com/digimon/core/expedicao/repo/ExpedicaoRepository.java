package br.com.digimon.core.expedicao.repo;

import br.com.digimon.core.expedicao.domain.Expedicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedicaoRepository extends JpaRepository<Expedicao, Long> {}

