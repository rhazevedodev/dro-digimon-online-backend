package br.com.digimon.core.inventario.domain;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.item.domain.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_inventario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class InventarioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "digimon_id", nullable = false)
    private Digimon digimon;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantidade;
}
