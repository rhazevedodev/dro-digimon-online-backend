package br.com.digimon.core.item.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_loot_pool")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemLootPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O item que PODE conceder o loot (ex: Baú de Fragmentos - Rookie)
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_consumivel_id")
    private Item itemConsumivel;

    // O item que É dado como recompensa (ex: Fragmento DNA Agumon)
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_recompensa_id")
    private Item itemRecompensa;

    @Column(nullable = false)
    private int quantidadeMinima;

    @Column(nullable = false)
    private int quantidadeMaxima;

    @Column(nullable = false)
    private double chance; // 0.0 a 1.0
}