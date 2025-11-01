CREATE TABLE digimon (
    id SERIAL PRIMARY KEY,
    jogador_id INT NOT NULL REFERENCES jogador(id) ON DELETE CASCADE,
    nome VARCHAR(60) NOT NULL,
    especie VARCHAR(60) NOT NULL,
    nivel INT DEFAULT 1,
    experiencia INT DEFAULT 0,
    energia INT DEFAULT 100,
    power_total INT DEFAULT 0,
    bits INT DEFAULT 0,
    atk INT DEFAULT 0,
    def INT DEFAULT 0,
    spd INT DEFAULT 0,
    hp INT DEFAULT 0,
    ativo BOOLEAN DEFAULT FALSE
);
