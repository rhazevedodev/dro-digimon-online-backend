# Digimon Revolution Online (Exemplo com Spring Boot + Docker + PostgreSQL)

Este projeto é um exemplo de aplicação **Java Spring Boot** rodando em **Docker**, com **PostgreSQL** como banco de dados e **Flyway** para versionamento/migração de tabelas.

---

## 🚀 Requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ▶️ Como executar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-repo/meu-projeto.git
cd meu-projeto
```

### 2. Subir os containers
```bash
docker-compose up --build
```

O comando irá:
- Criar e subir o banco **PostgreSQL** (`meu_postgres_digimon`)
- Criar e subir a aplicação **Spring Boot** (`meu_app_digimon`)
- Executar as migrations do **Flyway** (criando tabelas e inserindo o usuário administrador)

---

## 🗄️ Banco de Dados

- **Host**: `localhost`  
- **Porta**: `5432`  
- **Banco**: `meu_banco`  
- **Usuário**: `admin`  
- **Senha**: `admin123`  

### Acessar o banco pelo terminal
```bash
docker exec -it meu_postgres psql -U admin -d meu_banco
```

No console do `psql`, você pode verificar os dados:
```sql
\dt; -- lista tabelas
SELECT * FROM usuarios;
```

Para sair:
```sql
\q
```

---

## 🌐 API da Aplicação

A aplicação sobe no endereço:
```
http://localhost:8080
```

Exemplo de endpoint para listar usuários (se implementado):
```
GET http://localhost:8080/usuarios
```

---

## 🛑 Parar os containers
```bash
docker-compose down
```

Se quiser **apagar também os dados do banco**:
```bash
docker-compose down -v
```

---

## 📌 Notas
- O banco já é iniciado com o usuário administrador:
  - **Email**: `admin@sistema.com`  
  - **Senha**: `senha123`  
  - **Role**: `ADMIN`
- O Flyway gerencia a criação de tabelas e dados iniciais.
