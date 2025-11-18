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
import java.util.Map;

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

    // 🔹 Requisitos de desbloqueio por slot (3 → 8)
    private static final Map<Integer, Integer> SLOT_UNLOCK_REQUIREMENTS = Map.of(
            3, 10000,
            4, 50000,
            5, 250000,
            6, 1000000,
            7, 5000000,
            8, 99999999
    );

    // ========================================================
    // 🔥 MONTAR JORNADA COMPLETA (8 SLOTS)
    // ========================================================
    public JornadaResponseDTO montarJornada(Jogador jogador) {

        List<Digimon> digimons = digimonRepository.findByJogadorId(jogador.getId());
        int qtd = digimons.size();
        int pontos = jogador.getPontosDigitais();

        List<JornadaResponseDTO.SlotDTO> slots = new ArrayList<>();

        // ========================================================
        // SLOT 1 — sempre primeiro Digimon
        // ========================================================
        if (qtd >= 1) {
            slots.add(slotOcupado(digimons.get(0)));
        } else {
            slots.add(slotBloqueado());
        }

        // ========================================================
        // SLOT 2 — segundo Digimon ou disponível
        // ========================================================
        if (qtd >= 2) {
            slots.add(slotOcupado(digimons.get(1)));
        } else {
            slots.add(slotDisponivel());
        }

        // ========================================================
        // SLOTS 3 a 8 — desbloqueios individuais + preenchimento
        // ========================================================
        for (int slotIndex = 3; slotIndex <= 8; slotIndex++) {

            int digimonIndex = slotIndex - 1; // índice real da lista

            // Caso já existam digimons suficientes para ocupar esse slot
            if (digimonIndex < qtd) {
                slots.add(slotOcupado(digimons.get(digimonIndex)));
                continue;
            }

            // Verifica desbloqueio individual
            boolean desbloqueado = podeDesbloquearSlot(slotIndex, pontos);

            if (desbloqueado) {
                slots.add(slotDisponivel());
            } else {
                slots.add(slotBloqueado());
            }
        }

        return new JornadaResponseDTO(pontos, slots);
    }

    // ========================================================
    // 🔹 Verifica desbloqueio individual do slot
    // ========================================================
    private boolean podeDesbloquearSlot(int slotNumero, int pontosDigitais) {
        return pontosDigitais >= SLOT_UNLOCK_REQUIREMENTS.getOrDefault(slotNumero, Integer.MAX_VALUE);
    }

    // ========================================================
    // 🔹 Helpers para criar SlotDTO
    // ========================================================
    private JornadaResponseDTO.SlotDTO slotOcupado(Digimon digimon) {
        return new JornadaResponseDTO.SlotDTO(
                "ocupado",
                new JornadaResponseDTO.DigimonDTO(
                        digimon.getId(),
                        digimon.getNome(),
                        digimon.getNivel(),
                        "/assets/digimons/" + digimon.getNome().toLowerCase() + ".png"
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
