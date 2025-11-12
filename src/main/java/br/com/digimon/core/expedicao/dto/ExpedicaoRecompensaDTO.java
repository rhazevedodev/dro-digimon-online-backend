package br.com.digimon.core.expedicao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpedicaoRecompensaDTO {
    private String nomeExpedicao;
    private String dificuldade;
    private int experienciaGanha;
    private int bitsGanho;

    private String nomeItemRecompensa;
    private int quantidadeItemRecompensa;
}
