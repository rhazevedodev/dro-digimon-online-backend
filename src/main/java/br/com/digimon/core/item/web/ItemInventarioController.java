package br.com.digimon.core.item.web;

import br.com.digimon.core.item.dto.AdicionarItemInventarioDTO;
import br.com.digimon.core.item.dto.InventarioDigimonDTO;
import br.com.digimon.core.item.service.ItemInventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
@RequiredArgsConstructor
public class ItemInventarioController {

    private final ItemInventarioService itemInventarioService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarItem(@RequestBody AdicionarItemInventarioDTO dto) {
        itemInventarioService.adicionarItem(dto);
        return ResponseEntity.ok("Item adicionado com sucesso ao inventário!");
    }

    @GetMapping("/{idDigimon}")
    public ResponseEntity<InventarioDigimonDTO> listarInventario(@PathVariable Long idDigimon) {
        return ResponseEntity.ok(itemInventarioService.listarInventario(idDigimon));
    }
}
