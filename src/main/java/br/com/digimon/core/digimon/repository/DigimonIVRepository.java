package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.DigimonIV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonIVRepository extends JpaRepository<DigimonIV, Long> {

    // Busca os IVs de um Digimon pelo ID
    DigimonIV findByDigimonId(Long digimonId);
}
