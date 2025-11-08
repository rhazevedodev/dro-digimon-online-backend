package br.com.digimon.core.digimon.dto;

import br.com.digimon.core.digimon.domain.Digimon;

public record ListarDigimonsDTO(Long id, String nome, String especie, int level) {
    public static ListarDigimonsDTO from(Digimon d) {
        return new ListarDigimonsDTO(d.getId(), d.getNome(), d.getSpecies().getNome(), d.getNivel());
    }
}
