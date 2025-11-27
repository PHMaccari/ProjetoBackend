# Casa Automática

## O que é?

- Uma API destinada ao gerenciamento de dispositivos conectados a internet, possibilitando a atribuição de tarefas de forma remota.

## Integrantes:

- Paulo Henrique Da Silva Maccari 
- Matheus De Souza Constante 
- Gabriel Dos Reis Klein
 
## Problema

- Centralizar controle. Atualmente, gerenciar dispositivos conectados à internet exige o uso do app da fabricante para ligá-los ou enviar tarefas a eles.

## Tecnologias utilizadas

- **Java 17** - Linguagem principal para o desenvolvimento
- **Spring Boot 3.5.7** - Framework para a construção das APIs REST e gerenciar dependências
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória para persistência dos dados
- **MapStruct 1.5.5** - Mapeamento entre entidades e DTOs
- **Lombok** - Redução de boilerplate code
- **Springdoc OpenAPI 2.3.0** - Documentação automática da API (Swagger)
- **Spring Validation** - Validação de dados de entrada
- **Maven** - Gerenciamento de dependências e build
- **IntelliJ** - IDE principal para o desenvolvimento

## Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller** - Camada de apresentação, recebe requisições HTTP
- **Service** - Camada de lógica de negócio
- **Repository** - Camada de acesso a dados (Spring Data JPA)
- **DTO (Data Transfer Object)** - Objetos para transferência de dados entre camadas
- **Mapper** - Conversão entre entidades e DTOs usando MapStruct
- **Exception Handler** - Tratamento global de exceções

## Casos de uso:

- API para controle de dispositivos conectados a internet 
- Gerenciamento para dispositivos executar tarefas 
- Registrar novos dispositivos para execução de funcionalidades específicas.
- Gerenciar e atualizar informações de dispositivos previamente cadastrados.
- Realizar verificações de operabilidade e desempenho dos dispositivos existentes.
- Remover cadastros de dispositivos inoperantes ou obsoletos.
- Agendar e registrar novas tarefas nos dispositivos.
- Consultar e acompanhar o status de tarefas pendentes ou em andamento.
- Alterar os parâmetros ou detalhes de tarefas já cadastradas.
- Excluir definitivamente tarefas do sistema.
- Cadastrar novos usuários para permitir gerenciar os aparelhos.
- Consulta de informações do usuário para mostrar aparelhos do usuário.
- Atualizar informações de usuários para manter os registros sempre precisos.
- Remover usuários do sistema que não usam mais o sistema.
- Criar novas categorias para organizar aparelhos de maneira estruturada e eficiente.
- Administrar categorias existentes, otimizando a classificação dos aparelhos.
- Atualizar dados de categorias para refletir alterações de finalidade.
- Excluir categorias obsoletas garantindo a manutenção de um ambiente limpo e funcional.

## Entidades

- **Usuario**: Representa os usuários do sistema. Possui nome, email e senha.
- **Aparelho**: Representa os dispositivos IoT conectados. Possui nome, descrição e está associado a um usuário e uma categoria.
- **Tarefa**: Representa as tarefas agendadas para os aparelhos. Possui descrição, hora, repetição, período e situação.
- **Categoria**: Organiza os aparelhos por tipo (ex: limpeza, iluminação, segurança).

## Relacionamentos

- Um usuário pode ter vários aparelhos, mas o aparelho só pode ter um usuário
- Um aparelho pode ter várias tarefas que podem ocorrer ao longo do dia
- Uma tarefa pertence a apenas um aparelho
- Uma categoria pode estar em vários aparelhos, porém o aparelho só pode ter uma categoria

<img width="471" height="637" alt="image" src="https://github.com/user-attachments/assets/d3e0566d-f1dd-4e91-8995-05ad4bbc0a6c" />


## Endpoints

### Usuários

| Descrição | URI | Método HTTP | Corpo | Resposta Esperada | Erros esperados |
| --------- | --- | ----------- | ----- | ----------------- | --------------- |
| Listar todos os usuários | `/api/usuarios` | `GET` | Vazio | `200 OK` | - |
| Retornar um usuário | `/api/usuarios/{id}` | `GET` | Vazio | `200 OK` | `404 Not Found` - usuário não foi encontrado |
| Inserir novo usuário | `/api/usuarios` | `POST` | `{ "nome": "Gabriel", "email": "gabriel@example.com", "senha": "senha123" }` | `201 Created` | `400 Bad Request` - validação falhou |
| Atualizar usuário | `/api/usuarios/{id}` | `PUT` | `{ "nome": "Gabriel Reis", "email": "gabriel@hotmail.com", "senha": "novaSenha123" }` | `200 OK` | `404 Not Found` - usuário não foi encontrado |
| Deletar usuário | `/api/usuarios/{id}` | `DELETE` | Vazio | `204 No Content` | `404 Not Found` - usuário não foi encontrado |

