<h1 align="center">
  Tech Challenge 01
</h1>

Monolito para gestão de autoatendimento para lanchonetes. [Este desafio](https://on.fiap.com.br/mod/conteudoshtml/view.php?id=407435&c=11255&sesskey=0W0NdVRNSB) faz parte da Fase 1 - Welcome to Software Architecture da pós graduação em Software Architecture da FIAP.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot docker-compose](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)
- [MySql](https://dev.mysql.com/doc/)
- [Swagger](https://swagger.io/docs/)
- [QR Code do Mercado Pago](https://www.mercadopago.com.br/developers/pt/reference/qr-dynamic/_instore_orders_qr_seller_collectors_user_id_pos_external_pos_id_qrs/post)

## Práticas adotadas

- Domain-Driven Design (DDD)
- Arquitetura Hexagonal
- Consultas com filtros dinâmicos
- API reativa na web e na camada de banco
- Uso de DTOs para a API
- Geração automática do Swagger

Acessar a interface do Swagger
Após configurar o Swagger, você pode acessar a interface do Swagger UI na seguinte URL:

Para consumo das API acessar o [Swagger](http://localhost:8080/swagger-ui.html).

```
Para criação do cliente:

http POST :8080/lanchonete/customer

{
  "name": "string",
  "cpf": "29295965612",
  "email_address": "string"
}

```

```
Para busca de produtos por categoria:

http GET :8080/lanchonete/categoria/{categoriaID}

```