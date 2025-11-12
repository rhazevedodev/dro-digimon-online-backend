package br.com.digimon.core.digimon.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "digimon_ev")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigimonEV {

    @Id
    @Column(name = "digimon_id")
    private Long digimonId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;

    @Column(name = "ev_hp")
    private int evHp;

    @Column(name = "ev_atk")
    private int evAtk;

    @Column(name = "ev_def")
    private int evDef;

    @Column(name = "ev_int")
    private int evInt;

    @Column(name = "ev_spd")
    private int evSpd;

    @Column(name = "ev_total")
    private int evTotal;
}
