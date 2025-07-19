# Golden Raspberry Awards API

Este projeto implementa uma API RESTful para análise dos vencedores da categoria **Pior Filme** do Golden Raspberry Awards. Ele calcula os **menores e maiores intervalos** entre prêmios consecutivos dos produtores vencedores.

## ✅ Tecnologias utilizadas

- Java 21
- Spring Boot 3.2.x
- Maven
- H2 (banco de dados em memória)
- Apache Commons CSV
- JUnit 5 + Spring Boot Test

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- Java 21 instalado
- Maven instalado

### Passos

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/golden-raspberry-api.git
cd golden-raspberry-api
```

2. **Adicione o arquivo `movielist.csv` no caminho:**

```
src/main/resources/movielist.csv
```

Exemplo de conteúdo mínimo do arquivo:

```csv
year;title;studios;producers;winner
2008;Some Movie;Studio A;Producer 1;yes
2009;Another Movie;Studio B;Producer 1;yes
1990;Old Movie;Studio C;Producer 2;yes
2010;Comeback Movie;Studio C;Producer 2;yes
```

> ⚠️ Use `;` como separador. A primeira linha deve conter os cabeçalhos: `year;title;studios;producers;winner`.

3. **Compile e execute a aplicação:**

```bash
./mvnw spring-boot:run
```

Ou, se você usa Maven instalado no sistema:

```bash
mvn spring-boot:run
```

---

## 🔍 Como acessar a API

Após iniciar a aplicação, acesse:

```
GET http://localhost:8080/awards/intervals
```

### Exemplo de resposta:

```json
{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    }
  ],
  "max": [
    {
      "producer": "Producer 2",
      "interval": 20,
      "previousWin": 1990,
      "followingWin": 2010
    }
  ]
}
```

---

## 🧪 Executando os testes de integração

Os testes de integração garantem que os dados carregados do CSV estão sendo processados corretamente pela API.

### Para executar os testes:

```bash
mvn test
```

> O teste verifica a presença de chaves `"min"` e `"max"` na resposta e valida que o produtor `"Producer 1"` aparece com intervalo de `1` (com base no exemplo acima).

---

## 📂 Estrutura do projeto

```
src
├── main
│   ├── java
│   │   └── com.goldenraspberry
│   │       ├── controller
│   │       ├── domain
│   │       ├── repository
│   │       ├── service
│   │       └── RaspberryAwardsApplication.java
│   └── resources
│       └── movielist.csv
└── test
    └── java
        └── com.goldenraspberry
            └── AwardsIntegrationTest.java
```

---

## 📄 Licença

Este projeto é apenas para fins de avaliação técnica.