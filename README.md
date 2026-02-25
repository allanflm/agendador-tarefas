# 🗓️ Task Scheduler API (Agendador de Tarefas)

> API robusta para gerenciamento de fluxos de trabalho, construída com Spring Boot, Gradle e MongoDB.

---

## 📖 Sobre o Projeto

O **Agendador de Tarefas** é uma solução de backend focada em organização e produtividade. O projeto foi estruturado para oferecer uma interface escalável onde usuários podem centralizar o controle de suas atividades diárias.

Diferente de sistemas relacionais rígidos, esta API utiliza o **MongoDB**, permitindo que cada tarefa seja armazenada como um documento flexível. Isso facilita futuras expansões do modelo de dados sem a necessidade de migrações complexas (Migrations), alinhando-se às necessidades de agilidade do mercado.

---

## 🛠️ Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

---

## ✨ Funcionalidades Principais

* **Persistência NoSQL:** Armazenamento de documentos flexíveis utilizando **MongoDB**.
* **Segurança de Rotas:** Autenticação e proteção de endpoints via **Spring Security**.
* **Ciclo de Vida de Tarefas:** Operações completas de CRUD (Criar, Ler, Atualizar e Deletar).
* **Gestão de Status:** Classificação dinâmica entre tarefas *Pendentes*, *Em Progresso* e *Concluídas*.
* **Arquitetura Layered:** Separação clara entre controladores, serviços e repositórios para fácil manutenção.

---

## 🏗️ Arquitetura

O projeto segue o padrão de **Arquitetura em Camadas**, garantindo uma estrutura organizada e de fácil leitura:

1.  **Controller Layer:** Pontos de entrada REST que recebem e respondem via JSON.
2.  **Service Layer:** Camada onde residem as regras de negócio e validações.
3.  **Repository Layer:** Interface de comunicação com o MongoDB via **Spring Data MongoDB**.
4.  **Security Layer:** Configurações de acesso e políticas de segurança da API.

---

## 🚀 Como Executar

### Pré-requisitos
* Java JDK 17
* Gradle 7.x ou superior (ou utilize o `gradlew` incluso)
* MongoDB ativo (Local ou Atlas)

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/allanflm/agendador-tarefas.git](https://github.com/allanflm/agendador-tarefas.git)
    cd agendador-tarefas
    ```

2.  **Configuração da Conexão:**
    No arquivo `src/main/resources/application.properties`, configure sua URI do MongoDB:
    ```properties
    spring.mongodb.uri=mongodb://localhost:27017/db_agendador
    ```
---

## 🛣️ Endpoints e Uso

Abaixo estão os principais recursos expostos pela API:

| Método | Endpoint | Objetivo | Requer Auth |
| :--- | :--- | :--- | :--- |
| `POST` | `/usuario | Login e obtenção de acesso | Não |
| `GET` | `/tarefas` | Lista todas as tarefas do usuário | Sim |
| `POST` | `tarefas` | Adiciona um novo documento ao MongoDB | Sim |
| `PUT` | `tarefas}` | Atualiza dados da tarefa por ID | Sim |
| `DELETE` | `tarefas` | Remove permanentemente a tarefa | Sim |

### Exemplo de Payload (JSON):
```json
{
  "titulo": "Monitorar Pipeline SAP",
  "descricao": "Verificar ingestão de dados para o Databricks",
  "dataAgendamento": "2026-02-28T09:00:00",
  "status": "EM_ANDAMENTO"
}
