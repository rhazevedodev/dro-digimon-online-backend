CREATE TABLE jogador (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL UNIQUE REFERENCES usuario(id) ON DELETE CASCADE,
    pontos_digitais INT DEFAULT 0
);
