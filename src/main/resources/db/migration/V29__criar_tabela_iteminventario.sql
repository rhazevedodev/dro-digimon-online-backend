CREATE TABLE item_inventario (
    id BIGSERIAL PRIMARY KEY,
    digimon_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,

    CONSTRAINT fk_item_inventario_digimon
        FOREIGN KEY (digimon_id)
        REFERENCES digimon (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_item_inventario_item
        FOREIGN KEY (item_id)
        REFERENCES item (id)
        ON DELETE CASCADE,

    CONSTRAINT uq_item_inventario_digimon_item UNIQUE (digimon_id, item_id),

    CONSTRAINT ck_item_inventario_quantidade CHECK (quantidade >= 0)
);
