package br.com.digimon.core.digimon.service;

import br.com.digimon.core.digimon.domain.Digimon;
import br.com.digimon.core.jogador.repo.JogadorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DigimonService {

//    private final JogadorRepository jogadorRepository;
//
//    public Digimon criarPrimeiroDigimon(Long jogadorId, String nomeDigimon) {
//        var jogador = jogadorRepository.findById(jogadorId)
//                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
//
//        var digimon = Digimon.builder()
//                .nome(nomeDigimon)
//                .especie(nomeDigimon)
//                .nivel(1)
//                .bits(0)
//                .ativo(true) // primeiro digimon já é o parceiro
//                .jogador(jogador)
//                .build();
//
//        return digimonRepository.save(digimon);
//    }
}
