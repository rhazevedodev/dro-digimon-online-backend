package br.com.digimon.core.digimon.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DigimonResumoDTO {
    private Long id;
    private String nome;
    private boolean ativo;
}