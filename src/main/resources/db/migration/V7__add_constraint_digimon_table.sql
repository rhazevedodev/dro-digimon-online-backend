ALTER TABLE digimon
ADD CONSTRAINT unico_digimon_ativo_por_jogador
UNIQUE (jogador_id, ativo)
WHERE ativo = true;