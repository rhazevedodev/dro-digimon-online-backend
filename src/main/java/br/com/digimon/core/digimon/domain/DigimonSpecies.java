package br.com.digimon.core.digimon.domain;

import br.com.digimon.core.digimon.enumerator.DigimonType;
import br.com.digimon.core.digimon.enumerator.DigimonElement;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "digimon_species")
public class DigimonSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String estagio; // Baby I, Rookie...

    @Enumerated(EnumType.STRING)
    private DigimonType tipo;

    @Enumerated(EnumType.STRING)
    private DigimonElement elemento;

    private int baseHp;
    private int baseAtk;
    private int baseDef;
    private int baseInt;
    private int baseSpd;
}