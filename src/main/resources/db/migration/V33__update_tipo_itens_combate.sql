-- ===========================================================
-- Atualiza o tipo dos itens de ID 34 a 55 para 'COMBATE'
-- ===========================================================

UPDATE item
SET tipo = 'COMBATE'
WHERE id BETWEEN 34 AND 55;