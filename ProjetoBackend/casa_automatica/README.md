# 🏠 Casa Automática - Sistema de Automação Residencial

Sistema backend para automação residencial com IoT, desenvolvido com Spring Boot.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Java 17** ou superior ([Download](https://adoptium.net/))
- **Maven** (opcional, o projeto inclui Maven Wrapper)
- **Git** para clonar o repositório

## 🚀 Como executar o projeto

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd casa_automatica
```

### 2. Configure as variáveis de ambiente

O projeto usa um arquivo `.env` para configuração. Copie o arquivo de exemplo:

**Windows (PowerShell):**
```powershell
Copy-Item .env.example .env
```

**Windows (CMD):**
```cmd
copy .env.example .env
```

**Linux/Mac:**
```bash
cp .env.example .env
```

> 💡 **Nota:** As configurações padrão já funcionam para desenvolvimento local. Você pode editar o `.env` se precisar customizar.

### 3. Execute o projeto

**Windows (PowerShell/CMD):**
```powershell
./mvnw spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

> ⏱️ **Aguarde:** A primeira execução pode demorar alguns minutos, pois o Maven irá baixar todas as dependências.

### 4. Acesse a aplicação

Após a inicialização bem-sucedida, você verá a mensagem:
```
Started CasaAutomaticaApplication in X.XXX seconds
```

A aplicação estará disponível em: **http://localhost:8080**

## 📚 Documentação da API

### Swagger UI (Interface Interativa)
Acesse a documentação interativa da API:
- **URL:** http://localhost:8080/swagger-ui.html

### OpenAPI JSON
Especificação OpenAPI em formato JSON:
- **URL:** http://localhost:8080/v3/api-docs

## 🗄️ Banco de Dados

O projeto usa **H2 Database** em memória para desenvolvimento.

### Console H2
Acesse o console web do H2:
- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:casadb`
- **Username:** `sa`
- **Password:** *(deixe em branco)*

## 🔌 Endpoints da API

### Usuários
- `GET /api/usuarios` - Listar todos os usuários
- `GET /api/usuarios/{id}` - Buscar usuário por ID
- `POST /api/usuarios` - Criar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário
- `DELETE /api/usuarios/{id}` - Deletar usuário

### Categorias
- `GET /api/categorias` - Listar todas as categorias
- `GET /api/categorias/{id}` - Buscar categoria por ID
- `POST /api/categorias` - Criar nova categoria
- `PUT /api/categorias/{id}` - Atualizar categoria
- `DELETE /api/categorias/{id}` - Deletar categoria

### Aparelhos
- `GET /api/aparelhos` - Listar todos os aparelhos
- `GET /api/aparelhos/{id}` - Buscar aparelho por ID
- `GET /api/aparelhos/usuario/{usuarioId}` - Listar aparelhos por usuário
- `POST /api/aparelhos` - Criar novo aparelho
- `PUT /api/aparelhos/{id}` - Atualizar aparelho
- `DELETE /api/aparelhos/{id}` - Deletar aparelho

### Tarefas
- `GET /api/tarefas` - Listar todas as tarefas
- `GET /api/tarefas/{id}` - Buscar tarefa por ID
- `GET /api/tarefas/aparelho/{aparelhoId}` - Listar tarefas por aparelho
- `POST /api/tarefas` - Criar nova tarefa
- `PUT /api/tarefas/{id}` - Atualizar tarefa
- `DELETE /api/tarefas/{id}` - Deletar tarefa

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **H2 Database** - Banco de dados em memória
- **Hibernate** - ORM
- **MapStruct 1.5.5** - Mapeamento de DTOs
- **Lombok** - Redução de boilerplate
- **Springdoc OpenAPI 2.7.0** - Documentação da API
- **dotenv-java 3.0.0** - Gerenciamento de variáveis de ambiente

## 📦 Estrutura do Projeto

```
casa_automatica/
├── src/
│   ├── main/
│   │   ├── java/viniccius13/casa_automatica/
│   │   │   ├── config/          # Configurações
│   │   │   ├── controller/      # Controllers REST
│   │   │   ├── dtos/            # Data Transfer Objects
│   │   │   ├── exception/       # Tratamento de exceções
│   │   │   ├── mappers/         # MapStruct mappers
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositórios JPA
│   │   │   └── service/         # Lógica de negócio
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yml
│   └── test/                    # Testes
├── .env.example                 # Exemplo de variáveis de ambiente
├── ENV_SETUP.md                 # Documentação detalhada das variáveis
├── pom.xml                      # Dependências Maven
└── README.md                    # Este arquivo
```

## ⚙️ Configuração Avançada

### Variáveis de Ambiente

Todas as variáveis disponíveis estão documentadas em `ENV_SETUP.md`.

Principais variáveis:

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `SERVER_PORT` | Porta do servidor | `8080` |
| `DB_URL` | URL do banco de dados | `jdbc:h2:mem:casadb` |
| `JPA_DDL_AUTO` | Estratégia de criação do schema | `update` |
| `H2_CONSOLE_ENABLED` | Habilitar console H2 | `true` |

### Mudar a Porta do Servidor

Edite o arquivo `.env` e altere:
```env
SERVER_PORT=9090
```

### Usar Banco de Dados Persistente

Para usar H2 em arquivo (dados persistem entre reinicializações):

```env
DB_URL=jdbc:h2:file:./data/casadb
```

## 🧪 Executar Testes

```bash
./mvnw test
```

## 📦 Gerar o JAR

```bash
./mvnw clean package
```

O arquivo JAR será gerado em: `target/casa_automatica-0.0.1-SNAPSHOT.jar`

### Executar o JAR

```bash
java -jar target/casa_automatica-0.0.1-SNAPSHOT.jar
```

## 🐛 Troubleshooting

### Erro: "Port 8080 was already in use"

A porta 8080 já está sendo usada por outro processo.

**Solução 1:** Pare o processo que está usando a porta

**Windows:**
```powershell
Get-NetTCPConnection -LocalPort 8080 | Select-Object -ExpandProperty OwningProcess | ForEach-Object { Stop-Process -Id $_ -Force }
```

**Linux/Mac:**
```bash
lsof -ti:8080 | xargs kill -9
```

**Solução 2:** Mude a porta no arquivo `.env`

### Erro: "No qualifying bean of type 'Mapper'"

Os mappers do MapStruct não foram gerados.

**Solução:**
```bash
./mvnw clean compile
```

### Erro: "Malformed entry" no arquivo .env

O arquivo `.env` tem problemas de encoding.

**Solução:** Recrie o arquivo a partir do `env.example`:
```bash
rm .env
cp env.example .env
```

## 📝 Exemplos de Uso

### Criar um Usuário

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "senha123"
  }'
```

### Criar uma Categoria

```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{
    "tipo": "Iluminação",
    "descricao": "Dispositivos de iluminação"
  }'
```

### Criar um Aparelho

```bash
curl -X POST http://localhost:8080/api/aparelhos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Lâmpada Sala",
    "descricao": "Lâmpada inteligente da sala",
    "usuarioId": 1,
    "categoriaId": 1
  }'
```

### Criar uma Tarefa

```bash
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Acender luz às 18h",
    "hora": "18:00",
    "repetir": "DIARIO",
    "aparelhoId": 1
  }'
```

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

## 👥 Autor

**viniccius13**

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!


