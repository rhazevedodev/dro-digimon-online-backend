CREATE TABLE expedicao_item_recompensa (
    id BIGSERIAL PRIMARY KEY,
    expedicao_dificuldade_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    chance NUMERIC(5,4) NOT NULL DEFAULT 1.0,

    CONSTRAINT fk_recompensa_dificuldade
        FOREIGN KEY (expedicao_dificuldade_id)
        REFERENCES expedicao_dificuldade (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_recompensa_item
        FOREIGN KEY (item_id)
        REFERENCES item (id)
        ON DELETE CASCADE,

    CONSTRAINT ck_chance_range CHECK (chance >= 0.0 AND chance <= 1.0)
);
