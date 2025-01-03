CREATE TABLE restaurant_owner (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL ,
    login VARCHAR(255) NOT NULL ,
    ultima_alteracao DATE,
    endereco VARCHAR(255)
);

CREATE TABLE client (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL ,
    login VARCHAR(255) NOT NULL ,
    ultima_alteracao DATE,
    endereco VARCHAR(255)
);