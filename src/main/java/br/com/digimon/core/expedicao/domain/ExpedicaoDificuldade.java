package br.com.digimon.core.expedicao.domain;

import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.item.domain.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expedicao_dificuldade")
@Data
public class ExpedicaoDificuldade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expedicao_id", nullable = false)
    private Expedicao expedicao;

    @Enumerated(EnumType.STRING)
    private DificuldadeExpedicao dificuldade; // FACIL, MEDIA, DIFICIL, EXTREMA

    private int poderMinimo;
    private int duracaoHoras;

    private int expBase;  // 💎 novo campo
    private int bitsBase; // 💰 novo campo

    @ManyToOne
    @JoinColumn(name = "item_recompensa_id")
    private Item itemRecompensa;

    private int quantidadeItemRecompensa = 1;
}