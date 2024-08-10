<h1 align="center">
  Tech Challenge 01
</h1>

Monolito para gestão de autoatendimento para lanchonetes. [Este desafio](https://on.fiap.com.br/mod/conteudoshtml/view.php?id=407435&c=11255&sesskey=0W0NdVRNSB) faz parte da Fase 1 - Welcome to Software Architecture da pós graduação em Software Architecture da FIAP.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot docker-compose](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)
- [MySql](https://dev.mysql.com/doc/)
- [Liquibase](https://docs.liquibase.com/home.html)
- [Swagger](https://swagger.io/docs/)
- [QR Code do Mercado Pago](https://www.mercadopago.com.br/developers/pt/reference/qr-dynamic/_instore_orders_qr_seller_collectors_user_id_pos_external_pos_id_qrs/post)

## Práticas adotadas

- Domain-Driven Design (DDD)
- Arquitetura Hexagonal
- Consultas com filtros dinâmicos
- API reativa na web e na camada de banco
- Uso de DTOs para a API
- Geração automática do Swagger



Criando o banco via linha de comando:

- mysql -u root -p 
- create database lanchonete;

Verifique se foi criado corretamento com:

- show databases;

Dê run no projeto.

Agora acesse a interface do Swagger
Após configurar o Swagger, você pode acessar a interface do Swagger UI na seguinte URL:

Para consumo das API acessar o [Swagger](http://localhost:8081/swagger-ui.html).


PAINEL CLIENTE:

```
Para criação do cliente:

http POST :8081/lanchonete/customer

{
  "name": "string",
  "cpf": "29295965612",
  "email_address": "string"
}

```

```
Para buscar o cliente:

http GET :8081/lanchonete/customer

  "cpf": "29295965612",

```

```
Para busca de produtos por categoria:

http GET :8081/lanchonete/categoria/{categoriaID}

```

```
Para enviar pedido:

http POST :8081/order

{
  "orderSnackId": "string",
  "progress": "string",
  "createdAt": "2024-08-09T00:52:30.887Z",
  "customerId": "string",
  "customerName": "string",
  "cpf": "string",
  "items": [
    {
      "orderSnackItemId": "string",
      "amount": 0,
      "productName": "string",
      "price": 0,
      "quantity": 1,
      "product_id": "string"
    }
  ]
}

```


-----------------------------------------------------------

PAINEL COZINHA
 
```
Para buscar os pedidos

http GET :8081/lanchonete/order

(vai listar todos)

para buscar com filtro:

progress (string) Available values : RECEIVED, IN_PREPARATION, READY, FINISHED

ou com o cpf de um cliente:

cpf (string)

```
