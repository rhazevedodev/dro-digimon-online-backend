package br.com.digimon.core.digitama.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DigitamaSelecaoResponseDTO {
    private String mensagem;
    private DigitamaInfo digitama;
    private JogadorInfo jogador;

    @Data
    @AllArgsConstructor
    public static class DigitamaInfo {
        private Long id;
        private String nome;
        private String imagem;
    }

    @Data
    @AllArgsConstructor
    public static class JogadorInfo {
        private Long id;
        private boolean digitamaSelecionada;
        private Long digitamaIdSelecionada;
    }
}