# ✅ Checklist de Deploy/Clone

Use este checklist para garantir que o projeto funcione em qualquer máquina após clonar do GitHub.

## 📋 Antes de Fazer Push para o GitHub

- [x] ✅ `.gitignore` configurado corretamente
- [x] ✅ Arquivo `.env` está no `.gitignore` (não será versionado)
- [x] ✅ Arquivo `env.example` está presente (será versionado)
- [x] ✅ `README.md` com instruções completas criado
- [x] ✅ `ENV_SETUP.md` com documentação das variáveis presente
- [x] ✅ Maven Wrapper (`mvnw` e `mvnw.cmd`) incluídos
- [x] ✅ `pom.xml` com todas as dependências configurado
- [x] ✅ Código compila sem erros

## 📥 Após Clonar em Nova Máquina

### 1. Verificar Pré-requisitos
```bash
# Verificar versão do Java (deve ser 17+)
java -version
```

### 2. Criar arquivo .env
```bash
# Windows PowerShell
Copy-Item .env.example .env

# Linux/Mac
cp .env.example .env
```

### 3. Dar permissão ao Maven Wrapper (Linux/Mac apenas)
```bash
chmod +x mvnw
```

### 4. Compilar o projeto
```bash
./mvnw clean compile
```

### 5. Executar o projeto
```bash
./mvnw spring-boot:run
```

### 6. Verificar se está funcionando
- Abrir navegador em: http://localhost:8080/swagger-ui.html
- Deve aparecer a documentação da API

## 🎯 Comandos Úteis

### Limpar e recompilar
```bash
./mvnw clean compile
```

### Executar testes
```bash
./mvnw test
```

### Gerar JAR executável
```bash
./mvnw clean package
```

### Executar o JAR
```bash
java -jar target/casa_automatica-0.0.1-SNAPSHOT.jar
```

## 🔍 O que DEVE estar no GitHub

✅ **Incluir:**
- `src/` (código fonte)
- `pom.xml` (dependências)
- `mvnw` e `mvnw.cmd` (Maven Wrapper)
- `.mvn/wrapper/maven-wrapper.properties`
- `env.example` (exemplo de configuração)
- `README.md` (documentação)
- `ENV_SETUP.md` (documentação das variáveis)
- `.gitignore` (arquivos a ignorar)

❌ **NÃO incluir:**
- `.env` (variáveis de ambiente locais)
- `target/` (arquivos compilados)
- `.idea/` (configurações do IntelliJ)
- `.vscode/` (configurações do VS Code)
- `*.iml` (arquivos do IntelliJ)

## 🚨 Problemas Comuns e Soluções

### Problema: "mvnw: Permission denied" (Linux/Mac)
**Solução:**
```bash
chmod +x mvnw
```

### Problema: "Port 8080 already in use"
**Solução Windows:**
```powershell
Get-NetTCPConnection -LocalPort 8080 | Select-Object -ExpandProperty OwningProcess | ForEach-Object { Stop-Process -Id $_ -Force }
```

**Solução Linux/Mac:**
```bash
lsof -ti:8080 | xargs kill -9
```

### Problema: "No qualifying bean of type Mapper"
**Solução:**
```bash
./mvnw clean compile
```

### Problema: "Malformed entry" no .env
**Solução:**
```bash
# Deletar e recriar o .env
rm .env
cp .env.example .env
```

## ✨ Teste Final

Após clonar e configurar, execute:

```bash
# 1. Criar .env
cp .env.example .env

# 2. Compilar
./mvnw clean compile

# 3. Executar
./mvnw spring-boot:run

# 4. Em outro terminal, testar a API
curl http://localhost:8080/api/usuarios
```

Se você receber uma resposta JSON (mesmo que vazia `[]`), está tudo funcionando! 🎉

## 📝 Notas Importantes

1. **Java 17+** é obrigatório
2. **Não precisa instalar Maven** - o projeto usa Maven Wrapper
3. **Primeira execução demora** - Maven baixa todas as dependências
4. **Banco de dados é em memória** - dados são perdidos ao reiniciar
5. **Porta padrão é 8080** - pode ser alterada no `.env`

---

**Status do Projeto:** ✅ Pronto para Deploy


