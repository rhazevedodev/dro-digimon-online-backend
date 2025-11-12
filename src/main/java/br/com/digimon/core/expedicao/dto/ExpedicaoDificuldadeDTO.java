package br.com.digimon.core.expedicao.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ExpedicaoDificuldadeDTO {
    private Long id;
    private String dificuldade;
    private int poderMinimo;
    private int duracaoHoras;
}
