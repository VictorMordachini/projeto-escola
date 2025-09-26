# API de Gestão Escolar com Spring Boot

![Java](https://img.shields.io/badge/Java-21-blue.svg) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-brightgreen.svg) ![Maven](https://img.shields.io/badge/Maven-4.0.0-red.svg) ![H2 Database](https://img.shields.io/badge/Database-H2-orange.svg)

API RESTful para um sistema simplificado de gestão escolar, desenvolvida com Java e Spring Boot. O projeto permite o gerenciamento completo de Cursos, Alunos e Professores, com uma arquitetura limpa e desacoplada.

---

## 🚀 Visão Geral das Funcionalidades

* **Gerenciamento de Cursos:** CRUD completo (Criar, Ler, Atualizar, Excluir) para cursos, incluindo título e carga horária.
* **Gerenciamento de Alunos:** CRUD completo para alunos, permitindo associá-los a um curso existente.
* **Gerenciamento de Professores:** CRUD completo para professores, com suporte para múltiplas turmas e disciplinas.
* **Interface Web Simples:** Uma página estática (`index.html`) é fornecida para interagir e testar a API diretamente pelo navegador.
* **Arquitetura em Camadas:** Organização clara do código em camadas de Interface (Controllers), Aplicação (Services e DTOs) e Domínio (Entities e Repositories).

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.3.5**
* **Spring Data JPA / Hibernate**
* **Banco de Dados em Memória H2**
* **Lombok**
* **Maven**
* **Frontend:** HTML5, CSS3 e JavaScript puro para a interface de interação.

---

## 🏃‍♀️ Como Rodar a Aplicação

1.  **Pré-requisitos:**
    * Java JDK 21 ou superior.
    * Apache Maven (opcional, pois o projeto utiliza o Maven Wrapper).

2.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/api-escola-spring.git
    cd api-escola-spring
    ```

3.  **Execute a aplicação com o Maven Wrapper:**
    * No Windows:
        ```bash
        ./mvnw.cmd spring-boot:run
        ```
    * No Linux ou macOS:
        ```bash
        ./mvnw spring-boot:run
        ```

4.  A API estará disponível em `http://localhost:8080`. Para acessar a interface web, navegue para **`http://localhost:8080/index.html`**.

---

## 📚 Endpoints da API

A API segue os padrões REST e utiliza o formato JSON.

### **Curso**
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/curso` | Retorna a lista de todos os cursos. |
| `GET` | `/curso/{id}` | Retorna os detalhes de um curso específico. |
| `POST` | `/curso` | Cria um novo curso. |
| `PUT` | `/curso/{id}` | Atualiza um curso existente. |
| `DELETE`| `/curso/{id}` | Exclui um curso. |

* **Payload para `POST` / `PUT`:**
    ```json
    {
        "titulo": "Desenvolvimento Web com Spring Boot",
        "cargaHoraria": 120
    }
    ```

### **Aluno**
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/aluno` | Retorna a lista de todos os alunos. |
| `GET` | `/aluno/{id}` | Retorna os detalhes de um aluno específico. |
| `POST` | `/aluno` | Cria um novo aluno. |
| `PUT` | `/aluno/{id}` | Atualiza um aluno existente. |
| `DELETE`| `/aluno/{id}` | Exclui um aluno. |

* **Payload para `POST` / `PUT`:**
    ```json
    {
        "nome": "Maria Silva",
        "cpf": 12345678900,
        "turma": "2B",
        "idCurso": "uuid-do-curso-aqui"
    }
    ```

### **Professor**
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/professor` | Retorna a lista de todos os professores. |
| `GET` | `/professor/{id}`| Retorna os detalhes de um professor. |
| `POST` | `/professor` | Cria um novo professor. |
| `PUT` | `/professor/{id}` | Atualiza um professor existente. |
| `DELETE`| `/professor/{id}`| Exclui um professor. |

* **Payload para `POST` / `PUT`:**
    ```json
    {
        "nome": "João Souza",
        "cpf": 98765432100,
        "turmas": ["2A", "2B"],
        "disciplinas": ["Matemática", "Algoritmos"]
    }
    ```

---

## 🏗️ Estrutura do Projeto

O código-fonte é organizado em uma arquitetura de 3 camadas para garantir baixo acoplamento e alta coesão.

com.senai.projeto_escola
│
├── application
│   ├── dto         # Data Transfer Objects: desacopla a camada de domínio da interface.
│   └── service     # Contém a lógica de negócio e as regras da aplicação.
│
├── domain
│   ├── entity      # Entidades JPA que representam as tabelas do banco de dados.
│   └── repository  # Interfaces Spring Data JPA para abstrair o acesso aos dados.
│
└── interface_ui
└── controller  # Controladores REST que expõem os endpoints da API para o mundo externo.
