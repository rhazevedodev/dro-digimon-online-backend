package br.com.digimon.core.expedicao.domain;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.expedicao.enumerator.DificuldadeExpedicao;
import br.com.digimon.core.jogador.domain.Jogador;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "expedicao_ativa")
public class ExpedicaoAtiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "digimon_id")
    private Digimon digimon;

    @ManyToOne(optional = false)
    @JoinColumn(name = "expedicao_id")
    private Expedicao expedicao;

    @Enumerated(EnumType.STRING)
    private DificuldadeExpedicao dificuldade;

    private boolean concluida = false;

    private Instant inicio;
    private Instant fim;
}
