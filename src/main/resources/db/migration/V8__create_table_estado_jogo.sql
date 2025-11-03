CREATE TABLE estado_jogo (
    id BIGSERIAL PRIMARY KEY,
    jogador_id BIGINT NOT NULL UNIQUE REFERENCES jogador(id) ON DELETE CASCADE,
    digitama_selecionada BOOLEAN DEFAULT FALSE,
    digitama_id_selecionada BIGINT,
    digitama_chocada BOOLEAN DEFAULT FALSE,
    ultimo_update TIMESTAMP DEFAULT NOW()
);
