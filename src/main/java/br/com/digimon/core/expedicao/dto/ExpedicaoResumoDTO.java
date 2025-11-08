package br.com.digimon.core.expedicao.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpedicaoResumoDTO {
    private Long id;
    private String nome;
}