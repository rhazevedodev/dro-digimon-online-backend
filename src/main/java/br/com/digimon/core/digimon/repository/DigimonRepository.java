package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigimonRepository extends JpaRepository<Digimon, Long> {
    @Override
    List<Digimon> findAll();
}
