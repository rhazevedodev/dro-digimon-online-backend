package br.com.digimon.core.digimon.web;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.dto.ListarDigimonsDTO;
import br.com.digimon.core.digimon.service.DigimonService;
import br.com.digimon.core.digitama.domain.Digitama;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/digimon")
@RequiredArgsConstructor
public class DigimonController {

    private final DigimonService digimonService;

    @GetMapping
    public ResponseEntity<List<ListarDigimonsDTO>> listar() {
        return ResponseEntity.ok(digimonService.listarDigimons());
    }
}
