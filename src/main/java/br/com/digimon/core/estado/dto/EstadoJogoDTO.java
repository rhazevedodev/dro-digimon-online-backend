package br.com.digimon.core.estado.dto;

public record EstadoJogoDTO(
        boolean digitamaSelecionada,
        boolean digitamaChocada,
        boolean selecaoDigitamaParaSlot
) {}