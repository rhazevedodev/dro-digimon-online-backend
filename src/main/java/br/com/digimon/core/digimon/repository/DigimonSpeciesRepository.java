package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.DigimonSpecies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigimonSpeciesRepository extends JpaRepository<DigimonSpecies, Long> {
    Optional<DigimonSpecies> findByNomeIgnoreCase(String nome);
}