package br.com.digimon.core.digimon.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.dto.ListarDigimonsDTO;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DigimonService {

    private final DigimonRepository digimonRepository;

    public List<ListarDigimonsDTO> listarDigimons() {
        return digimonRepository.findAll()
                .stream()
                .map(ListarDigimonsDTO::from)
                .toList();
    }

}
