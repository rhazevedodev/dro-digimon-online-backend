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

    @Column(nullable = false)
    private String nome;

    private String estagio;

    private int nivel = 1;
    private int experiencia = 0;
    private int energia = 0;
    private int powerTotal = 0;
    private int bits = 0;

    @Column(nullable = false)
    private boolean ativo = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private DigimonSpecies species;

    @Enumerated(EnumType.STRING)
    private DigimonPersonality personality;

    // Atributos dinâmicos
    private int hp;
    private int atk;
    private int def;
    private int int_;
    private int spd;

    private double critRate;
    private double critDamage;
    private double accuracy;
    private double evade;
    private double bond;

    // 🔹 IVs (1–100)
    private int ivHp;
    private int ivAtk;
    private int ivDef;
    private int ivInt;
    private int ivSpd;

    // 🔹 IV total (média dos cinco)
    private double ivTotal;

    // 🔹 Gera IVs aleatórios ao nascer
    public void gerarIVs() {
        Random r = new Random();
        ivHp = r.nextInt(100) + 1;  // 1–100
        ivAtk = r.nextInt(100) + 1;
        ivDef = r.nextInt(100) + 1;
        ivInt = r.nextInt(100) + 1;
        ivSpd = r.nextInt(100) + 1;

        // Calcula IV total como média
        ivTotal = Math.round(((ivHp + ivAtk + ivDef + ivInt + ivSpd) / 5.0) * 100.0) / 100.0;
    }

    // 🔹 EVs (Effort Values)
    private int evHp;
    private int evAtk;
    private int evDef;
    private int evInt;
    private int evSpd;

    @Transient
    public int getEvTotal() {
        return evHp + evAtk + evDef + evInt + evSpd;
    }

}