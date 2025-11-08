-- ===========================================================
-- População inicial da tabela expedicao_item_recompensa
-- ===========================================================
-- Usa apenas o item "Fragmento DNA Agumon" (id = 1)
-- ===========================================================

-- Floresta Misteriosa - FÁCIL
INSERT INTO expedicao_item_recompensa (expedicao_dificuldade_id, item_id, quantidade, chance)
VALUES
(1, 1, 1, 0.6);  -- 60% de chance de 1 fragmento

-- Floresta Misteriosa - MÉDIA
INSERT INTO expedicao_item_recompensa (expedicao_dificuldade_id, item_id, quantidade, chance)
VALUES
(2, 1, 2, 0.8);  -- 80% de chance de 2 fragmentos

-- Floresta Misteriosa - DIFÍCIL
INSERT INTO expedicao_item_recompensa (expedicao_dificuldade_id, item_id, quantidade, chance)
VALUES
(3, 1, 3, 1.0);  -- 100% de chance de 3 fragmentos

-- Floresta Misteriosa - EXTREMA
INSERT INTO expedicao_item_recompensa (expedicao_dificuldade_id, item_id, quantidade, chance)
VALUES
(4, 1, 5, 1.0);  -- 100% de chance de 5 fragmentos
