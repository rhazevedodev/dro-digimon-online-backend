package br.com.digimon.core.digimon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Table(name = "digimon_iv")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigimonIV {

    @Id
    @Column(name = "digimon_id")
    private Long digimonId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;

    @Column(name = "iv_hp")
    private int ivHp;

    @Column(name = "iv_atk")
    private int ivAtk;

    @Column(name = "iv_def")
    private int ivDef;

    @Column(name = "iv_int")
    private int ivInt;

    @Column(name = "iv_spd")
    private int ivSpd;

    @Column(name = "iv_total")
    private int ivTotal;

    // 🔹 Gera IVs aleatórios ao nascer
    public void gerarIVs() {
        Random r = new Random();
        ivHp = r.nextInt(100) + 1;  // 1–100
        ivAtk = r.nextInt(100) + 1;
        ivDef = r.nextInt(100) + 1;
        ivInt = r.nextInt(100) + 1;
        ivSpd = r.nextInt(100) + 1;

        // Calcula IV total como média arredondada para int
        //ivTotal = Math.round((ivHp + ivAtk + ivDef + ivInt + ivSpd) / 5.0f);
    }
}
