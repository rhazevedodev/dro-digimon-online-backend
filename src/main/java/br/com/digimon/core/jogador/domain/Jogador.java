package br.com.digimon.core.jogador.domain;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.usuario.domain.Usuario;
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
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "pontos_digitais", nullable = false)
    private int pontosDigitais = 0;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Digimon> digimons = new ArrayList<>();

    public Optional<Digimon> getDigimonParceiro() {
        return digimons.stream().filter(Digimon::isAtivo).findFirst();
    }
}
