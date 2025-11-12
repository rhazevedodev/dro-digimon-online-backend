-- Floresta Misteriosa
UPDATE expedicao_dificuldade
SET exp_base = 50, bits_base = 100, item_recompensa_id= 89, quantidade_item_recompensa= 1
WHERE dificuldade = 'FACIL' AND expedicao_id = 1;

UPDATE expedicao_dificuldade
SET exp_base = 100, bits_base = 200, item_recompensa_id= 90, quantidade_item_recompensa= 1
WHERE dificuldade = 'MEDIA' AND expedicao_id = 1;

UPDATE expedicao_dificuldade
SET exp_base = 200, bits_base = 400, item_recompensa_id= 91, quantidade_item_recompensa= 1
WHERE dificuldade = 'DIFICIL' AND expedicao_id = 1;

UPDATE expedicao_dificuldade
SET exp_base = 400, bits_base = 800, item_recompensa_id= 92, quantidade_item_recompensa= 1
WHERE dificuldade = 'EXTREMA' AND expedicao_id = 1;
