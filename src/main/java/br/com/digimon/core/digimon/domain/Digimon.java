package br.com.digimon.core.digimon.domain;

import br.com.digimon.core.jogador.domain.Jogador;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especie;

    private int nivel = 1;
    private int experiencia = 0;
    private int energia = 100;
    private int powerTotal = 0;
    private int bits = 0;
    private int atk = 0;
    private int def = 0;
    private int spd = 0;
    private int hp = 0;

    @Column(nullable = false)
    private boolean ativo = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;
}