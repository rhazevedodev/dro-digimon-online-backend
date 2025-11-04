CREATE TABLE digimon_species (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    estagio VARCHAR(20) NOT NULL, -- Baby I, Baby II, Rookie, etc.
    tipo VARCHAR(20) NOT NULL,    -- VACCINE, VIRUS, DATA, UNKNOWN
    elemento VARCHAR(20) NOT NULL, -- FIRE, WATER, WIND, etc.

    base_hp INT NOT NULL,
    base_atk INT NOT NULL,
    base_def INT NOT NULL,
    base_int INT NOT NULL,
    base_spd INT NOT NULL
);

-- 🔹 Seeds iniciais (Baby I)
INSERT INTO digimon_species (nome, estagio, tipo, elemento, base_hp, base_atk, base_def, base_int, base_spd) VALUES
('Botamon', 'Baby I', 'DATA', 'NEUTRAL', 10, 8, 8, 5, 4),
('Punimon', 'Baby I', 'VACCINE', 'WATER', 9, 7, 9, 5, 6),
('Yuramon', 'Baby I', 'DATA', 'WOOD', 8, 6, 7, 8, 6),
('Pabumon', 'Baby I', 'VACCINE', 'WIND', 8, 7, 8, 7, 5),
('Pichimon', 'Baby I', 'DATA', 'WATER', 7, 5, 6, 9, 8),
('Poyomon', 'Baby I', 'DATA', 'LIGHT', 7, 5, 5, 10, 7);
