package br.com.digimon.core.estado.service;

import br.com.digimon.core.estado.domain.EstadoJogo;
import br.com.digimon.core.estado.repository.EstadoJogoRepository;
import br.com.digimon.core.jogador.domain.Jogador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoJogoService {

    private final EstadoJogoRepository estadoRepo;

    public EstadoJogo getOuCriarEstado(Jogador jogador) {
        return estadoRepo.findByJogadorId(jogador.getId())
                .orElseGet(() -> {
                    EstadoJogo novo = new EstadoJogo();
                    novo.setJogador(jogador);
                    return estadoRepo.save(novo);
                });
    }

    public void marcarDigitamaSelecionada(Jogador jogador, Long digitamaId) {
        EstadoJogo estado = getOuCriarEstado(jogador);

        if (estado.getDigitamaSelecionada()) {
            throw new IllegalStateException("O jogador já possui uma Digitama selecionada.");
        }
        estado.setDigitamaSelecionada(true);
        estado.setDigitamaIdSelecionada(digitamaId);
        estado.setDigitamaChocada(false);
        estadoRepo.save(estado);
    }

    public void marcarDigitamaChocada(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);
        estado.setDigitamaChocada(true);
        estadoRepo.save(estado);
    }

    public void resetarEstado(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);
        estado.setDigitamaSelecionada(false);
        estado.setDigitamaChocada(false);
        estado.setDigitamaIdSelecionada(null);
        estadoRepo.save(estado);
    }
}
