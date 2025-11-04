package br.com.digimon.core.digimon.service;

import br.com.digimon.core.digimon.domain.DigimonSpecies;
import br.com.digimon.core.digimon.repository.DigimonSpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DigimonSpeciesService {

    private final DigimonSpeciesRepository speciesRepository;

    public DigimonSpecies findByName(String nome) {
        return speciesRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada: " + nome));
    }
}
