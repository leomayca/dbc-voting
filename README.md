# Sistema de Votação Cooperativa

## Descrição
Este é um sistema de votação cooperativa desenvolvido em Java com Spring Boot. Permite que membros de uma cooperativa votem em diferentes pautas, garantindo que cada membro vote apenas uma vez por pauta.

## Características
- Cadastro de pautas.
- Gerenciamento de sessões de votação.
- Votação com opções 'Sim' ou 'Não'.
- Contabilização e anúncio dos resultados das votações.

## Tecnologias Utilizadas
- Java: 21
- Spring Boot: 3.2.2
- Maven
- Banco de Dados: MySQL

## Dependências do Maven
- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- Spring Boot DevTools (Runtime)
- MySQL Connector Java (Runtime)
- Lombok (Opcional)
- Spring Boot Starter Test (Test)
- Springdoc OpenAPI Web MVC UI: 2.0.2

## Instalação e Execuçãp
1. **Construa o projeto com Maven:**
   ```bash
   mvn clean install
2. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
## Endpoints da API
- `POST /api/members`: Cadastra um novo membro.
- `GET /api/members`: Lista todos os membros.
- `GET /api/members/{id}`: Obtém detalhes de um membro específico.
- `PUT /api/members/{id}`: Atualiza um membro.
- `DELETE /api/members/{id}`: Remove um membro.
- `POST /api/agendaItems`: Cadastra uma nova pauta.
- `GET /api/agendaItems`: Lista todas as pautas.
- `GET /api/agendaItems/{id}`: Obtém detalhes de uma pauta específica.
- `PUT /api/members/{id}`: Atualiza uma pauta.
- `DELETE /api/members/{id}`: Remove uma pauta.
- `POST /api/votes/cast`: Registra um voto.
- `GET /api/votingResults/{agendaItemId}`: Obtém o resultado da votação de uma pauta.
