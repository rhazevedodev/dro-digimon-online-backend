-- ===========================================================
-- Migration: V8__alter_chance_to_double_precision.sql
-- Objetivo: Corrigir o tipo da coluna "chance" de NUMERIC(5,4)
--            para DOUBLE PRECISION, compatível com o atributo
--            Java "double chance" em ExpedicaoItemRecompensa.
-- ===========================================================

-- 🔹 Converte a coluna sem perder dados existentes
ALTER TABLE expedicao_item_recompensa
    ALTER COLUMN chance TYPE DOUBLE PRECISION
    USING chance::double precision;

-- 🔹 Garante que o valor padrão e a constraint sejam mantidos
ALTER TABLE expedicao_item_recompensa
    ALTER COLUMN chance SET DEFAULT 1.0;

-- 🔹 (Opcional, mas recomendado) Recria a constraint de faixa válida
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'ck_chance_range'
    ) THEN
        ALTER TABLE expedicao_item_recompensa
        ADD CONSTRAINT ck_chance_range CHECK (chance >= 0.0 AND chance <= 1.0);
    END IF;
END$$;
