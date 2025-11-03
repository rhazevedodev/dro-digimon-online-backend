package br.com.digimon.core.jogador.domain;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.estado.domain.EstadoJogo;
import br.com.digimon.core.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonManagedReference
    private Usuario usuario;

    @Column(name = "pontos_digitais", nullable = false)
    private int pontosDigitais = 0;

    @OneToOne(mappedBy = "jogador", cascade = CascadeType.ALL)
    @JsonManagedReference
    private EstadoJogo estado;

    @Column(name = "primeiro_acesso", nullable = false)
    private boolean primeiroAcesso = false;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Digimon> digimons = new ArrayList<>();

    public Optional<Digimon> getDigimonParceiro() {
        return digimons.stream().filter(Digimon::isAtivo).findFirst();
    }
}
