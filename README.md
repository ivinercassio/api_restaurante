# 🍽️ Restaurante API

Esta é uma API RESTful para a gestão de um ecossistema gastronômico. O sistema permite o controle sobre o inventário de ingredientes, a criação de receitas, a montagem de cardápios e o gerenciamento técnico do relacionamento entre pratos e insumos.

---

## 🚀 Funcionalidades

A API está organizada em quatro módulos principais para facilitar a manutenção e a escalabilidade:

### 🥗 Ingredientes (`ingrediente-controller`)
Gerenciamento de todos os itens base utilizados nas preparações.
* Cadastro, edição, listagem e exclusão de ingredientes.

### 📜 Receitas (`receita-controller`)
Definição dos pratos e preparos que compõem o menu.
* Controle de nomes, descrições, valor, porcionamento e identificador de cardápio.

### 📋 Cardápios (`cardapio-controller`)
Agrupamento de receitas para oferta comercial e organização do menu da casa.
* Gestão de menus semanais.

### 🔗 Composição (`receita-ingrediente-controller`)
O núcleo lógico do sistema, responsável por gerenciar a ficha técnica de cada prato.
* **Vínculos:** Associa ingredientes a receitas específicas.
* **Consultas Bidirecionais:** Permite saber quais ingredientes compõem um prato ou em quais pratos um ingrediente específico é utilizado.
* **Gestão de Relacionamentos:** Remoção precisa de insumos vinculados a uma receita através de chaves compostas.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot
* **Documentação:** Swagger UI / OpenAPI 3
* **Arquitetura:** REST (Representational State Transfer)

---

## 📌 Documentação da API

Uma vez que o projeto esteja rodando localmente, você pode acessar a documentação interativa (Swagger) para testar os endpoints através do link:

`http://localhost:8080/swagger-ui.html`

### Principais Endpoints

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/v1/receitas` | Lista todas as receitas cadastradas. |
| `POST` | `/api/v1/relacoes/insert` | Cria o vínculo entre uma receita e um ingrediente. |
| `GET` | `/api/v1/relacoes/receita/{id}` | Busca todos os ingredientes de uma receita específica. |
| `DELETE` | `/api/v1/relacoes/delete/receita/{idR}/ingrediente/{idI}` | Remove um ingrediente específico de uma receita. |

---

## ⚙️ Como executar o projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/restaurante-api.git](https://github.com/seu-usuario/restaurante-api.git)

2. **Navegue até o diretório:**
   ```bash
   cd api_restaurante

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run

Desenvolvido por @ivinercassio