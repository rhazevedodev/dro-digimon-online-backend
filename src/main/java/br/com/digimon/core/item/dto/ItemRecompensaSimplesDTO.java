package br.com.digimon.core.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ItemRecompensaSimplesDTO {
    private String nome;
    private int quantidade;
}
