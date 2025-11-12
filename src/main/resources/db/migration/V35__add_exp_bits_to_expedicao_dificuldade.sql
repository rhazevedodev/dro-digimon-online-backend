-- ===========================================================
-- Refatoração das colunas de recompensas nas tabelas de expedição
-- ===========================================================

-- 1️⃣ Remover a FK e coluna antiga de item_recompensa da tabela expedicao
ALTER TABLE expedicao
DROP CONSTRAINT IF EXISTS fk_expedicao_item_recompensa,
DROP COLUMN IF EXISTS item_recompensa_id;

-- 2️⃣ Remover a tabela antiga de recompensas específicas (agora obsoleta)
DROP TABLE IF EXISTS expedicao_item_recompensa CASCADE;

-- 3️⃣ Remover a coluna de recompensas da tabela de dificuldades (antigo mapeamento)
ALTER TABLE expedicao_dificuldade
DROP COLUMN IF EXISTS recompensas;

-- 4️⃣ Adicionar novos campos básicos de recompensa
ALTER TABLE expedicao_dificuldade
ADD COLUMN exp_base INT DEFAULT 0,
ADD COLUMN bits_base INT DEFAULT 0,
ADD COLUMN item_recompensa_id BIGINT,
ADD COLUMN quantidade_item_recompensa INT DEFAULT 1;

-- 5️⃣ Criar chave estrangeira segura
ALTER TABLE expedicao_dificuldade
ADD CONSTRAINT fk_expedicao_dificuldade
FOREIGN KEY (item_recompensa_id)
REFERENCES item (id)
ON DELETE SET NULL;

-- ===========================================================
-- Comentário:
-- Agora cada dificuldade de expedição possui diretamente:
--   - EXP base (exp_base)
--   - Bits base (bits_base)
--   - Item recompensa (item_recompensa_id) → FK para item.id
--   - Quantidade de itens (quantidade_item_recompensa)
-- ===========================================================
