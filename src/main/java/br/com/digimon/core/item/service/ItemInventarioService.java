package br.com.digimon.core.item.service;

import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.domain.ItemInventario;
import br.com.digimon.core.item.dto.AdicionarItemInventarioDTO;
import br.com.digimon.core.item.dto.InventarioDigimonDTO;
import br.com.digimon.core.item.repo.ItemInventarioRepository;
import br.com.digimon.core.item.repo.ItemRepository;
import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.digimon.repository.DigimonRepository;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemInventarioService {

    private final ItemInventarioRepository itemInventarioRepository;
    private final ItemRepository itemRepository;
    private final DigimonRepository digimonRepository;
    private final JogadorRepository jogadorRepository;

    public void adicionarItem(AdicionarItemInventarioDTO dto) {
        var jogador = jogadorRepository.findById(dto.getIdJogador())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        var digimon = digimonRepository.findById(dto.getIdDigimon())
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        // valida se o digimon pertence ao jogador
        if (!digimon.getJogador().getId().equals(jogador.getId())) {
            throw new RuntimeException("Este Digimon não pertence ao jogador informado");
        }

        var item = itemRepository.findById(dto.getIdItem())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        var existente = itemInventarioRepository.findByDigimonAndItem(digimon, item);

        if (existente.isPresent()) {
            var inv = existente.get();
            inv.setQuantidade(inv.getQuantidade() + dto.getQuantidade());
            itemInventarioRepository.save(inv);
        } else {
            var novo = ItemInventario.builder()
                    .digimon(digimon)
                    .item(item)
                    .quantidade(dto.getQuantidade())
                    .build();
            itemInventarioRepository.save(novo);
        }
    }

    public InventarioDigimonDTO listarInventario(Long idDigimon) {
        Digimon digimon = digimonRepository.findById(idDigimon)
                .orElseThrow(() -> new RuntimeException("Digimon não encontrado"));

        var itens = itemInventarioRepository.findAll().stream()
                .filter(ii -> ii.getDigimon().getId().equals(idDigimon))
                .map(InventarioDigimonDTO.ItemInventarioDTO::fromEntity)
                .collect(Collectors.toList());

        return InventarioDigimonDTO.builder()
                .digimonId(digimon.getId())
                .digimonNome(digimon.getNome())
                .itens(itens)
                .build();
    }
}
