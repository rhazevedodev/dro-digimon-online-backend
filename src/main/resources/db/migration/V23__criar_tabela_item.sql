-- ===========================================
-- TABELA: ITEM
-- ===========================================
CREATE TABLE item (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    imagem VARCHAR(255),
    pode_vender BOOLEAN DEFAULT FALSE,
    valor_compra INT DEFAULT 0,
    valor_venda INT DEFAULT 0
);
