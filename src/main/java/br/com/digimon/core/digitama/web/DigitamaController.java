package br.com.digimon.core.digitama.web;

import br.com.digimon.core.digitama.config.DigitamaProperties;
import br.com.digimon.core.digitama.domain.Digitama;
import br.com.digimon.core.digitama.dto.ChocarDigitamaRequestDTO;
import br.com.digimon.core.digitama.dto.ChocarDigitamaResponseDTO;
import br.com.digimon.core.digitama.dto.DigitamaSelecaoResponseDTO;
import br.com.digimon.core.digitama.service.DigitamaService;
import br.com.digimon.core.estado.service.EstadoJogoService;
import br.com.digimon.core.jogador.domain.Jogador;
import br.com.digimon.core.jogador.service.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/digitamas")
@RequiredArgsConstructor
public class DigitamaController {

    private final DigitamaService digitamaService;
    private final EstadoJogoService estadoJogoService;
    private final JogadorService jogadorService;
    private final DigitamaProperties digitamaProperties;

    @GetMapping
    public ResponseEntity<List<Digitama>> listar() {
        return ResponseEntity.ok(digitamaService.listarDigitamas());
    }

    @PostMapping("/selecionar")
    public ResponseEntity<DigitamaSelecaoResponseDTO> selecionarDigitama(
            @RequestBody Map<String, Long> body,
            Authentication auth) {

        Long digitamaId = body.get("digitamaId");
        Jogador jogador = jogadorService.buscarPorUsername(auth.getName());

        estadoJogoService.marcarDigitamaSelecionada(jogador, digitamaId);

        // Busca detalhes da Digitama escolhida nas configurações
        var config = digitamaProperties.getList().stream()
                .filter(d -> d.getId().equals(digitamaId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Digitama não encontrada nas configurações."));

        var resposta = new DigitamaSelecaoResponseDTO(
                "Digitama selecionada com sucesso!",
                new DigitamaSelecaoResponseDTO.DigitamaInfo(
                        config.getId(),
                        config.getNome(),
                        config.getImagem()
                ),
                new DigitamaSelecaoResponseDTO.JogadorInfo(
                        jogador.getId(),
                        true,
                        digitamaId
                )
        );

        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/chocar")
    public ResponseEntity<ChocarDigitamaResponseDTO> chocarDigitama(
            @RequestBody ChocarDigitamaRequestDTO dto,
            Authentication auth) {
        ChocarDigitamaResponseDTO digimonChocado = digitamaService.chocarDigitama(dto, auth.getName());
        return ResponseEntity.ok(digimonChocado);
    }

    @PostMapping("/iniciar-selecao-slot")
    public ResponseEntity<Void> iniciarSelecaoDigitamaSlot(Authentication auth) {
        Jogador jogador = jogadorService.buscarPorUsername(auth.getName());
        estadoJogoService.iniciarSelecaoDigitamaParaSlot(jogador);
        return ResponseEntity.ok().build();
    }
}
