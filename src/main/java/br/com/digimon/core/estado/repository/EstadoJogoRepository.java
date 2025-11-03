package br.com.digimon.core.estado.repository;

import br.com.digimon.core.estado.domain.EstadoJogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoJogoRepository extends JpaRepository<EstadoJogo, Long> {
    Optional<EstadoJogo> findByJogadorId(Long jogadorId);
}
