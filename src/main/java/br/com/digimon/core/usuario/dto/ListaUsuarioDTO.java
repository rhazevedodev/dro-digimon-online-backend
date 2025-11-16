package br.com.digimon.core.usuario.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListaUsuarioDTO {
    private Long id;
    private String username;
    private String email;

    // Construtor
    public ListaUsuarioDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
