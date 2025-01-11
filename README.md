# Sistema de Gerenciamento de Usuários para Restaurantes

## Descrição do Projeto

Este sistema backend é desenvolvido em Java utilizando Spring Boot, projetado para gerenciar
dois tipos de usuários: donos de restaurante e clientes. Ele permite o cadastro, a gestão de perfis, a troca de senha,
e a validação de login de usuários.

## Funcionalidades

- Cadastro de usuário com campos: Nome, Email, Login, Senha, Data da última alteração, Endereço.
- Gerenciamento de usuário: Alteração de dados cadastrais.
- Troca de senha.
- Validação de login.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Docker
- PostgreSQL
- H2

## Como Executar

### Pré-requisitos

- JDK 11 ou superior
- Docker
- Docker Compose


### Instruções

1. Clone o repositório:
   git clone https://github.com/Lucas-Duarte-94/tech-challenge

2. Navegue até o diretório do projeto:
   cd tech-challenge

3. Compile o projeto (se Maven estiver instalado):
   mvn clean install

4. Configure o banco de dados conforme o ambiente (H2 para desenvolvimento ou PostgreSQL para DSV).
5. Execute o Docker Compose:
   docker-compose up
6. Execute a aplicação

## Documentação da API
A documentação completa da API está disponível através do Swagger UI. Isso inclui detalhes sobre todos os endpoints,
parâmetros e modelos de dados. Você pode acessar a documentação interativa e executar chamadas de API diretamente
através do Swagger.

## Autores
Luiza V. Ramos
Lucas Duarte