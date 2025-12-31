package br.com.digimon.core.expedicao.dto;

import br.com.digimon.core.digimon.dto.DigimonResumoDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExpedicaoAtivaDTO {
    private Long id;
    private ExpedicaoResumoDTO expedicao;
    private String dificuldade;
    private DigimonResumoDTO digimon;
    private String inicio;
    private String fim;
    private long duracaoHoras;
    private String tempoRestante;
}
