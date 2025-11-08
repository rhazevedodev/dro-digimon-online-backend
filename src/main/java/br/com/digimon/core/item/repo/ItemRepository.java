package br.com.digimon.core.item.repo;

import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.enumerator.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNome(String nome);
    boolean existsByNome(String nome);

    List<Item> findByTipo(TipoItem tipo);
}