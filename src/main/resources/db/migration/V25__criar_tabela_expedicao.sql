-- ===========================================
-- TABELA: EXPEDICAO
-- ===========================================
CREATE TABLE expedicao (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    desbloqueada_padrao BOOLEAN DEFAULT FALSE,

    item_requerido_id BIGINT,
    item_recompensa_id BIGINT,

    CONSTRAINT fk_expedicao_item_req FOREIGN KEY (item_requerido_id) REFERENCES item (id),
    CONSTRAINT fk_expedicao_item_recomp FOREIGN KEY (item_recompensa_id) REFERENCES item (id)
);
