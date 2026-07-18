# API - Grupo Escoteiro (Spring Boot)

## Pré-requisitos
- Java 17+
- Maven
- MySQL rodando com o banco `grupo_escoteiro` já criado (rode o script .sql antes)

## Como rodar

1. Ajuste usuário/senha do banco em `src/main/resources/application.properties`

2. Rode com Maven:
   ```
   mvn spring-boot:run
   ```

3. A API sobe em `http://localhost:8080`

## Estrutura

- `model/` — 11 entidades JPA (uma por tabela)
- `repository/` — interfaces `JpaRepository` (CRUD automático)
- `controller/` — endpoints REST (GET, GET/{id}, POST, PUT/{id}, DELETE/{id}) para cada entidade
- `application.properties` — configuração de conexão com o MySQL

## Observações de design

- Usei `spring.jpa.hibernate.ddl-auto=update`: o Hibernate ajusta o schema conforme as entidades,
  mas como você já tem o script `.sql`, pode trocar para `validate` ou `none` se preferir manter o controle total via SQL.
- Não incluí uma camada de Service separada (Controller fala direto com Repository) para manter o projeto enxuto.
  Se quiser evoluir para uma arquitetura em camadas (Controller -> Service -> Repository), é um próximo passo natural.
- Os `@Enumerated(EnumType.STRING)` mapeiam os ENUMs do MySQL (tipo_membro, status_pagamento, etc.)
