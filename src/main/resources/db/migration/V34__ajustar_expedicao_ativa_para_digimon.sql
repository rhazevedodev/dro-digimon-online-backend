-- ===========================================================
-- Ajusta a tabela expedicao_ativa para referenciar diretamente o Digimon
-- ===========================================================

-- 1️⃣ Adiciona nova coluna digimon_id
ALTER TABLE expedicao_ativa
ADD COLUMN digimon_id BIGINT;

-- 2️⃣ Copia os valores existentes do digimon_enviado_id (se houver)
UPDATE expedicao_ativa
SET digimon_id = digimon_enviado_id;

-- 3️⃣ Remove chaves antigas e colunas desnecessárias
ALTER TABLE expedicao_ativa
DROP CONSTRAINT IF EXISTS fk_expedicao_ativa_jogador_id,
DROP CONSTRAINT IF EXISTS fk_expedicao_ativa_digimon_enviado_id;

ALTER TABLE expedicao_ativa
DROP COLUMN IF EXISTS jogador_id,
DROP COLUMN IF EXISTS digimon_enviado_id;

-- 4️⃣ Cria nova FK correta
ALTER TABLE expedicao_ativa
ADD CONSTRAINT fk_expedicao_ativa_digimon
FOREIGN KEY (digimon_id)
REFERENCES digimon(id)
ON DELETE CASCADE;