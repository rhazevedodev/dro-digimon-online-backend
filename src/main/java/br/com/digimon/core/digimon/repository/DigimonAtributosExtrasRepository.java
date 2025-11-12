package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.DigimonAtributosExtras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonAtributosExtrasRepository extends JpaRepository<DigimonAtributosExtras, Long> {

    // Busca atributos extras de um Digimon pelo ID
    DigimonAtributosExtras findByDigimonId(Long digimonId);
}
