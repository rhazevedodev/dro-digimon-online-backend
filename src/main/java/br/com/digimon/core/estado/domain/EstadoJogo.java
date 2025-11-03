package br.com.digimon.core.estado.domain;

import br.com.digimon.core.jogador.domain.Jogador;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "estado_jogo")
public class EstadoJogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "jogador_id", nullable = false, unique = true)
    @JsonBackReference
    private Jogador jogador;

    private Boolean digitamaSelecionada = false;
    private Long digitamaIdSelecionada;
    private Boolean digitamaChocada = false;

    @UpdateTimestamp
    private LocalDateTime ultimoUpdate;
}