### Aparelhos

| Descrição | URI | Método HTTP | Corpo | Resposta Esperada | Erros esperados |
| --------- | --- | ----------- | ----- | ----------------- | --------------- |
| Listar todos os aparelhos | `/api/aparelhos` | `GET` | Vazio | `200 OK` | - |
| Retornar um aparelho | `/api/aparelhos/{id}` | `GET` | Vazio | `200 OK` | `404 Not Found` - aparelho não foi encontrado |
| Inserir novo aparelho | `/api/aparelhos` | `POST` | `{ "nome": "robô aspirador", "descricao": "aspirar pó", "categoriaId": 1, "usuarioId": 1 }` | `201 Created` | `400 Bad Request` - validação falhou ou entidade relacionada não encontrada |
| Atualizar informações de um aparelho | `/api/aparelhos/{id}` | `PUT` | `{ "nome": "robô aspirador", "descricao": "aspira pó e passa pano", "categoriaId": 1, "usuarioId": 1 }` | `200 OK` | `404 Not Found` - aparelho não foi encontrado |
| Deletar um aparelho | `/api/aparelhos/{id}` | `DELETE` | Vazio | `204 No Content` | `404 Not Found` - aparelho não foi encontrado |

### Tarefas

| Descrição | URI | Método HTTP | Corpo | Resposta Esperada | Erros esperados |
| --------- | --- | ----------- | ----- | ----------------- | --------------- |
| Listar todas as tarefas | `/api/tarefas` | `GET` | Vazio | `200 OK` | - |
| Retornar uma tarefa | `/api/tarefas/{id}` | `GET` | Vazio | `200 OK` | `404 Not Found` - tarefa não foi encontrada |
| Inserir nova tarefa | `/api/tarefas` | `POST` | `{ "descricao": "ligar aspirador de pó por 10 minutos", "hora": "16:20:00", "repetir": "segunda", "periodo": "dentro", "aparelhoId": 1 }` | `201 Created` | `400 Bad Request` - validação falhou ou aparelho não encontrado |
| Atualizar tarefa | `/api/tarefas/{id}` | `PUT` | `{ "descricao": "ligar aspirador de pó por 20 minutos", "hora": "16:20:00", "repetir": "segunda", "periodo": "dentro", "aparelhoId": 1 }` | `200 OK` | `404 Not Found` - tarefa não foi encontrada |
| Deletar uma tarefa | `/api/tarefas/{id}` | `DELETE` | Vazio | `204 No Content` | `404 Not Found` - tarefa não foi encontrada |

### Categorias

| Descrição | URI | Método HTTP | Corpo | Resposta Esperada | Erros esperados |
| --------- | --- | ----------- | ----- | ----------------- | --------------- |
| Listar todas as categorias | `/api/categorias` | `GET` | Vazio | `200 OK` | - |
| Retornar uma categoria | `/api/categorias/{id}` | `GET` | Vazio | `200 OK` | `404 Not Found` - categoria não foi encontrada |
| Inserir nova categoria | `/api/categorias` | `POST` | `{ "tipo": "limpeza", "descricao": "limpeza da casa" }` | `201 Created` | `400 Bad Request` - validação falhou |
| Atualizar informações de uma categoria | `/api/categorias/{id}` | `PUT` | `{ "tipo": "limpeza", "descricao": "aparelhos que limpam a casa" }` | `200 OK` | `404 Not Found` - categoria não foi encontrada |
| Deletar uma categoria | `/api/categorias/{id}` | `DELETE` | Vazio | `204 No Content` | `404 Not Found` - categoria não foi encontrada |

## Estrutura de Dados

### UsuarioDTO
```json
{
  "id": 1,
  "nome": "Gabriel",
  "email": "gabriel@example.com",
  "senha": "senha123"
}
```

### AparelhoDTO
```json
{
  "id": 1,
  "nome": "robô aspirador",
  "descricao": "aspirar pó",
  "categoriaId": 1,
  "usuarioId": 1
}
```

### TarefaDTO
```json
{
  "id": 1,
  "descricao": "ligar aspirador de pó por 10 minutos",
  "hora": "16:20:00",
  "repetir": "segunda",
  "periodo": "dentro",
  "inicioPeriodo": "08:00:00",
  "fimPeriodo": "18:00:00",
  "aparelhoId": 1
}
```

