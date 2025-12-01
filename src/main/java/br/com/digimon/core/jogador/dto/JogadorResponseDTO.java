package br.com.digimon.core.jogador.dto;

import br.com.digimon.core.digimon.dto.DigimonResumoDTO;
import br.com.digimon.core.usuario.dto.UsuarioResumoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.stream.Collectors;

import java.util.List;

@Data
@Builder
public class JogadorResponseDTO {
    private Long id;
    private int pontosDigitais;
    private String primeiroAcesso;
    private UsuarioResumoDTO usuario;
    private List<DigimonResumoDTO> digimons;

    // Conversor estático
    public static JogadorResponseDTO fromEntity(br.com.digimon.core.jogador.domain.Jogador jogador) {
        return JogadorResponseDTO.builder()
                .id(jogador.getId())
                .pontosDigitais(jogador.getPontosDigitais())
                .primeiroAcesso(Boolean.toString(jogador.isPrimeiroAcesso()))
                .usuario(UsuarioResumoDTO.builder()
                        .id(jogador.getUsuario().getId())
                        .username(jogador.getUsuario().getUsername())
                        .build())
                .digimons(jogador.getDigimons() != null
                        ? jogador.getDigimons().stream()
                        .map(d -> DigimonResumoDTO.builder()
                                .id(d.getId())
                                .nome(d.getNome())
                                .ativo(d.isAtivo())
                                .estagio(d.getEstagio())
                                .nivel(d.getNivel())
                                .build())
                        .collect(Collectors.toList())
                        : List.of())
                .build();
    }
}