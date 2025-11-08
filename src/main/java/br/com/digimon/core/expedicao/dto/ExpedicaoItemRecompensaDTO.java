package br.com.digimon.core.expedicao.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpedicaoItemRecompensaDTO {
    private Long id;
    private int quantidade;
    private double chance;
    private ItemResumoDTO item;
}
