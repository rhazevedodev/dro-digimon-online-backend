CREATE TABLE item_loot_pool (
    id BIGSERIAL PRIMARY KEY,
    item_consumivel_id BIGINT NOT NULL,
    item_recompensa_id BIGINT NOT NULL,
    quantidade_minima INT NOT NULL DEFAULT 1,
    quantidade_maxima INT NOT NULL DEFAULT 1,
    chance DOUBLE PRECISION NOT NULL DEFAULT 1.0,

    CONSTRAINT fk_loot_consumivel FOREIGN KEY (item_consumivel_id)
        REFERENCES item (id) ON DELETE CASCADE,

    CONSTRAINT fk_loot_recompensa FOREIGN KEY (item_recompensa_id)
        REFERENCES item (id) ON DELETE CASCADE,

    CONSTRAINT ck_loot_chance_range CHECK (chance >= 0.0 AND chance <= 1.0)
);