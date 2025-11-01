CREATE TABLE jogador (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuario(id) ON DELETE CASCADE,
    pontos_digitais INT DEFAULT 0
);
