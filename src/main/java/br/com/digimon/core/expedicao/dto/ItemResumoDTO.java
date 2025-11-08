package br.com.digimon.core.expedicao.dto;

import br.com.digimon.core.item.enumerator.TipoItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResumoDTO {
    private Long id;
    private String nome;
    private TipoItem tipo;
    private String descricao;
    private String imagem;
}
