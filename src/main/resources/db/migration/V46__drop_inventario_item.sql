-- Remove constraint se existir (opcional; DROP TABLE remove junto, mas é mais explícito)
ALTER TABLE IF EXISTS inventario_item
  DROP CONSTRAINT IF EXISTS unique_item_por_jogador;

-- Drop da tabela antiga (inventário por jogador)
DROP TABLE IF EXISTS inventario_item;
