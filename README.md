# API Vendas (estudo)

API REST com Spring Boot: usuários, produtos, carrinho de compras, JWT e Swagger.

## Configuração do banco (PostgreSQL)

No `application.properties` ou via variáveis de ambiente:

| Variável        | Descrição        | Exemplo     |
|-----------------|------------------|-------------|
| `DB_HOST`       | Host do PostgreSQL | `localhost` |
| `DB_PORT`       | Porta            | `5432`      |
| `DB_NAME`       | Nome do banco    | `vendas`    |
| `DB_USER`       | Usuário          | `postgres`  |
| `DB_PASSWORD`   | Senha            | (sua senha) |

Exemplo no `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vendas
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

## JWT (opcional)

Para produção, defina uma chave segura (mín. 256 bits / 32 caracteres):

```properties
app.jwt.secret=sua-chave-secreta-muito-longa-para-assinatura-do-token
app.jwt.expiration-ms=86400000
```

Ou variável de ambiente: `JWT_SECRET`.

## Swagger

Com a aplicação rodando:

- **UI:** http://localhost:8080/swagger-ui.html  
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs  

Rotas públicas (sem token): `POST /api/auth/registrar`, `POST /api/auth/login`.  
Demais rotas: use o token no Swagger em **Authorize** (Bearer &lt;token&gt;).

## Fluxo rápido

1. **Registrar:** `POST /api/auth/registrar` (nome, email, senha, cpf) → retorna token.
2. **Login:** `POST /api/auth/login` (email, senha) → retorna token.
3. No Swagger, clicar em **Authorize** e colar o token.
4. **Produtos:** criar/listar em `/api/produtos`.
5. **Carrinho:** `GET /api/carrinho`, `POST /api/carrinho/itens` (produtoId, quantidade).

## Estrutura

- `config/` – Security, JWT properties, OpenAPI.
- `security/` – JwtService, JwtFilter, UserDetails, CurrentUser.
- `domain/entity/` – Usuario, Produto, Carrinho, ItemCarrinho.
- `repository/` – JPA.
- `service/` – Auth, Usuario, Produto, Carrinho.
- `web/controller/` – REST + tags Swagger (Autenticação, Usuários, Produtos, Carrinho).
- `web/dto/` – Request/Response por recurso.
- `exception/` – Tratamento global e respostas de erro.

Senhas são armazenadas com **BCrypt**; rotas protegidas exigem **JWT** no header `Authorization: Bearer <token>`.
