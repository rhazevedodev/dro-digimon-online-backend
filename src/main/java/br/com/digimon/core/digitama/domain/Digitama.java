package br.com.digimon.core.digitama.domain;

import java.util.List;

public record Digitama(
        Long id,
        String nome,
        String imagem,
        List<String> possiveis
) {}