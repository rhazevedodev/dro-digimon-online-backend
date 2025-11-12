-- ===========================================================
-- Criação das tabelas normalizadas de atributos de Digimon
-- ===========================================================

-- 🧱 Atributos básicos: HP, ATK, DEF, INT, SPD
CREATE TABLE digimon_atributos_basicos (
    digimon_id BIGINT PRIMARY KEY REFERENCES digimon(id) ON DELETE CASCADE,
    hp INT DEFAULT 0 CHECK (hp >= 0),
    atk INT DEFAULT 0 CHECK (atk >= 0),
    def INT DEFAULT 0 CHECK (def >= 0),
    int_ INT DEFAULT 0 CHECK (int_ >= 0),
    spd INT DEFAULT 0 CHECK (spd >= 0)
);

-- 💫 Atributos extras: críticos, precisão, evasão, afinidade
CREATE TABLE digimon_atributos_extras (
    digimon_id BIGINT PRIMARY KEY REFERENCES digimon(id) ON DELETE CASCADE,
    crit_rate DECIMAL(5,2) DEFAULT 0.00 CHECK (crit_rate >= 0 AND crit_rate <= 1),
    crit_damage DECIMAL(5,2) DEFAULT 1.00 CHECK (crit_damage >= 1),
    accuracy DECIMAL(5,2) DEFAULT 1.00 CHECK (accuracy >= 0 AND accuracy <= 1),
    evade DECIMAL(5,2) DEFAULT 0.00 CHECK (evade >= 0 AND evade <= 1),
    bond INT DEFAULT 0 CHECK (bond >= 0)
);

-- 🧬 IVs (valores individuais)
CREATE TABLE digimon_iv (
    digimon_id BIGINT PRIMARY KEY REFERENCES digimon(id) ON DELETE CASCADE,

    iv_hp  INT DEFAULT 1 CHECK (iv_hp  >= 1 AND iv_hp  <= 100),
    iv_atk INT DEFAULT 1 CHECK (iv_atk >= 1 AND iv_atk <= 100),
    iv_def INT DEFAULT 1 CHECK (iv_def >= 1 AND iv_def <= 100),
    iv_int INT DEFAULT 1 CHECK (iv_int >= 1 AND iv_int <= 100),
    iv_spd INT DEFAULT 1 CHECK (iv_spd >= 1 AND iv_spd <= 100),

    iv_total NUMERIC(10,2) GENERATED ALWAYS AS (
        ROUND((iv_hp + iv_atk + iv_def + iv_int + iv_spd) / 5.0, 2)
    ) STORED
);

-- ⚙️ EVs (valores de esforço)
CREATE TABLE digimon_ev (
    digimon_id BIGINT PRIMARY KEY REFERENCES digimon(id) ON DELETE CASCADE,

    ev_hp  INT DEFAULT 0 CHECK (ev_hp  >= 0 AND ev_hp  <= 252),
    ev_atk INT DEFAULT 0 CHECK (ev_atk >= 0 AND ev_atk <= 252),
    ev_def INT DEFAULT 0 CHECK (ev_def >= 0 AND ev_def <= 252),
    ev_int INT DEFAULT 0 CHECK (ev_int >= 0 AND ev_int <= 252),
    ev_spd INT DEFAULT 0 CHECK (ev_spd >= 0 AND ev_spd <= 252),

    ev_total INT GENERATED ALWAYS AS (
        ev_hp + ev_atk + ev_def + ev_int + ev_spd
    ) STORED
);

-- ===========================================================
-- Índices para performance e consistência
-- ===========================================================
CREATE INDEX idx_digimon_basicos_id ON digimon_atributos_basicos(digimon_id);
CREATE INDEX idx_digimon_extras_id ON digimon_atributos_extras(digimon_id);
CREATE INDEX idx_digimon_iv_id ON digimon_iv(digimon_id);
CREATE INDEX idx_digimon_ev_id ON digimon_ev(digimon_id);

-- ===========================================================
-- Comentário:
-- Agora os atributos estão normalizados e protegidos por CHECKs.
--
-- 🧬 IVs (1–100) → representam o potencial genético do Digimon.
-- ⚙️ EVs (0–252) → representam o esforço obtido via treino.
-- 🧱 Atributos básicos ≥ 0 → evitam status negativos.
-- 💫 Extras entre 0 e 1 → representam multiplicadores ou chances.
-- ===========================================================
