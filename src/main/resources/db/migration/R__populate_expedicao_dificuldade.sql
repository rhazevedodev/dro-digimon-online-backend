-- ===========================================================
-- População da tabela expedicao_dificuldade
-- ===========================================================
-- Campos: expedicao_id, dificuldade, poder_minimo, duracao_horas
-- ===========================================================

-- Floresta Misteriosa (id = 1)
INSERT INTO expedicao_dificuldade (expedicao_id, dificuldade, poder_minimo, duracao_horas)
VALUES
(1, 'FACIL',   100,  1),
(1, 'MEDIA',   300,  3),
(1, 'DIFICIL', 600,  6),
(1, 'EXTREMA',1000,  12);

-- Ruínas Antigas (id = 2)
INSERT INTO expedicao_dificuldade (expedicao_id, dificuldade, poder_minimo, duracao_horas)
VALUES
(2, 'FACIL',   200,  1),
(2, 'MEDIA',   500,  3),
(2, 'DIFICIL', 900,  6),
(2, 'EXTREMA',1300,  12);

-- Mina de Dados (id = 3)
INSERT INTO expedicao_dificuldade (expedicao_id, dificuldade, poder_minimo, duracao_horas)
VALUES
(3, 'FACIL',   250,  1),
(3, 'MEDIA',   600,  3),
(3, 'DIFICIL',1000,  6),
(3, 'EXTREMA',1500,  12);
