package br.com.digimon.core.usuario.service;

import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.dto.CriarJogadorDTO;
import br.com.digimon.core.jogador.service.JogadorService;
import br.com.digimon.core.usuario.domain.Usuario;
import br.com.digimon.core.usuario.dto.CriarUsuarioDTO;
import br.com.digimon.core.usuario.repo.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JogadorService jogadorService;

    @Transactional
    public Usuario criarUsuario(CriarUsuarioDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername()))
            throw new RuntimeException("Usuário já existe");

        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new RuntimeException("Email já cadastrado");

        Usuario usuario = Usuario.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        usuarioRepository.save(usuario);

        jogadorService.criarJogador(new CriarJogadorDTO(usuario.getId(),0));

        return usuario;
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}