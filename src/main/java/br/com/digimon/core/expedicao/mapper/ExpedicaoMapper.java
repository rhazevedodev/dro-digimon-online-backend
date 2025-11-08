package br.com.digimon.core.expedicao.mapper;

import br.com.digimon.core.expedicao.domain.Expedicao;
import br.com.digimon.core.expedicao.domain.ExpedicaoDificuldade;
import br.com.digimon.core.expedicao.domain.ExpedicaoItemRecompensa;
import br.com.digimon.core.expedicao.dto.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpedicaoMapper {

    public static ExpedicaoDTO toDTO(Expedicao e) {
        return ExpedicaoDTO.builder()
                .id(e.getId())
                .nome(e.getNome())
                .descricao(e.getDescricao())
                .desbloqueadaPadrao(e.isDesbloqueadaPadrao())
                .dificuldades(
                        e.getDificuldades().stream()
                                .map(ExpedicaoMapper::toDificuldadeDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static ExpedicaoDificuldadeDTO toDificuldadeDTO(ExpedicaoDificuldade d) {
        return ExpedicaoDificuldadeDTO.builder()
                .id(d.getId())
                .dificuldade(d.getDificuldade().name())
                .poderMinimo(d.getPoderMinimo())
                .duracaoHoras(d.getDuracaoHoras())
                .recompensas(
                        d.getRecompensas().stream()
                                .map(ExpedicaoMapper::toRecompensaDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private static ExpedicaoItemRecompensaDTO toRecompensaDTO(ExpedicaoItemRecompensa r) {
        return ExpedicaoItemRecompensaDTO.builder()
                .id(r.getId())
                .quantidade(r.getQuantidade())
                .chance(r.getChance())
                .item(
                        ItemResumoDTO.builder()
                                .id(r.getItem().getId())
                                .nome(r.getItem().getNome())
                                .tipo(r.getItem().getTipo())
                                .descricao(r.getItem().getDescricao())
                                .imagem(r.getItem().getImagem())
                                .build()
                )
                .build();
    }
}