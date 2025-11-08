package br.com.digimon.core.expedicao.domain;

import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expedicao_dificuldade")
@Data
public class ExpedicaoDificuldade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expedicao_id")
    private Expedicao expedicao;

    @Enumerated(EnumType.STRING)
    private DificuldadeExpedicao dificuldade; // FÁCIL, MÉDIA, DIFÍCIL, EXTREMA

    private int poderMinimo;
    private int duracaoHoras;
    private String recompensas; // JSON string ou mapeado em tabela própria
}