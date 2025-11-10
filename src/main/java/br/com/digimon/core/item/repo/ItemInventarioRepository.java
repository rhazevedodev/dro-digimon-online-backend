package br.com.digimon.core.item.repo;

import br.com.digimon.core.item.domain.ItemInventario;
import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.digimon.domain.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemInventarioRepository extends JpaRepository<ItemInventario, Long> {
    Optional<ItemInventario> findByDigimonAndItem(Digimon digimon, Item item);

    Optional<ItemInventario> findByDigimonIdAndItemId(Long digimonId, Long itemConsumivelId);
}
