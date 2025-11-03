package br.com.digimon.core.jogador.web;

import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.dto.CriarJogadorDTO;
import br.com.digimon.core.jogador.dto.JogadorResponseDTO;
import br.com.digimon.core.jogador.service.JogadorService;
import br.com.digimon.core.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogador")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<Jogador> criar(@RequestBody CriarJogadorDTO dto) {
        Jogador novo = jogadorService.criarJogador(dto);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/me")
    public ResponseEntity<JogadorResponseDTO> me(Authentication auth) {
        Object principal = auth.getPrincipal();
        String username;

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else if (principal instanceof Usuario usuario) {
            username = usuario.getUsername();
        } else {
            username = auth.getName();
        }

        JogadorResponseDTO dto = jogadorService.getJogadorResponse(username);
        return ResponseEntity.ok(dto);
    }
}
