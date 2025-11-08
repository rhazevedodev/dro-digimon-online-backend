package br.com.digimon.core.expedicao.web;

import br.com.digimon.core.expedicao.domain.Expedicao;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.expedicao.service.ExpedicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expedicoes")
@RequiredArgsConstructor
public class ExpedicaoController {

    private final ExpedicaoService service;

    @GetMapping
    public ResponseEntity<List<Expedicao>> listar(@RequestParam Long jogadorId) {
        return ResponseEntity.ok(service.listarExpedicoesDisponiveis(jogadorId));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<ExpedicaoAtiva> iniciar(
            @PathVariable Long id,
            @RequestParam Long jogadorId,
            @RequestParam DificuldadeExpedicao dificuldade
    ) {
        return ResponseEntity.ok(service.iniciarExpedicao(jogadorId, id, dificuldade));
    }

    @PostMapping("/coletar/{ativaId}")
    public ResponseEntity<String> coletar(
            @PathVariable Long ativaId,
            @RequestParam Long jogadorId
    ) {
        boolean sucesso = service.coletarRecompensa(jogadorId, ativaId);
        return sucesso ? ResponseEntity.ok("Recompensa coletada!")
                : ResponseEntity.badRequest().body("Expedição ainda não concluída!");
    }
}