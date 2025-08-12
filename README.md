# 🐦 Simple Tweet API

Uma API REST desenvolvida em **Java + Spring Boot** para simular funcionalidades básicas de uma rede social (estilo Twitter/X).  
Inclui autenticação utilizando OAuth2 + JWT, criação e listagem de tweets, curtidas, comentários e feed paginado.

---

## 📋 Sumário
- [Sobre o projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Funcionalidades](#-funcionalidades)
- [Modelagem de dados](#-modelagem-de-dados)

---

## 📖 Sobre o projeto
O **Simple Tweet API** foi criado como um projeto de estudo e prática de boas práticas de desenvolvimento backend, com foco em:
- Modelagem de entidades relacionais
- Padrões de projeto no Spring Boot
- Autenticação e autorização com **JWT** e **OAuth2**
- Paginação e consultas otimizadas no banco de dados
- Separação clara de camadas (Controller, Facade, Service, Repository, Mapper)

---

## 🛠 Tecnologias
- **Java 17+**
- **Spring Boot 3+**
    - Spring Web
    - Spring Data JPA
    - Spring Security
- **Hibernate**
- **OAuth2**
- **JWT (JSON Web Token)**
- **MySQL**
- **Docker**
- **Maven** (gerenciamento de dependências)

---

## 🏗 Arquitetura
O projeto segue a seguinte organização:

- **controller**    # Camada de entrada da API (endpoints REST)
- **facade**       # Orquestra chamadas entre serviços, mapeadores e repositórios 
- **service**       # Lógica de negócio (Transactional)
- **repository**    # Acesso a dados (Spring Data JPA)
- **mapper**        # Conversão entre entidades e DTOs (MapStruct ou manual)
- **model**         # Entidades do domínio (JPA entities)
- **dto**           # Objetos de transferência de dados (requests/responses)
- **config**       # Configurações do Spring, segurança e JWT
- **security**     # Lógicas e handlers de segurança
- **exceptions**    # Exceções personalizadas

## ✨ Funcionalidades

- **Autenticação**
  - Login com username e senha
  - Geração de JWT com roles e permissões
- **Tweets**
  - Criar tweet
  - Listar feed paginado
  - Curtir/descurtir tweet
- **Comentários**
  - Criar comentário em tweet
  - Responder comentário
  - Curtir comentário
- **Usuários**
  - Criar conta
  - Atualizar conta
  - Visualizar perfil
  - Listar tweets do usuário

---

## 🗃 Modelagem de dados

**Entidades principais:**
- `User`
- `Tweet`
- `Comment`
- `Like`
- `Role`