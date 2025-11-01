package br.com.digimon.core.usuario.dto;

import lombok.Data;

@Data
public class CriarUsuarioDTO {
    private String username;
    private String email;
    private String password;
}