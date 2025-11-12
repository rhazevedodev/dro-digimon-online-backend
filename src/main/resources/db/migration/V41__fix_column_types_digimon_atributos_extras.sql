-- ===========================================================
-- Corrige os tipos de coluna da tabela digimon_atributos_extras
-- para usar DOUBLE PRECISION (compatível com double do Java)
-- ===========================================================

ALTER TABLE digimon_atributos_extras
ALTER COLUMN crit_rate TYPE DOUBLE PRECISION USING crit_rate::double precision,
ALTER COLUMN crit_damage TYPE DOUBLE PRECISION USING crit_damage::double precision,
ALTER COLUMN accuracy TYPE DOUBLE PRECISION USING accuracy::double precision,
ALTER COLUMN evade TYPE DOUBLE PRECISION USING evade::double precision;