package br.com.digimon.core.jogador.repo;

import br.com.digimon.core.jogador.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findByUsuarioUsername(String username);
}
