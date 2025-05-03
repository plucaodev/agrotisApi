# 🧪 API de Gestão de Usuários e Laboratórios

Este projeto é uma API REST desenvolvida em **Java** com **Spring Boot**, utilizando banco de dados **H2 em memória**, e documentação automática via **Swagger**.

Ela permite o cadastro, filtro e gerenciamento de usuários e laboratórios vinculados a propriedades.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3+**
  - Spring Web
  - Spring Data JPA
  - Validation
- **H2 Database (memória)**
- **Swagger/OpenAPI (Springdoc)**
- **JUnit + Mockito** (para testes)

---

## 🛠️ Como executar o projeto localmente

### Pré-requisitos

- Java 17 ou superior instalado
- Maven instalado (ou utilize via terminal do IntelliJ)

📚 Documentação Swagger
Após iniciar o projeto, acesse:

🔗 http://localhost:8080/swagger-ui.html

Lá você pode testar todos os endpoints de forma interativa.

💾 Banco de Dados H2
O banco é carregado em memória (não persiste após reiniciar o projeto).

Acesse a interface web do H2 em:

🔗 http://localhost:8080/h2-console

Configurações:

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (em branco)

