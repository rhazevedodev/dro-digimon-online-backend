-- ===========================================================
-- Corrige constraints para permitir valores 0.00 em atributos extras
-- ===========================================================

ALTER TABLE digimon_atributos_extras
DROP CONSTRAINT IF EXISTS digimon_atributos_extras_crit_damage_check,
DROP CONSTRAINT IF EXISTS digimon_atributos_extras_crit_rate_check,
DROP CONSTRAINT IF EXISTS digimon_atributos_extras_accuracy_check,
DROP CONSTRAINT IF EXISTS digimon_atributos_extras_evade_check;

ALTER TABLE digimon_atributos_extras
ADD CONSTRAINT digimon_atributos_extras_crit_damage_check CHECK (crit_damage >= 0),
ADD CONSTRAINT digimon_atributos_extras_crit_rate_check CHECK (crit_rate >= 0),
ADD CONSTRAINT digimon_atributos_extras_accuracy_check CHECK (accuracy >= 0),
ADD CONSTRAINT digimon_atributos_extras_evade_check CHECK (evade >= 0);
--- Depois basta reiniciar o container da aplicação para o Flyway aplicar automaticamente: