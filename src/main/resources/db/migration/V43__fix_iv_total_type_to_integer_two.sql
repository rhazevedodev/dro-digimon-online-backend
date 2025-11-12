-- ===========================================================
-- Corrige o tipo da coluna iv_total para INTEGER
-- compatível com o tipo int usado no Java
-- ===========================================================

ALTER TABLE digimon_iv
DROP COLUMN IF EXISTS iv_total;

ALTER TABLE digimon_iv
ADD COLUMN iv_total INTEGER GENERATED ALWAYS AS (
    ROUND((iv_hp + iv_atk + iv_def + iv_int + iv_spd) / 5.0)
) STORED;

-- ===========================================================
-- Comentário:
-- PostgreSQL não permite ALTER TYPE em colunas geradas.
-- A única forma segura é remover e recriar a coluna.
-- ===========================================================
