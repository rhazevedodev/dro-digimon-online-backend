package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
}
