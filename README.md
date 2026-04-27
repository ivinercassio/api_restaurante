# 🍽️ Restaurante API

Esta é uma API RESTful robusta para a gestão de um ecossistema gastronômico. O sistema permite o controle total sobre o inventário de ingredientes, criação de receitas, montagem de cardápios e, agora, conta com uma **camada de segurança** para proteção de dados e controle de acesso.

---

## 🔐 Novidade: Segurança e Autenticação

A aplicação foi atualizada com **Spring Security**, implementando autenticação e autorização para garantir que apenas usuários permitidos realizem operações sensíveis.

* **Autenticação JWT:** Proteção de endpoints via tokens.
* **Controle de Acesso (RBAC):** Gestão de perfis e permissões de usuário.
* **Criptografia:** Senhas armazenadas de forma segura utilizando BCrypt.

---

## 🚀 Módulos da API

A API está organizada em módulos lógicos para facilitar a manutenção e a escalabilidade:

### 🥗 Gestão de Insumos e Pratos
* **Ingredientes (`ingrediente-controller`):** Cadastro e controle de itens base.
* **Receitas (`receita-controller`):** Definição de pratos, valores e porcionamento.
* **Cardápios (`cardapio-controller`):** Organização comercial e menus semanais.
* **Composição (`receita-ingrediente-controller`):** Ficha técnica detalhada vinculando insumos a pratos.

### 🛡️ Gestão de Acesso (Segurança)
* **Usuários (`usuario-controller`):** Gestão de contas de operadores e administradores.
* **Perfis (`usuario-perfil-controller`):** Atribuição de cargos e níveis de acesso aos usuários.
* **Autenticação (`authentication-controller`):** Endpoints para login, registro e validação de permissões.

---

## 📌 Documentação da API

Com a aplicação em execução, acesse a interface interativa do Swagger para explorar e testar os recursos:
`http://localhost:8080/swagger-ui.html`

### Principais Endpoints

| Módulo | Método | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| **Auth** | `POST` | `/api/v2/auth/login` | Autentica o usuário e retorna o token de acesso. |
| **Auth** | `POST` | `/api/v2/auth/register` | Realiza o cadastro de novos usuários no sistema. |
| **Receitas** | `GET` | `/api/v2/receitas` | Lista todas as receitas cadastradas. |
| **Composição** | `POST` | `/api/v2/receita-ingrediente/insert` | Cria o vínculo (ficha técnica) entre receita e ingrediente. |
| **Usuários** | `GET` | `/api/v2/usuarios` | Lista usuários (Requer privilégios administrativos). |

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3+
* **Segurança:** Spring Security (JWT / BCrypt)
* **Documentação:** Swagger UI / OpenAPI 3
* **Gerenciador de Dependências:** Maven

---

## ⚙️ Como executar o projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/ivinercassio/api_restaurante.git
   ```

2. **Navegue até o diretório:**
   ```bash
   cd api_restaurante
   ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

> **Nota:** Ao testar endpoints protegidos no Swagger, lembre-se de utilizar o botão **Authorize** informando o token JWT obtido no endpoint de login.

---
Desenvolvido por [@ivinercassio](https://github.com/ivinercassio)