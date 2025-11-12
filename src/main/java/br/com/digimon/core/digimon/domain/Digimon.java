package br.com.digimon.core.digimon.domain;

import br.com.digimon.core.digimon.enumerator.DigimonPersonality;
import br.com.digimon.core.jogador.domain.Jogador;
import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "digimon")
public class Digimon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;

    @Column(nullable = false)
    private String nome;
    private int nivel;
    private int experiencia;
    private int energia;
    private int bits;
    private int powerTotal;
    @Enumerated(EnumType.STRING)
    private DigimonPersonality personality;
    private String estagio;
    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private DigimonSpecies species;

    @OneToOne(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private DigimonAtributosBasicos atributosBasicos;

    @OneToOne(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private DigimonAtributosExtras atributosExtras;

    @OneToOne(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private DigimonIV iv;

    @OneToOne(mappedBy = "digimon", cascade = CascadeType.ALL, orphanRemoval = true)
    private DigimonEV ev;

}