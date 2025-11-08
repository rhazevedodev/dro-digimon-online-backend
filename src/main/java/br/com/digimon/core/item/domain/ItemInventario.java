package br.com.digimon.core.item.domain;

import br.com.digimon.core.digimon.domain.Digimon;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_inventario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ItemInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Digimon digimon;

    @ManyToOne(optional = false)
    private Item item;

    @Column(nullable = false)
    private int quantidade;
}
