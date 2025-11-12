-- ===========================================================
-- Migração dos dados da tabela digimon para as novas tabelas normalizadas
-- ===========================================================

-- 🧱 Atributos básicos
INSERT INTO digimon_atributos_basicos (digimon_id, hp, atk, def, int_, spd)
SELECT id, hp, atk, def, int_, spd
FROM digimon
WHERE hp IS NOT NULL;

-- 💫 Atributos extras
INSERT INTO digimon_atributos_extras (digimon_id, crit_rate, crit_damage, accuracy, evade, bond)
SELECT id, crit_rate, crit_damage, accuracy, evade, bond
FROM digimon
WHERE crit_rate IS NOT NULL;

-- 🧬 IVs (valores individuais)
INSERT INTO digimon_iv (digimon_id, iv_hp, iv_atk, iv_def, iv_int, iv_spd)
SELECT id, iv_hp, iv_atk, iv_def, iv_int, iv_spd
FROM digimon
WHERE iv_hp IS NOT NULL;

-- ⚙️ EVs (valores de esforço)
INSERT INTO digimon_ev (digimon_id, ev_hp, ev_atk, ev_def, ev_int, ev_spd)
SELECT id, ev_hp, ev_atk, ev_def, ev_int, ev_spd
FROM digimon
WHERE ev_hp IS NOT NULL;

-- ===========================================================
-- Revalidação dos cálculos automáticos (iv_total e ev_total)
-- ===========================================================
-- Esses campos são gerados automaticamente (STORED), portanto
-- basta atualizar um campo dependente para forçar recalcular
-- em bancos que exigem reavaliação física de colunas geradas.

UPDATE digimon_iv SET iv_hp = iv_hp;
UPDATE digimon_ev SET ev_hp = ev_hp;

-- ===========================================================
-- Verificação (opcional)
-- SELECT COUNT(*) FROM digimon;
-- SELECT COUNT(*) FROM digimon_atributos_basicos;
-- SELECT COUNT(*) FROM digimon_atributos_extras;
-- SELECT COUNT(*) FROM digimon_iv;
-- SELECT COUNT(*) FROM digimon_ev;
-- Todos devem retornar a mesma contagem.
-- ===========================================================

-- Comentário:
-- Este script migra os dados de atributos da tabela digimon
-- para as novas tabelas normalizadas, garantindo que:
--   🧬 iv_total = média automática dos cinco IVs (NUMERIC)
--   ⚙️ ev_total = soma automática de todos os EVs (INT)
-- Nenhuma coluna antiga é removida ainda — isso será feito
-- em um próximo script (V18) após validação da migração.
-- ===========================================================
