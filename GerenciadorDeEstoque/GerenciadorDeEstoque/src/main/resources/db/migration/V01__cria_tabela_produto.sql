CREATE TABLE produto (
    id_produto SERIAL PRIMARY KEY,
    nome_produto VARCHAR(255) NOT NULL,
    estoque_produto INT NOT NULL,
    categoria_produto VARCHAR(255) NOT NULL
);
