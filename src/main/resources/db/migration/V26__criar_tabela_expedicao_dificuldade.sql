-- ===========================================
-- TABELA: EXPEDICAO_DIFICULDADE
-- ===========================================
CREATE TABLE expedicao_dificuldade (
    id BIGSERIAL PRIMARY KEY,
    expedicao_id BIGINT NOT NULL,
    dificuldade VARCHAR(30) NOT NULL, -- FACIL, MEDIA, DIFICIL, EXTREMA
    poder_minimo INT DEFAULT 0,
    duracao_horas INT DEFAULT 1,
    recompensas TEXT, -- pode armazenar JSON futuramente

    CONSTRAINT fk_expdif_expedicao FOREIGN KEY (expedicao_id) REFERENCES expedicao (id) ON DELETE CASCADE
);
