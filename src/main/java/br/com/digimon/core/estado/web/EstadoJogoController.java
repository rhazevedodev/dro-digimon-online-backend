package br.com.digimon.core.estado.web;

import br.com.digimon.core.estado.domain.EstadoJogo;
import br.com.digimon.core.estado.service.EstadoJogoService;
import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
public class EstadoJogoController {

    private final EstadoJogoService estadoService;
    private final JogadorService jogadorService;

    /**
     * Retorna o estado atual do jogador logado (digitama selecionada, chocada, etc.)
     */
    @GetMapping
    public ResponseEntity<EstadoJogo> getEstadoAtual(Authentication auth) {
        String username = auth.getName();
        Jogador jogador = jogadorService.buscarPorUsername(username);
        EstadoJogo estado = estadoService.getOuCriarEstado(jogador);
        return ResponseEntity.ok(estado);
    }

    /**
     * Reinicia o estado do jogador (opcional, útil para testes ou resets)
     */
    @PostMapping("/resetar")
    public ResponseEntity<Void> resetarEstado(Authentication auth) {
        String username = auth.getName();
        Jogador jogador = jogadorService.buscarPorUsername(username);
        estadoService.resetarEstado(jogador);
        return ResponseEntity.ok().build();
    }
}
