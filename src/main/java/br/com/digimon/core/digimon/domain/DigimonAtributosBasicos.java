package br.com.digimon.core.digimon.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "digimon_atributos_basicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigimonAtributosBasicos {

    @Id
    @Column(name = "digimon_id")
    private Long digimonId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;

    private int hp;
    private int atk;
    private int def;
    @Column(name = "int_")
    private int intValue; // "int" é palavra reservada, então usamos intValue no Java
    private int spd;
}
