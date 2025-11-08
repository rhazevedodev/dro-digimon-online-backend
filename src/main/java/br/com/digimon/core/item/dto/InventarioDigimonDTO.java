package br.com.digimon.core.item.dto;

import br.com.digimon.core.item.domain.ItemInventario;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InventarioDigimonDTO {

    private Long digimonId;
    private String digimonNome;
    private List<ItemInventarioDTO> itens;

    @Getter
    @Builder
    public static class ItemInventarioDTO {
        private Long idItem;
        private String nome;
        private String tipo;
        private int quantidade;
        private String imagem;

        public static ItemInventarioDTO fromEntity(ItemInventario ii) {
            return ItemInventarioDTO.builder()
                    .idItem(ii.getItem().getId())
                    .nome(ii.getItem().getNome())
                    .tipo(ii.getItem().getTipo().name())
                    .quantidade(ii.getQuantidade())
                    .imagem(ii.getItem().getImagem())
                    .build();
        }
    }
}
