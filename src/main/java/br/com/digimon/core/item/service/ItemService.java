package br.com.digimon.core.item.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.domain.ItemInventario;
import br.com.digimon.core.item.domain.ItemLootPool;
import br.com.digimon.core.item.dto.ItemEnxutoDTO;
import br.com.digimon.core.item.dto.ItemRecompensaSimplesDTO;
import br.com.digimon.core.item.dto.ListaItemEnxutoDTO;
import br.com.digimon.core.item.dto.LootPossivelDTO;
import br.com.digimon.core.item.enumerator.TipoItem;
import br.com.digimon.core.item.repo.ItemInventarioRepository;
import br.com.digimon.core.item.repo.ItemLootPoolRepository;
import br.com.digimon.core.item.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final DigimonRepository digimonRepository;
    private final ItemInventarioRepository itemInventarioRepository;
    private final ItemLootPoolRepository itemLootPoolRepository;


    public void popularItens(List<Item> itens) {
        // evita duplicados baseados no nome
        itens.stream()
                .filter(item -> !itemRepository.existsByNome(item.getNome()))
                .forEach(itemRepository::save);
    }

    public List<Item> listarItens() {
        return itemRepository.findAll();
    }


    public ListaItemEnxutoDTO listarItensEnxuto() {
        var itens = itemRepository.findAll();

        var listaDTO = new ListaItemEnxutoDTO();
        listaDTO.setTotalItens(itens.size());
        listaDTO.setItens(
                itens.stream()
                        .map(item -> {
                            var dto = new ItemEnxutoDTO();
                            dto.setId(item.getId());
                            dto.setNome(item.getNome());
                            return dto;
                        })
                        .collect(Collectors.toList())
        );

        return listaDTO;
    }

    public List<Item> listarPorTipo(TipoItem tipo) {
        return itemRepository.findByTipo(tipo);
    }

    public List<ItemRecompensaSimplesDTO> abrirBau(Long digimonId, Long itemConsumivelId) {
        Digimon digimon = digimonRepository.findById(digimonId)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        Item itemConsumivel = itemRepository.findById(itemConsumivelId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        ItemInventario itemInventario = itemInventarioRepository
                .findByDigimonIdAndItemId(digimonId, itemConsumivelId)
                .orElseThrow(() -> new RuntimeException("O Digimon não possui este item para abrir."));

        if (itemInventario.getQuantidade() <= 0) {
            throw new RuntimeException("O Digimon não possui este item para abrir.");
        }

        List<ItemLootPool> lootEntries = itemLootPoolRepository.findByItemConsumivelId(itemConsumivelId);
        if (lootEntries.isEmpty()) {
            throw new RuntimeException("Nenhum loot configurado para este baú.");
        }

        List<ItemRecompensaSimplesDTO> recompensas = new ArrayList<>();

        for (ItemLootPool entry : lootEntries) {
            if (Math.random() <= entry.getChance()) {
                int quantidade = ThreadLocalRandom.current()
                        .nextInt(entry.getQuantidadeMinima(), entry.getQuantidadeMaxima() + 1);

                // Atualiza ou cria o item no inventário
                ItemInventario inventario = itemInventarioRepository
                        .findByDigimonIdAndItemId(digimonId, entry.getItemRecompensa().getId())
                        .orElseGet(() -> {
                            ItemInventario novo = new ItemInventario();
                            novo.setDigimon(digimon);
                            novo.setItem(entry.getItemRecompensa());
                            novo.setQuantidade(0);
                            return novo;
                        });

                inventario.setQuantidade(inventario.getQuantidade() + quantidade);
                itemInventarioRepository.save(inventario);

                // Adiciona apenas nome + quantidade à lista de resposta
                recompensas.add(
                        new ItemRecompensaSimplesDTO(entry.getItemRecompensa().getNome(), quantidade)
                );
            }
        }

        // Consome o baú (1 unidade)
        itemInventario.setQuantidade(itemInventario.getQuantidade() - 1);
        if (itemInventario.getQuantidade() <= 0) {
            itemInventarioRepository.delete(itemInventario);
        } else {
            itemInventarioRepository.save(itemInventario);
        }

        return recompensas;
    }

    public List<LootPossivelDTO> listarLootsPossiveis(Long itemConsumivelId) {
        // Busca todos os loots associados a esse item (baú)
        List<ItemLootPool> lootEntries = itemLootPoolRepository.findByItemConsumivelId(itemConsumivelId);

        if (lootEntries.isEmpty()) {
            throw new RuntimeException("Nenhum loot configurado para este baú.");
        }

        // Mapeia para DTO enxuto
        List<LootPossivelDTO> loots = new ArrayList<>();

        for (ItemLootPool entry : lootEntries) {
            LootPossivelDTO dto = LootPossivelDTO.builder()
                    .nome(entry.getItemRecompensa().getNome())
                    .quantidadeMinima(entry.getQuantidadeMinima())
                    .quantidadeMaxima(entry.getQuantidadeMaxima())
                    .chance(entry.getChance())
                    .build();

            loots.add(dto);
        }

        return loots;
    }
}
