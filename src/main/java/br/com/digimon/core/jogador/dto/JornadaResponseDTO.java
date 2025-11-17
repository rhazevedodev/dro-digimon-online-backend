package br.com.digimon.core.jogador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JornadaResponseDTO {
    private int pontosDigitais;
    private List<SlotDTO> slots;

    @Data
    @AllArgsConstructor
    public static class SlotDTO {
        private String status; // ocupado, disponivel, bloqueado
        private DigimonDTO digimon;
    }

    @Data
    @AllArgsConstructor
    public static class DigimonDTO {
        private Long id;
        private String nome;
        private int nivel;
        private String imagem;
    }
}
