package br.com.digimon.core.jogador.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.dto.CriarJogadorDTO;
import br.com.digimon.core.jogador.dto.JogadorResponseDTO;
import br.com.digimon.core.jogador.dto.JornadaResponseDTO;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import br.com.digimon.core.usuario.repo.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final UsuarioRepository usuarioRepository;
    private final DigimonRepository digimonRepository;

    public Jogador criarJogador(CriarJogadorDTO dto){
        var usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var jogador = Jogador.builder()
                .usuario(usuario)
                .pontosDigitais(dto.getPontosDigitais())
                .build();

        return jogadorRepository.save(jogador);

    }

    public JogadorResponseDTO getJogadorResponse(String username) {
        Jogador jogador = buscarPorUsername(username);
        return JogadorResponseDTO.fromEntity(jogador);
    }

    public Jogador buscarPorUsername(String username) {
        return jogadorRepository.findByUsuarioUsername(username)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado para o usuário: " + username));
    }

    public JornadaResponseDTO montarJornada(Jogador jogador) {

        List<Digimon> digimons = digimonRepository.findByJogadorId(jogador.getId());
        int qtd = digimons.size();
        int pontos = jogador.getPontosDigitais();

        List<JornadaResponseDTO.SlotDTO> slots = new ArrayList<>();

        // ------------------------
        // SLOT 1
        // ------------------------
        if (qtd >= 1) {
            slots.add(slotOcupado(digimons.get(0)));
        } else {
            slots.add(slotBloqueado());
        }

        // ------------------------
        // SLOT 2
        // ------------------------
        if (qtd >= 2) {
            slots.add(slotOcupado(digimons.get(1)));
        } else {
            slots.add(slotDisponivel());
        }

        // ------------------------
        // SLOTS 3 a 8
        // ------------------------
        boolean desbloquearAvancados = (pontos == 99_999_999);

        for (int i = 2; i < 8; i++) {

            // Se jogador desbloqueou e já existe digimon naquela posição
            if (i < qtd) {
                slots.add(slotOcupado(digimons.get(i)));
            }

            // Se jogador desbloqueou mas não tem digimon naquele slot
            else if (desbloquearAvancados) {
                slots.add(slotDisponivel());
            }

            // Não desbloqueou → bloqueado
            else {
                slots.add(slotBloqueado());
            }
        }

        return new JornadaResponseDTO(
                pontos,
                slots
        );
    }

    private JornadaResponseDTO.SlotDTO slotOcupado(Digimon d) {
        return new JornadaResponseDTO.SlotDTO(
                "ocupado",
                new JornadaResponseDTO.DigimonDTO(
                        d.getId(),
                        d.getNome(),
                        d.getNivel(),
                        "/assets/digimons/" + d.getNome().toLowerCase() + ".png"
                )
        );
    }

    private JornadaResponseDTO.SlotDTO slotDisponivel() {
        return new JornadaResponseDTO.SlotDTO("disponivel", null);
    }

    private JornadaResponseDTO.SlotDTO slotBloqueado() {
        return new JornadaResponseDTO.SlotDTO("bloqueado", null);
    }
}
