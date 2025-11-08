package br.com.digimon.core.item.web;

import br.com.digimon.core.item.domain.Item;
import br.com.digimon.core.item.enumerator.TipoItem;
import br.com.digimon.core.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 🧩 1. Popular itens via JSON externo
    @PostMapping("/popular")
    public ResponseEntity<String> popularItens(@RequestBody List<Item> itens) {
        itemService.popularItens(itens);
        return ResponseEntity.ok("Itens processados com sucesso: " + itens.size());
    }

    // 📜 2. Listar todos os itens
    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        return ResponseEntity.ok(itemService.listarItens());
    }

    // 🎯 3. Listar itens filtrados por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Item>> listarPorTipo(@PathVariable("tipo") TipoItem tipo) {
        return ResponseEntity.ok(itemService.listarPorTipo(tipo));
    }

    // 🧾 4. Listar todos os tipos possíveis de itens (enum)
    @GetMapping("/tipos")
    public ResponseEntity<List<TipoItem>> listarTipos() {
        return ResponseEntity.ok(Arrays.asList(TipoItem.values()));
    }
}
