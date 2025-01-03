CREATE TABLE restaurant_owner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    email VARCHAR(255),
    senha VARCHAR(255) NOT NULL ,
    login VARCHAR(255) NOT NULL ,
    ultima_alteracao DATE,
    endereco VARCHAR(255)
);

CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    email VARCHAR(255),
    senha VARCHAR(255) NOT NULL ,
    login VARCHAR(255) NOT NULL ,
    ultima_alteracao DATE,
    endereco VARCHAR(255)
);