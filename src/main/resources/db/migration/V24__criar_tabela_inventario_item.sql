-- ===========================================
-- TABELA: INVENTARIO_ITEM
-- ===========================================
CREATE TABLE inventario_item (
    id BIGSERIAL PRIMARY KEY,
    jogador_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantidade INT DEFAULT 0,

    CONSTRAINT fk_inventario_jogador FOREIGN KEY (jogador_id) REFERENCES jogador (id) ON DELETE CASCADE,
    CONSTRAINT fk_inventario_item FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);

-- Garante que um jogador não tenha o mesmo item repetido
ALTER TABLE inventario_item
    ADD CONSTRAINT unique_item_por_jogador UNIQUE (jogador_id, item_id);
