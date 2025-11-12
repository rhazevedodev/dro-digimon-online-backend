package br.com.digimon.core.expedicao.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.digimon.core.item.domain.Item;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expedicao")
@Data
public class Expedicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private boolean desbloqueadaPadrao = false;

    @OneToMany(mappedBy = "expedicao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("expedicao-dificuldades")
    private List<ExpedicaoDificuldade> dificuldades;

    @ManyToOne
    @JoinColumn(name = "item_requerido_id")
    private Item itemRequerido;
}
