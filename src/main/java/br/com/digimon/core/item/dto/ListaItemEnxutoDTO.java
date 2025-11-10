package br.com.digimon.core.item.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListaItemEnxutoDTO {
    private int totalItens;
    private List<ItemEnxutoDTO> itens;
}
