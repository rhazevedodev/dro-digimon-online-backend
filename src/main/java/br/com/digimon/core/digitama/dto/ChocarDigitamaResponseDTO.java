package br.com.digimon.core.digitama.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class ChocarDigitamaResponseDTO {
    private Long digimonId;
    private String nome;
    private String imagem;
    private String tipo;
    private String elemento;
    private String personalidade;
    private Map<String, ?> ivs;
    private int level;
}
