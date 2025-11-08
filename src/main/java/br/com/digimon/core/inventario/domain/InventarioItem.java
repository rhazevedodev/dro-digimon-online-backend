package br.com.digimon.core.inventario.domain;

import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.jogador.domain.Jogador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventario_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventarioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantidade = 0;
}
