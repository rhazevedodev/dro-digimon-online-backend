-- ===========================================
-- TABELA: EXPEDICAO_ATIVA
-- ===========================================
CREATE TABLE expedicao_ativa (
    id BIGSERIAL PRIMARY KEY,
    jogador_id BIGINT NOT NULL,
    expedicao_id BIGINT NOT NULL,
    dificuldade VARCHAR(30) NOT NULL, -- Enum DificuldadeExpedicao
    concluida BOOLEAN DEFAULT FALSE,
    inicio TIMESTAMP WITH TIME ZONE,
    fim TIMESTAMP WITH TIME ZONE,

    digimon_enviado_id BIGINT,

    CONSTRAINT fk_expativa_jogador FOREIGN KEY (jogador_id) REFERENCES jogador (id) ON DELETE CASCADE,
    CONSTRAINT fk_expativa_expedicao FOREIGN KEY (expedicao_id) REFERENCES expedicao (id) ON DELETE CASCADE,
    CONSTRAINT fk_expativa_digimon FOREIGN KEY (digimon_enviado_id) REFERENCES digimon (id)
);
