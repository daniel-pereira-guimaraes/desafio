# DESAFIO - API de Pedido

## 🏃 Rodando Localmente

Clone o projeto:

```bash
git clone https://github.com/daniel-pereira-guimaraes/desafio.git
cd desafio
```

## Requisitos para rodar localmente

+ Java 21
+ Maven 3.8+
+ Porta 8080 livre


## Rodando localmente

+ Build e package com Maven:
  ```
  ./mvnw clean package
  # OU
  mvn clean package
  ```

## Rodar a aplicação:

```
java -jar target/desafio-0.0.1-SNAPSHOT.jar
```


## Rodando com Docker

```
docker build -t desafio-app . 
docker run -p 8080:8080 desafio-app
```

##Acesse a aplicação em:

+ API: http://localhost:8080
+ Swagger UI: http://localhost:8080/swagger-ui.html
+ Healthcheck: http://localhost:8080/actuator/health
