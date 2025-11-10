package br.com.digimon.core.item.repo;

import br.com.digimon.core.item.domain.ItemLootPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemLootPoolRepository extends JpaRepository<ItemLootPool, Long> {
    List<ItemLootPool> findByItemConsumivelId(Long itemConsumivelId);
}
