package br.com.digimon.core.item.repo;

import br.com.digimon.core.inventario.domain.InventarioItem;
import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.digimon.domain.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemInventarioRepository extends JpaRepository<InventarioItem, Long> {
    Optional<InventarioItem> findByDigimonAndItem(Digimon digimon, Item item);

    Optional<InventarioItem> findByDigimonIdAndItemId(Long digimonId, Long itemConsumivelId);

    boolean existsByDigimonIdAndItemId(Long digimonId, Long itemId);
}
