package br.com.digimon.core.item.service;

import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.enumerator.TipoItem;
import br.com.digimon.core.item.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void popularItens(List<Item> itens) {
        // evita duplicados baseados no nome
        itens.stream()
                .filter(item -> !itemRepository.existsByNome(item.getNome()))
                .forEach(itemRepository::save);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    public List<Item> listarPorTipo(TipoItem tipo) {
        return itemRepository.findByTipo(tipo);
    }
}
