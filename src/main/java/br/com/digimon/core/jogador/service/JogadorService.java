package br.com.digimon.core.jogador.service;

import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.dto.CriarJogadorDTO;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import br.com.digimon.core.usuario.repo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final UsuarioRepository usuarioRepository;

    public Jogador criarJogador(CriarJogadorDTO dto){
        var usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var jogador = Jogador.builder()
                .usuario(usuario)
                .pontosDigitais(dto.getPontosDigitais())
                .build();

        return jogadorRepository.save(jogador);

    }
}
