## API de Produtos
- Esta é uma API RESTful desenvolvida com Spring Boot para gerenciamento de produtos. 
- Ela permite realizar operações básicas de CRUD (Criar, Ler, Atualizar, Deletar)
- sobre os produtos de um sistema de inventário.

## Tecnologias Utilizadas
- Spring Boot: Framework para desenvolvimento de aplicações Java.
- Spring Data JPA: Para manipulação de dados com um repositório baseado em JPA.
- Spring Validation: Para validação das entradas de dados nos endpoints.
- Spring Web: Para criar a API RESTful.
- Postgresql

## Requisitos
 - Java 17 ou Superior
 - Jdk instalado
 - maven
 - Git 
 - Banco de Dados Postgres

## Passos para Instalação
1. Clone este repositório: https://github.com/GabrielCarvalho0812/Api-Products.git
2. importe o projeto para sua IDE
3. Configure o banco de dados no arquivo application.properties

# Configuração do banco de dados
- spring.datasource.url=jdbc:postgresql://localhost:5432/meu-banco
- spring.datasource.username=postgres
- spring.datasource.password=123
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
 
## iniciar a aplicação
- Compile e inicie a aplicação: mvn spring-boot:run
- A API estará disponível em http://localhost:8080

-teste



 