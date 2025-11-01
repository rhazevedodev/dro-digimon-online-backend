package br.com.digimon.core.jogador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarJogadorDTO {
    private Long usuarioId;
    private int pontosDigitais = 0;
}
