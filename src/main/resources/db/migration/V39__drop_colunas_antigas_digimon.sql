-- ===========================================================
-- Limpeza das colunas antigas da tabela digimon
-- ===========================================================
-- ⚠️ AVISO IMPORTANTE:
-- Execute este script SOMENTE após verificar que os dados foram
-- migrados corretamente para as tabelas normalizadas:
--   - digimon_atributos_basicos
--   - digimon_atributos_extras
--   - digimon_iv
--   - digimon_ev
-- ===========================================================

ALTER TABLE digimon
DROP COLUMN IF EXISTS hp,
DROP COLUMN IF EXISTS atk,
DROP COLUMN IF EXISTS def,
DROP COLUMN IF EXISTS int_,
DROP COLUMN IF EXISTS spd,

DROP COLUMN IF EXISTS crit_rate,
DROP COLUMN IF EXISTS crit_damage,
DROP COLUMN IF EXISTS accuracy,
DROP COLUMN IF EXISTS evade,
DROP COLUMN IF EXISTS bond,

DROP COLUMN IF EXISTS iv_hp,
DROP COLUMN IF EXISTS iv_atk,
DROP COLUMN IF EXISTS iv_def,
DROP COLUMN IF EXISTS iv_int,
DROP COLUMN IF EXISTS iv_spd,
DROP COLUMN IF EXISTS iv_total,

DROP COLUMN IF EXISTS ev_hp,
DROP COLUMN IF EXISTS ev_atk,
DROP COLUMN IF EXISTS ev_def,
DROP COLUMN IF EXISTS ev_int,
DROP COLUMN IF EXISTS ev_spd,
DROP COLUMN IF EXISTS ev_total;

-- ===========================================================
-- Comentário:
-- Este script remove definitivamente as colunas de atributos
-- que foram migradas para as tabelas normalizadas.
--
-- Nova estrutura:
--   🧱 digimon_atributos_basicos  → hp, atk, def, int_, spd
--   💫 digimon_atributos_extras   → crit_rate, accuracy, evade, bond
--   🧬 digimon_iv                 → iv_hp...iv_spd, iv_total (média)
--   ⚙️ digimon_ev                 → ev_hp...ev_spd, ev_total (soma)
-- ===========================================================
