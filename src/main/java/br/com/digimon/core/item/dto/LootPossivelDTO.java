package br.com.digimon.core.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LootPossivelDTO {
    private String nome;
    private int quantidadeMinima;
    private int quantidadeMaxima;
    private double chance;
}