### CategoriaDTO
```json
{
  "id": 1,
  "tipo": "limpeza",
  "descricao": "aparelhos de limpeza"
}
```

## Validações

O projeto utiliza validações do Jakarta Bean Validation:

- **Usuario**: nome (2-100 caracteres), email (formato válido), senha (mínimo 6 caracteres)
- **Aparelho**: nome (2-100 caracteres), descricao (máximo 500 caracteres), categoriaId e usuarioId obrigatórios
- **Tarefa**: descricao (5-200 caracteres), hora obrigatória, aparelhoId obrigatório
- **Categoria**: tipo (2-50 caracteres), descricao (máximo 200 caracteres)

## Tratamento de Erros

O projeto possui um `GlobalExceptionHandler` que trata:

- **NotFoundException** (404) - Recurso não encontrado
- **BusinessException** (400) - Erro de regra de negócio
- **Exception** (500) - Erros internos do servidor

Todas as respostas de erro seguem o formato:
```json
{
  "timestamp": "2024-01-01T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuário não encontrado"
}
```

## Documentação da API

A documentação interativa da API está disponível através do Swagger/OpenAPI:

- **URL**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

A documentação é gerada automaticamente a partir das anotações dos controllers e inclui:
- Descrição de todos os endpoints
- Modelos de dados (DTOs)
- Exemplos de requisições e respostas
- Códigos de status HTTP

## Banco de Dados

O projeto utiliza H2 Database em memória:

- **URL do Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:casadb`
- **Usuário**: `sa`
- **Senha**: (vazio)

O banco é recriado a cada execução da aplicação.

## Como executar projeto localmente

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+ (opcional, o projeto inclui Maven Wrapper)

### Passos

1. Clone o repositório:
```bash
git clone https://github.com/PHMaccari/ProjetoBackend.git
cd ProjetoBackend/casa_automatica
```

2. Configure as variáveis de ambiente:

Copie o arquivo `env.example` para `.env`:
```bash
# Windows (PowerShell)
Copy-Item env.example .env

# Linux/Mac
cp env.example .env
```

Edite o arquivo `.env` conforme necessário. As configurações padrão já funcionam para desenvolvimento local.

**Variáveis disponíveis:**
- `DB_URL` - URL de conexão do banco de dados (padrão: `jdbc:h2:mem:casadb`)
- `DB_DRIVER` - Driver do banco de dados (padrão: `org.h2.Driver`)
- `DB_USERNAME` - Usuário do banco (padrão: `sa`)
- `DB_PASSWORD` - Senha do banco (padrão: vazio)
- `JPA_DDL_AUTO` - Estratégia de criação do schema (padrão: `update`)
- `JPA_SHOW_SQL` - Exibir SQL no console (padrão: `true`)
- `H2_CONSOLE_ENABLED` - Habilitar console H2 (padrão: `true`)
- `H2_CONSOLE_PATH` - Caminho do console H2 (padrão: `/h2-console`)
- `SERVER_PORT` - Porta do servidor (padrão: `8080`)

**Nota:** O arquivo `.env` está no `.gitignore` e não será versionado. Use o `env.example` como referência.

📖 **Para mais detalhes sobre configuração de variáveis de ambiente, consulte [ENV_SETUP.md](casa_automatica/ENV_SETUP.md)**

3. Execute a aplicação:

**Usando Maven Wrapper (recomendado):**
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

**Usando Maven instalado:**
```bash
mvn spring-boot:run
```

**Usando IDE:**
- Abra o projeto no IntelliJ IDEA
- Execute a classe `CasaAutomaticaApplication`

3. A aplicação estará disponível em:
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - H2 Console: `http://localhost:8080/h2-console`

### Build do projeto

Para compilar o projeto:
```bash
mvn clean install
```

## Limitações

- Banco de dados em memória (dados são perdidos ao reiniciar a aplicação)
- Não possui autenticação/autorização implementada
- Senha do usuário é armazenada em texto plano (não recomendado para produção)

## Carta Desafio

A implementação da carta desafio foi feita através da inutilização do aparelho fora do período no dia e hora determinado pelo usuário e através da inutilização do aparelho dentro de um período determinado pelo usuário.

A funcionalidade é implementada através do campo `periodo` na entidade `Tarefa`, que pode ter os valores:
- `"dentro"` - Tarefa só pode ser executada dentro do período especificado
- `"fora"` - Tarefa só pode ser executada fora do período especificado
- `"sem restricao"` - Tarefa pode ser executada a qualquer momento

Os campos `inicioPeriodo` e `fimPeriodo` definem o intervalo de tempo permitido ou bloqueado, dependendo do valor de `periodo`.
