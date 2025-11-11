package br.com.digimon.core.expedicao.mapper;

import br.com.digimon.core.digimon.dto.DigimonResumoDTO;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.dto.ExpedicaoAtivaDTO;
import br.com.digimon.core.expedicao.dto.ExpedicaoResumoDTO;

import java.time.Duration;

public class ExpedicaoAtivaMapper {

    public static ExpedicaoAtivaDTO toDTO(ExpedicaoAtiva ativa) {
        if (ativa == null) return null;

        return ExpedicaoAtivaDTO.builder()
                .id(ativa.getId())
                .expedicao(
                        ExpedicaoResumoDTO.builder()
                                .id(ativa.getExpedicao().getId())
                                .nome(ativa.getExpedicao().getNome())
                                .build()
                )
                .dificuldade(ativa.getDificuldade().name())
                .digimon(
                        DigimonResumoDTO.builder()
                                .id(ativa.getDigimon().getId())
                                .nome(ativa.getDigimon().getNome())
                                .estagio(ativa.getDigimon().getEstagio())
                                .nivel(ativa.getDigimon().getNivel())
                                .build()
                )
                .inicio(ativa.getInicio() != null ? ativa.getInicio().toString() : null)
                .fim(ativa.getFim() != null ? ativa.getFim().toString() : null)
                .duracaoHoras(
                        (ativa.getInicio() != null && ativa.getFim() != null)
                                ? Duration.between(ativa.getInicio(), ativa.getFim()).toHours()
                                : 0
                )
                .build();
    }
}
