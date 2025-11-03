package br.com.digimon.core.digitama.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "digitamas")
public class DigitamaProperties {

    private List<DigitamaConfig> list;

    @Data
    public static class DigitamaConfig {
        private Long id;
        private String nome;
        private String imagem;
        private List<String> possiveis;
    }
}
