
---

````markdown
# 📚 Literalura

Aplicação desenvolvida em Spring Boot para gerenciar dados de literatura, utilizando PostgreSQL como banco de dados.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL
- Maven

## 💻 Requisitos

- Java 17 ou superior
- Maven
- PostgreSQL

## ⚙️ Configuração do banco de dados

Antes de rodar a aplicação, você precisa ter um banco PostgreSQL criado.  
Por exemplo:

```sql
CREATE DATABASE literatura;
````

Certifique-se também que você possui um usuário e senha válidos para acessar o banco.

## 🌎 Variáveis de ambiente

Configure as seguintes variáveis no seu terminal ou shell antes de executar:

```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=literatura
export DB_USERNAME=postgres
export DB_PASSWORD=sua_senha_aqui
```

**💡 Dica:** Você pode criar um arquivo `.env` ou script `.sh` para facilitar o setup.

## ▶️ Rodando a aplicação

Após configurar as variáveis de ambiente, execute:

```bash
./mvnw spring-boot:run
```

Ou, se preferir usar Maven instalado globalmente:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

## 🛠️ Estrutura principal

* `src/main/java/com/erickcosta/literatura`: código-fonte principal
* `src/main/resources/application.properties`: configurações da aplicação
* `pom.xml`: dependências e plugins do projeto

## 📝 Contribuições

Sinta-se à vontade para abrir issues ou pull requests! 🚀

## 📄 Licença

Este projeto está sob a licença MIT.

---

### 💬 Contato

Erick Costa — [LinkedIn](https://www.linkedin.com/in/ericktcosta/) — [erick.pt.costa.dev@gmail.com](mailto:erick.pt.costa.dev@gmail.com)

```

---

Se quiser, posso já gerar um arquivo `README.md` pronto para download, ou criar uma branch para você adicionar direto. Quer que eu faça? 🚀
```
