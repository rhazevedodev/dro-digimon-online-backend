package br.com.digimon.core.usuario.web;

import br.com.digimon.core.usuario.domain.Usuario;
import br.com.digimon.core.usuario.dto.AuthResponseDTO;
import br.com.digimon.core.usuario.dto.CriarUsuarioDTO;
import br.com.digimon.core.usuario.dto.LoginDTO;
import br.com.digimon.core.usuario.service.JwtService;
import br.com.digimon.core.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody CriarUsuarioDTO dto) {
        Usuario novo = usuarioService.criarUsuario(dto);
        String token = jwtService.generateToken(novo);
        return ResponseEntity.ok(new AuthResponseDTO(token, novo.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        Usuario usuario = usuarioService.buscarPorUsername(dto.getUsername());
        String token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(new AuthResponseDTO(token, usuario.getUsername()));
    }
}