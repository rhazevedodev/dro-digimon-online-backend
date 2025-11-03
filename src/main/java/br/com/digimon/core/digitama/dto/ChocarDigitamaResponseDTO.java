package br.com.digimon.core.digitama.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChocarDigitamaResponseDTO {
    private Long digimonId;
    private String nome;
    private String imagem;
    private String tipo; // "Baby I"
    private int level;
}
