package br.com.digimon.core.expedicao.domain;

import br.com.digimon.core.item.domain.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expedicao_item_recompensa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpedicaoItemRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expedicao_dificuldade_id")
    @JsonBackReference("dificuldade-recompensas")
    private ExpedicaoDificuldade dificuldade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @JsonManagedReference("recompensa-item")
    private Item item;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double chance; // chance de drop (0.0 a 1.0)
}
