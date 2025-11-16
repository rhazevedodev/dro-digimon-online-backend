package br.com.digimon.core.usuario.web;

import br.com.digimon.core.usuario.domain.Usuario;
import br.com.digimon.core.usuario.dto.AuthResponseDTO;
import br.com.digimon.core.usuario.dto.CriarUsuarioDTO;
import br.com.digimon.core.usuario.dto.LoginDTO;
import br.com.digimon.core.usuario.service.JwtService;
import br.com.digimon.core.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CriarUsuarioDTO dto) {

        ResponseEntity<?> validacoes = validarCadastro(dto);
        if(validacoes != null) {
            return validacoes;
        }

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

    private ResponseEntity<?> validarCadastro(CriarUsuarioDTO dto) {
        if(dto.getUsername().isBlank() || dto.getPassword().isBlank() || dto.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("Campos obrigatórios não podem estar em branco.");
        }
        if(dto.getPassword().length() < 6) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 6 caracteres.");
        }
        if(!dto.getEmail().contains("@")) {
            return ResponseEntity.badRequest().body("Email inválido.");
        }
        if(dto.getUsername().length() < 3) {
            return ResponseEntity.badRequest().body("O nome de usuário deve ter pelo menos 3 caracteres.");
        }
        if(dto.getUsername().length() > 20) {
            return ResponseEntity.badRequest().body("O nome de usuário não pode exceder 20 caracteres.");
        }
        if(dto.getEmail().length() > 20) {
            return ResponseEntity.badRequest().body("O email não pode exceder 20 caracteres.");
        }
        if(dto.getPassword().length() > 20) {
            return ResponseEntity.badRequest().body("A senha não pode exceder 20 caracteres.");
        }
        if(dto.getUsername().matches(".*\\s+.*")) {
            return ResponseEntity.badRequest().body("O nome de usuário não pode conter espaços.");
        }
        if(dto.getPassword().matches(".*\\s+.*")) {
            return ResponseEntity.badRequest().body("A senha não pode conter espaços.");
        }
        if(dto.getEmail().matches(".*\\s+.*")) {
            return ResponseEntity.badRequest().body("O email não pode conter espaços.");
        }
        if(!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return ResponseEntity.badRequest().body("Formato de email inválido.");
        }
        if(usuarioService.existsByUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de usuário já existe.");
        }
        if(usuarioService.existsByEmail(dto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }
        return null;
    }
}