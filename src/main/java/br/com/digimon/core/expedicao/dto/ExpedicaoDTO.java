package br.com.digimon.core.expedicao.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ExpedicaoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private boolean desbloqueadaPadrao;
    private List<ExpedicaoDificuldadeDTO> dificuldades;
}
