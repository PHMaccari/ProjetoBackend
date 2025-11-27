# Configuração de Variáveis de Ambiente

Este projeto utiliza variáveis de ambiente para configuração. As variáveis podem ser definidas através de um arquivo `.env` na raiz do projeto.

## Como Configurar

### 1. Criar o arquivo .env

Copie o arquivo `env.example` para `.env`:

**Windows (PowerShell):**
```powershell
Copy-Item env.example .env
```

**Windows (CMD):**
```cmd
copy env.example .env
```

**Linux/Mac:**
```bash
cp env.example .env
```

### 2. Editar as variáveis (opcional)

Abra o arquivo `.env` e ajuste as configurações conforme necessário. As configurações padrão já funcionam para desenvolvimento local.

## Variáveis Disponíveis

### Banco de Dados

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `DB_URL` | URL de conexão do banco de dados | `jdbc:h2:mem:casadb` |
| `DB_DRIVER` | Driver JDBC do banco | `org.h2.Driver` |
| `DB_USERNAME` | Usuário do banco de dados | `sa` |
| `DB_PASSWORD` | Senha do banco de dados | (vazio) |

### JPA/Hibernate

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `JPA_DDL_AUTO` | Estratégia de criação do schema<br>Opções: `create`, `create-drop`, `update`, `validate`, `none` | `update` |
| `JPA_SHOW_SQL` | Exibir SQL no console | `true` |

### Console H2

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `H2_CONSOLE_ENABLED` | Habilitar console web do H2 | `true` |
| `H2_CONSOLE_PATH` | Caminho do console H2 | `/h2-console` |

### Servidor

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `SERVER_PORT` | Porta do servidor | `8080` |

### Aplicação

| Variável | Descrição | Valor Padrão |
|----------|-----------|--------------|
| `APP_NAME` | Nome da aplicação | `casa_automatica` |
| `TAREFA_PERMITIDO_INICIO` | Horário de início permitido para tarefas | `08:00` |
| `TAREFA_PERMITIDO_FIM` | Horário de fim permitido para tarefas | `18:00` |

## Exemplos de Configuração

### Desenvolvimento Local (Padrão)

```env
DB_URL=jdbc:h2:mem:casadb
DB_DRIVER=org.h2.Driver
DB_USERNAME=sa
DB_PASSWORD=
JPA_DDL_AUTO=update
JPA_SHOW_SQL=true
H2_CONSOLE_ENABLED=true
SERVER_PORT=8080
```

### Banco H2 em Arquivo (Persistente)

```env
DB_URL=jdbc:h2:file:./data/casadb
DB_DRIVER=org.h2.Driver
DB_USERNAME=sa
DB_PASSWORD=
JPA_DDL_AUTO=update
JPA_SHOW_SQL=false
H2_CONSOLE_ENABLED=true
SERVER_PORT=8080
```

### Produção (Exemplo com MySQL)

```env
DB_URL=jdbc:mysql://localhost:3306/casa_automatica?useSSL=false&serverTimezone=UTC
DB_DRIVER=com.mysql.cj.jdbc.Driver
DB_USERNAME=root
DB_PASSWORD=sua_senha_segura
JPA_DDL_AUTO=validate
JPA_SHOW_SQL=false
H2_CONSOLE_ENABLED=false
SERVER_PORT=8080
```

**Nota:** Para usar MySQL ou outro banco, você precisa adicionar a dependência correspondente no `pom.xml`.

## Segurança

⚠️ **IMPORTANTE:**

- O arquivo `.env` está no `.gitignore` e **NÃO deve ser versionado**
- Nunca compartilhe seu arquivo `.env` com senhas reais
- Use o `env.example` como template sem informações sensíveis
- Em produção, considere usar variáveis de ambiente do sistema ou serviços de gerenciamento de segredos

## Como Funciona

O projeto carrega as variáveis do arquivo `.env` através da biblioteca `dotenv-java` na classe `CasaAutomaticaApplication.java`:

```java
Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()  // Não gera erro se .env não existir
        .load();

// Define as variáveis de ambiente do sistema
dotenv.entries().forEach(entry -> {
    if (System.getProperty(entry.getKey()) == null) {
        System.setProperty(entry.getKey(), entry.getValue());
    }
});
```

As variáveis são então usadas no `application.yml` com a sintaxe `${VARIAVEL:valor_padrao}`.

## Troubleshooting

### O arquivo .env não está sendo carregado

1. Verifique se o arquivo `.env` está na pasta `casa_automatica` (mesma pasta do `pom.xml`)
2. Certifique-se de que o arquivo não tem extensão adicional (não deve ser `.env.txt`)
3. Reinicie a aplicação após criar/editar o `.env`

### Variáveis não estão sendo aplicadas

1. Verifique se não há espaços antes ou depois do `=` no arquivo `.env`
2. Certifique-se de que os nomes das variáveis estão corretos (case-sensitive)
3. Verifique se não há caracteres especiais ou quebras de linha inválidas

### Erro de conexão com o banco

1. Verifique se a `DB_URL` está correta
2. Para H2 em arquivo, certifique-se de que o diretório existe
3. Para outros bancos, verifique se o servidor está rodando e acessível

## Suporte

Para mais informações sobre as configurações do Spring Boot, consulte:
- [Spring Boot Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [H2 Database](https://www.h2database.com/)
- [dotenv-java](https://github.com/cdimascio/dotenv-java)

