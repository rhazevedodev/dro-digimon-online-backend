package br.com.digimon.core.expedicao.web;

import br.com.digimon.core.expedicao.dto.ExpedicaoAtivaDTO;
import br.com.digimon.core.expedicao.dto.ExpedicaoDTO;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.expedicao.mapper.ExpedicaoAtivaMapper;
import br.com.digimon.core.expedicao.service.ExpedicaoAtivaService;
import br.com.digimon.core.expedicao.service.ExpedicaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expedicoes")
@RequiredArgsConstructor
@Slf4j
public class ExpedicaoController {

    private final ExpedicaoService service;
    private final ExpedicaoAtivaService expedicaoAtivaService;


    @GetMapping
    public ResponseEntity<List<ExpedicaoDTO>> listar(@RequestParam Long jogadorId) {
        log.info("Listando expedições disponíveis para o jogador ID: {}", jogadorId);
        return ResponseEntity.ok(service.listarExpedicoesDisponiveis(jogadorId));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<ExpedicaoAtivaDTO> iniciar(
            @PathVariable Long id,
            @RequestParam Long digimonId,
            @RequestParam DificuldadeExpedicao dificuldade
    ) {
        log.info("Iniciando expedição ID: {} com Digimon ID: {} na dificuldade: {}", id, digimonId, dificuldade);
        var ativa = service.iniciarExpedicao(digimonId, id, dificuldade);
        return ResponseEntity.ok(ExpedicaoAtivaMapper.toDTO(ativa));
    }

    @PostMapping("/coletar/{ativaId}")
    public ResponseEntity<String> coletar(@PathVariable Long ativaId) {
        log.info("Coletando recompensa da expedição ativa ID: {}", ativaId);
        boolean sucesso = service.coletarRecompensa(ativaId);
        return sucesso
                ? ResponseEntity.ok("Recompensa coletada com sucesso!")
                : ResponseEntity.badRequest().body("Expedição ainda não concluída!");
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<ExpedicaoAtivaDTO>> listarAtivas(@RequestParam Long digimonId) {
        log.info("Listando expedições ativas para o jogador ID: {}", digimonId);
        return ResponseEntity.ok(expedicaoAtivaService.listarPorDigimon(digimonId));
    }
}