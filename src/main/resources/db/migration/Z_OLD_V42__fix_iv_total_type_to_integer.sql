-- ===========================================================
-- Corrige o tipo da coluna iv_total para INTEGER
-- compatível com o tipo int usado no Java
-- ===========================================================

ALTER TABLE digimon_iv
ALTER COLUMN iv_total TYPE INTEGER
USING ROUND(iv_total)::INTEGER;
