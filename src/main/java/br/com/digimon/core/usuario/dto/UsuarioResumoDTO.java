package br.com.digimon.core.usuario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResumoDTO {
    private Long id;
    private String username;
}
