-- Garante que apenas um digimon ativo por jogador
CREATE UNIQUE INDEX IF NOT EXISTS unico_digimon_ativo_por_jogador_idx
ON digimon (jogador_id)
WHERE ativo = true;
