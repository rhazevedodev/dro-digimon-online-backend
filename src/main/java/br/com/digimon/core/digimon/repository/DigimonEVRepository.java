package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.DigimonEV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonEVRepository extends JpaRepository<DigimonEV, Long> {

    // Busca os EVs de um Digimon pelo ID
    DigimonEV findByDigimonId(Long digimonId);
}
