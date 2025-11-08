package br.com.digimon.core.expedicao.mapper;

import br.com.digimon.core.digimon.dto.DigimonResumoDTO;
import br.com.digimon.core.expedicao.domain.ExpedicaoAtiva;
import br.com.digimon.core.expedicao.dto.*;

import java.time.Duration;

public class ExpedicaoAtivaMapper {

    public static ExpedicaoAtivaDTO toDTO(ExpedicaoAtiva ativa) {
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
                                .id(ativa.getDigimonEnviado().getId())
                                .nome(ativa.getDigimonEnviado().getNome())
                                .estagio(ativa.getDigimonEnviado().getEstagio())
                                .nivel(ativa.getDigimonEnviado().getNivel())
                                .build()
                )
                .inicio(ativa.getInicio().toString())
                .fim(ativa.getFim().toString())
                .duracaoHoras(Duration.between(ativa.getInicio(), ativa.getFim()).toHours())
                .build();
    }
}
