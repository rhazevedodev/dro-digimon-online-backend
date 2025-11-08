package br.com.digimon.core.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdicionarItemInventarioDTO {
    private Long idJogador;
    private Long idDigimon;
    private Long idItem;
    private int quantidade;
}
