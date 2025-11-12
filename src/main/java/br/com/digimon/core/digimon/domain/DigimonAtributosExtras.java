package br.com.digimon.core.digimon.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "digimon_atributos_extras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigimonAtributosExtras {

    @Id
    @Column(name = "digimon_id")
    private Long digimonId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;

    @Column(name = "crit_rate")
    private double critRate;

    @Column(name = "crit_damage")
    private double critDamage;

    private double accuracy;
    private double evade;
    private int bond;
}
