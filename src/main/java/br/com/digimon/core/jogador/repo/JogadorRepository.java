package br.com.digimon.core.jogador.repo;

import br.com.digimon.core.jogador.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
