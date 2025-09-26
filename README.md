API de Gestão Escolar com Spring Boot
📖 Sobre o Projeto
Esta é uma API RESTful desenvolvida como um sistema simplificado para gestão de uma escola. A aplicação permite o gerenciamento completo (CRUD - Create, Read, Update, Delete) de Cursos, Alunos e Professores, oferecendo uma base sólida e organizada para sistemas acadêmicos.

O projeto foi construído seguindo as melhores práticas de desenvolvimento com Spring Boot, separando as responsabilidades em camadas (Domínio, Aplicação e Interface). Além do backend, uma interface web simples em HTML, CSS e JavaScript é fornecida para facilitar a interação e teste dos endpoints da API.

✨ Funcionalidades
Gestão de Cursos:

📝 Cadastrar novos cursos com título e carga horária.

🔄 Atualizar informações de cursos existentes.

🗑️ Excluir cursos.

📋 Listar todos os cursos cadastrados.

🔍 Buscar um curso específico por ID.

Gestão de Alunos:

🧑‍🎓 Cadastrar novos alunos com nome, CPF, turma e associá-los a um curso.

🔄 Atualizar dados cadastrais dos alunos.

🗑️ Excluir alunos.

📋 Listar todos os alunos.

🔍 Buscar um aluno específico por ID.

Gestão de Professores:

👨‍🏫 Cadastrar novos professores com nome, CPF, turmas e disciplinas que lecionam.

🔄 Atualizar dados cadastrais dos professores.

🗑️ Excluir professores.

📋 Listar todos os professores.

🔍 Buscar um professor específico por ID.

🛠️ Ferramentas e Tecnologias
A seleção de tecnologias foi pensada para criar uma aplicação robusta, moderna e de fácil manutenção.

Backend
Tecnologia	Versão	Propósito
Java	21	Linguagem de programação principal, escolhida por sua robustez, ecossistema maduro e forte tipagem.
Spring Boot	3.3.5	Framework principal para a construção da API. Ele acelera o desenvolvimento com sua convenção sobre configuração, auto-configuração e servidor embutido.
Spring Data JPA	-	Simplifica a camada de persistência de dados, automatizando a criação de repositórios e facilitando a comunicação com o banco de dados.
Hibernate	-	Implementação do JPA utilizada pelo Spring Data para mapear os objetos Java para o modelo relacional do banco de dados (ORM).
H2 Database	-	Banco de dados relacional em memória. Ideal para ambientes de desenvolvimento e teste, pois não requer instalação e é reiniciado a cada execução da aplicação.
Maven	4.0.0	Ferramenta de gerenciamento de dependências e build do projeto. O pom.xml centraliza todas as bibliotecas necessárias.
Lombok	-	Biblioteca que reduz o código boilerplate (getters, setters, construtores) nas classes de modelo através de anotações, tornando o código mais limpo.

Exportar para as Planilhas
Frontend
Tecnologia	Propósito
HTML5	Estruturação da interface web para interação com a API.
CSS3	Estilização da página, tornando a interface mais agradável e organizada.
JavaScript (ES6+)	Responsável pela interatividade da página, realizando as chamadas fetch para a API REST e manipulando o DOM para exibir os dados dinamicamente.

Exportar para as Planilhas
🚀 Como Executar o Projeto
Para executar a aplicação localmente, siga os passos abaixo:

Pré-requisitos:

JDK 21 ou superior instalado.

Maven configurado no PATH do sistema (opcional, pois o projeto usa o Maven Wrapper).

Passos:

Clone o repositório:

Bash

git clone https://github.com/seu-usuario/api-escola-spring.git
cd api-escola-spring
Execute a aplicação com o Maven Wrapper:

No Windows:

Bash

./mvnw.cmd spring-boot:run
No Linux ou macOS:

Bash

./mvnw spring-boot:run
Acesse a aplicação:

O backend estará disponível em http://localhost:8080.

A interface web pode ser acessada em http://localhost:8080/index.html.

API Endpoints
A API segue os padrões REST e utiliza o formato JSON para as requisições e respostas.

Curso
Método	Endpoint	Descrição
GET	/curso	Retorna a lista de todos os cursos.
GET	/curso/{id}	Retorna os detalhes de um curso específico.
POST	/curso	Cria um novo curso.
PUT	/curso/{id}	Atualiza as informações de um curso existente.
DELETE	/curso/{id}	Exclui um curso.

Exportar para as Planilhas
Exemplo de corpo (payload) para POST/PUT em /curso:

JSON

{
    "titulo": "Desenvolvimento Web com Spring Boot",
    "cargaHoraria": 120
}
Aluno
Método	Endpoint	Descrição
GET	/aluno	Retorna a lista de todos os alunos.
GET	/aluno/{id}	Retorna os detalhes de um aluno específico.
POST	/aluno	Cria um novo aluno.
PUT	/aluno/{id}	Atualiza as informações de um aluno existente.
DELETE	/aluno/{id}	Exclui um aluno.

Exportar para as Planilhas
Exemplo de corpo (payload) para POST/PUT em /aluno:

JSON

{
    "nome": "Maria Silva",
    "cpf": 12345678900,
    "turma": "2B",
    "idCurso": "uuid-do-curso-aqui"
}
Professor
Método	Endpoint	Descrição
GET	/professor	Retorna a lista de todos os professores.
GET	/professor/{id}	Retorna os detalhes de um professor específico.
POST	/professor	Cria um novo professor.
PUT	/professor/{id}	Atualiza as informações de um professor existente.
DELETE	/professor/{id}	Exclui um professor.

Exportar para as Planilhas
Exemplo de corpo (payload) para POST/PUT em /professor:

JSON

{
    "nome": "João Souza",
    "cpf": 98765432100,
    "turmas": ["2A", "2B"],
    "disciplinas": ["Matemática", "Algoritmos"]
}
🏗️ Estrutura do Projeto
O código-fonte está organizado em uma arquitetura de 3 camadas para garantir baixo acoplamento e alta coesão:

com.senai.projeto_escola
│
├── application
│   ├── dto         # Data Transfer Objects: objetos que carregam dados entre as camadas.
│   └── service     # Contém a lógica de negócio da aplicação.
│
├── domain
│   ├── entity      # Entidades JPA que mapeiam as tabelas do banco de dados.
│   └── repository  # Interfaces do Spring Data JPA para acesso aos dados.
│
└── interface_ui
    └── controller  # Controladores REST que expõem os endpoints da API.
