package br.com.digimon.core.digimon.repository;

import br.com.digimon.core.digimon.domain.DigimonAtributosBasicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigimonAtributosBasicosRepository extends JpaRepository<DigimonAtributosBasicos, Long> {

    // Busca atributos básicos de um Digimon pelo ID
    DigimonAtributosBasicos findByDigimonId(Long digimonId);
}
