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

    // ==========================================================
    // 🔹 Busca ou cria automaticamente o estado do jogador
    // ==========================================================
    public EstadoJogo getOuCriarEstado(Jogador jogador) {
        return estadoRepo.findByJogadorId(jogador.getId())
                .orElseGet(() -> {
                    EstadoJogo novo = new EstadoJogo();
                    novo.setJogador(jogador);
                    novo.setDigitamaSelecionada(false);
                    novo.setDigitamaChocada(false);
                    novo.setSelecaoDigitamaParaSlot(false);
                    return estadoRepo.save(novo);
                });
    }

    // ==========================================================
    // 🔹 TUTORIAL: marcar Digitama selecionada
    // ==========================================================
    public void marcarDigitamaSelecionada(Jogador jogador, Long digitamaId) {
        EstadoJogo estado = getOuCriarEstado(jogador);

        if (Boolean.FALSE.equals(estado.getDigitamaSelecionada())) {
            throw new IllegalStateException("O jogador já possui uma Digitama selecionada.");
        }

        estado.setDigitamaSelecionada(true);
        estado.setDigitamaIdSelecionada(digitamaId);
        estado.setDigitamaChocada(false);
        estadoRepo.save(estado);
    }

    // ==========================================================
    // 🔹 TUTORIAL: marcar Digitama chocada
    // ==========================================================
    public void marcarDigitamaChocada(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);
        estado.setDigitamaChocada(true);
        estadoRepo.save(estado);
    }

    // ==========================================================
    // 🔹 Fluxo Jornada: Iniciar escolha de Digitama para preencher slot
    // ==========================================================
    public void iniciarSelecaoDigitamaParaSlot(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);

        // marca que agora NÃO estamos mais no fluxo do tutorial
        estado.setSelecaoDigitamaParaSlot(true);

        // muito importante: não alterar digitamaSelecionada/digitamaChocada
        // apenas a interpretação muda no EstadoJogoDTO

        estadoRepo.save(estado);
    }

    // ==========================================================
    // 🔹 Finalizar choque da Digitama para slot extra
    // ==========================================================
    public void finalizarSelecaoDigitamaParaSlot(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);

        estado.setSelecaoDigitamaParaSlot(false);

        // não reseta nada do tutorial, apenas troca o modo
        estadoRepo.save(estado);
    }

    // ==========================================================
    // 🔹 Resetar estado completo do tutorial (opcional)
    // ==========================================================
    public void resetarEstado(Jogador jogador) {
        EstadoJogo estado = getOuCriarEstado(jogador);
        estado.setDigitamaSelecionada(false);
        estado.setDigitamaChocada(false);
        estado.setDigitamaIdSelecionada(null);
        estado.setSelecaoDigitamaParaSlot(false);
        estadoRepo.save(estado);
    }
}
