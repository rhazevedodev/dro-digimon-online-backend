package br.com.digimon.core.expedicao.domain;

import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
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
    @JoinColumn(name = "expedicao_id")
    @JsonBackReference("expedicao-dificuldades")
    private Expedicao expedicao;

    @Enumerated(EnumType.STRING)
    private DificuldadeExpedicao dificuldade; // FÁCIL, MÉDIA, DIFÍCIL, EXTREMA

    private int poderMinimo;
    private int duracaoHoras;

    @OneToMany(mappedBy = "dificuldade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("dificuldade-recompensas")
    private List<ExpedicaoItemRecompensa> recompensas;
}