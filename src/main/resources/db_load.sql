CREATE TABLE IF NOT EXISTS TB_ENDERECOS (
                              IDE_ENDERECO SERIAL PRIMARY KEY,
                              DES_LOGRADOURO VARCHAR(255),
                              NUM_ENDERECO VARCHAR(50),
                              DES_COMPLEMENTO VARCHAR(255),
                              DES_BAIRRO VARCHAR(255),
                              DES_CIDADE VARCHAR(255),
                              DES_ESTADO VARCHAR(255),
                              NUM_CEP VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS TB_TIPO_USUARIOS (
                                  IDE_TIPO_USUARIO SERIAL PRIMARY KEY,
                                  DES_TIPO_USUARIO VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS TB_USUARIOS (
                             IDE_USUARIO SERIAL PRIMARY KEY,
                             NOM_COMPLETO VARCHAR(255) NOT NULL,
                             IDE_ENDERECO BIGINT NULL,
                             DES_EMAIL VARCHAR(255) NOT NULL,
                             NOM_LOGIN VARCHAR(255) NOT NULL,
                             DES_SENHA VARCHAR(255) NOT NULL,
                             IDE_TIPO_USUARIO BIGINT NOT NULL,
                             DTA_ULT_ALTERACAO TIMESTAMP,
                             CONSTRAINT FK_USUARIOS_ENDERECO FOREIGN KEY (IDE_ENDERECO) REFERENCES TB_ENDERECOS(IDE_ENDERECO) ON DELETE SET NULL,
                             CONSTRAINT FK_USUARIOS_TIPO_USUARIO FOREIGN KEY (IDE_TIPO_USUARIO) REFERENCES TB_TIPO_USUARIOS(IDE_TIPO_USUARIO) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS TB_RESTAURANTES (
                                 IDE_RESTAURANTE SERIAL PRIMARY KEY,
                                 NOM_RESTAURANTE VARCHAR(255),
                                 TIPO_COZINHA VARCHAR(255),
                                 IDE_ENDERECO BIGINT NULL,
                                 IDE_USUARIO BIGINT NOT NULL,
                                 CONSTRAINT FK_RESTAURANTE_ENDERECO FOREIGN KEY (IDE_ENDERECO) REFERENCES TB_ENDERECOS(IDE_ENDERECO) ON DELETE SET NULL,
                                 CONSTRAINT FK_RESTAURANTE_USUARIO FOREIGN KEY (IDE_USUARIO) REFERENCES TB_USUARIOS(IDE_USUARIO) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS TB_CARDAPIO (
                             IDE_CARDAPIO SERIAL PRIMARY KEY,
                             IDE_RESTAURANTE BIGINT NULL,
                             CONSTRAINT FK_CARDAPIO_RESTAURANTE FOREIGN KEY (IDE_RESTAURANTE) REFERENCES TB_RESTAURANTES(IDE_RESTAURANTE) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS TB_ITENS_CARDAPIOS (
                                    IDE_ITEM SERIAL PRIMARY KEY,
                                    NOM_ITEM VARCHAR(255) NOT NULL,
                                    DES_ITEM VARCHAR(255) NOT NULL,
                                    VLR_ITEM DECIMAL(10, 2) NOT NULL,
                                    DES_FOTO VARCHAR(255),
                                    STA_LOCAL BOOLEAN NOT NULL,
                                    IDE_CARDAPIO BIGINT NOT NULL,
                                    CONSTRAINT FK_ITEM_CARDAPIO FOREIGN KEY (IDE_CARDAPIO) REFERENCES TB_CARDAPIO(IDE_CARDAPIO) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS TB_FUNCIONAMENTO (
                                  IDE_FUNCIONAMENTO SERIAL PRIMARY KEY,
                                  DTA_SEMANA VARCHAR(20),
                                  HORA_ABERTURA TIME,
                                  HORA_FECHA TIME,
                                  IDE_RESTAURANTE BIGINT NULL,
                                  CONSTRAINT FK_FUNCIONAMENTO_RESTAURANTE FOREIGN KEY (IDE_RESTAURANTE) REFERENCES TB_RESTAURANTES(IDE_RESTAURANTE) ON DELETE SET NULL
);

INSERT INTO TB_TIPO_USUARIOS (DES_TIPO_USUARIO) VALUES
                                                    ('CLIENTE'),
                                                    ('DONO_RESTAURANTE'),
                                                    ('ADMIN');

INSERT INTO TB_ENDERECOS (DES_LOGRADOURO, NUM_ENDERECO, DES_COMPLEMENTO, DES_BAIRRO, DES_CIDADE, DES_ESTADO, NUM_CEP) VALUES
    ('Rua das Flores', '123', 'Apto 101', 'Centro', 'São Paulo', 'SP', '01000-000'),
    ('Avenida Paulista', '1000', 'Sala 502', 'Bela Vista', 'São Paulo', 'SP', '01310-000'),
    ('Rua dos Restaurantes', '200', NULL, 'Jardins', 'São Paulo', 'SP', '01420-000'),
    ('Rua dos Mascotes', '400', NULL, 'Zoologicos', 'São Paulo', 'SP', '01510-000');

INSERT INTO TB_USUARIOS (NOM_COMPLETO, IDE_ENDERECO, DES_EMAIL, NOM_LOGIN, DES_SENHA, IDE_TIPO_USUARIO, DTA_ULT_ALTERACAO) VALUES
    ('Carlos Silva', 1, 'carlos@email.com', 'carlos_silva', 'senha123', 1, NOW()),
    ('Ana Souza', 2, 'ana@email.com', 'ana_souza', 'senha456', 2, NOW()),
    ('João Pedro', 3, 'joao@email.com', 'joao_pedro_5', 'senha789', 3, NOW()),
    ('João João', 4, 'joao2@email.com', 'joao_pedro_2', 'senha789', 3, NOW());

INSERT INTO TB_RESTAURANTES (NOM_RESTAURANTE, TIPO_COZINHA, IDE_ENDERECO, IDE_USUARIO) VALUES
    ('Restaurante Saboroso', 'Italiana', 1, 2),
    ('Churrascaria Gaúcha', 'Churrasco', 2, 2);

INSERT INTO TB_CARDAPIO (IDE_RESTAURANTE) VALUES
    (1),
    (2);

INSERT INTO TB_ITENS_CARDAPIOS (NOM_ITEM, DES_ITEM, VLR_ITEM, DES_FOTO, STA_LOCAL, IDE_CARDAPIO) VALUES
    ('Pizza Margherita', 'Pizza tradicional com molho de tomate, mussarela e manjericão.', 45.90, NULL, TRUE, 1),
    ('Picanha na Brasa', 'Deliciosa picanha grelhada com acompanhamento de arroz e farofa.', 89.90, NULL, TRUE, 2);

INSERT INTO TB_FUNCIONAMENTO (DTA_SEMANA, HORA_ABERTURA, HORA_FECHA, IDE_RESTAURANTE) VALUES
    ('MONDAY', '11:00', '23:00', 1),
    ('TUESDAY', '11:00', '23:00', 1),
    ('WEDNESDAY', '11:00', '23:00', 2),
    ('FRIDAY', '11:00', '23:00', 2);