<h1 align="center">
  Tech Challenge 02
</h1>

Monolito para gestão de autoatendimento para lanchonetes. Este desafio faz parte da Fase 2 - Gerenciamento de Kubernetes da FIAP.

## Colaboradores

- Vinícius Teixeira Sant' Anna - vinitsantanna@gmail.com - RM357722
- Welington Carlos Alves de Almeida Filho - wcfilho98@gmail.com - RM357115
- Ludionei da Penha dos Reis - ludioneireis@gmail.com - RM357306
- Francisco Washington de Almeida Oliveira - franciscowashington59@gmail.com - RM357075
- Charles Aparecido da Paixão de Jesus Campagnaro - charles.campag@gmail.com - RM357029


## Video no Youtube com explicação da Infraestrutura:
- [Infraestrutura Kubernetes](https://www.youtube.com/watch?v=cu73283Rkl0)
## Tecnologias

- [Java](https://docs.oracle.com/en/java/javase/17/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot docker-compose](https://spring.io/blog/2023/06/21/docker-compose-support-in-spring-boot-3-1)
- [MySql](https://dev.mysql.com/doc/)
- [Liquibase](https://docs.liquibase.com/home.html)
- [Swagger](https://swagger.io/docs/)
- [QR Code do Mercado Pago](https://www.mercadopago.com.br/developers/pt/reference/qr-dynamic/_instore_orders_qr_seller_collectors_user_id_pos_external_pos_id_qrs/post)


## Práticas adotadas

- Domain-Driven Design (DDD)
- Arquitetura Limpa
- Consultas com filtros dinâmicos
- API reativa na web e na camada de banco
- Uso de DTOs para a API
- Geração automática do Swagger

## Configuração do Ambiente

### Variáveis de Ambiente

- `SPRING.DATASOURCE.URL`: URL de conexão com o banco de dados.
- `SPRING.DATASOURCE.USERNAME`: Usuário do banco de dados.
- `SPRING.DATASOURCE.PASSWORD`: Senha do banco de dados.
- `INTEGRATION.MERCADOPAGO.URL`: URL da API do MercadoPago.
- `INTEGRATION.MERCADOPAGO.ACCESSTOKEN`: Token de acesso da API do MercadoPago.

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de que você possui os seguintes pré-requisitos instalados e configurados:

### 1. [Kubectl](https://kubernetes.io/docs/tasks/tools/)
- Ferramenta de linha de comando para interagir com clusters Kubernetes.
- **Instalação no Windows*:
  ```bash
  choco install kubernetes-cli

### 2. [Minikube](https://minikube.sigs.k8s.io/docs/)
- Ferramenta para executar um cluster Kubernetes local.
- **Instalação no Windows*:
  ```bash
  choco install minikube
### 3. Verificando a instalação
- Depois de instalar os pré-requisitos, você pode verificar se tudo está configurado corretamente rodando os seguintes comandos:
- **Instalação no Windows*:
  ```bash
   minikube start
   kubectl version --client
   minikube status
  
### Executando o Cluster Kubernetes

1. Na pasta raiz do projeto execute:
``` sh
kubectl apply -f kuber
```

2. Valide se os containers estão READY:
``` sh
kubectl get pods
```
3. Execute o comando abaixo para conectar a sua rede local a rede do Minikube
``` sh
   minikube tunnel
```

## Documentação
A documentação da API pode ser acessada pelo [Swagger](http://localhost:8080/swagger-ui.html) quando a aplicação está em execução.


## APIs

### Criar cliente
- Método: POST
- Endpoint: `/lanchonete/customer`
- Descricação: Cria um cliente novo
- Request Body:

```json
{
  "name": "string",
  "cpf": "11111111111",
  "email_address": "string"
}
```

### Buscar o cliente
- Método: GET
- Endpoint: `/lanchonete/customer/{cpf}`
- Parâmetro: `cpf`
- Descricação: Busca um cliente por cpf
- Response Body:
```json
{
  "id": "UUID do cliente",
  "name": "string",
  "cpf": "11111111111",
  "email_address": "string"
}

```

### Buscar produto por categoria
- Método: GET
- Endpoint: `/lanchonete/category/{categoryId}`
- Parâmetro: `categoryId`
- Descricação: Busca os produtos por uma categoria
- Response Body:
```json
{
  "description": "string",
  "products": [
    {
      "product_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "category_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "name": "string",
      "price": 0,
      "description": "string"
    }
  ],
  "productCategoryId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}

```


### Criar produto
- Método: POST
- Endpoint: `/lanchonete/product`
- Descricação: Cria um novo produto
- Request Body:

```json
{
  "category_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "price": 0,
  "description": "string"
}
```

### Alterar produto
- Método: PUT
- Endpoint: `/lanchonete/product`
- Descricação: Altera um produto
- Request Body:

```json
{
  "product_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "category_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "price": 0,
  "description": "string"
}
```

### Deleta um produto
- Método: DELETE
- Endpoint: `/lanchonete/product`
- Parâmetro: `productId`
- Descricação: Deleta um produto
- Request Body: `204 No Content`



### Enviar pedido
- Método: POST
- Endpoint: `/lanchonete/order`
- Descricação: Cria novo pedido
- Request Body:
```json
{
  "orderSnackId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "progress": "string",
  "createdAt": "2024-08-10T20:41:40.553Z",
  "customerId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "customerName": "string",
  "cpf": "string",
  "items": [
    {
      "orderSnackItemId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "amount": 0,
      "productName": "string",
      "price": 0,
      "quantity": 1,
      "product_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    }
  ]
}
```


### Listar pedidos
- Método: GET
- Endpoint: `/lanchonete/ordersnack?progress{progress}&cpf{cpf}`
- Parâmetro: Valores opcionais `progress` e `cpf`
- Descricação: Lista todos os pedidos
- Response Body:
```json
[
  {
    "orderSnackId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "progress": "string",
    "createdAt": "2024-08-10T20:50:50.244Z",
    "customerId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "customerName": "string",
    "cpf": "string",
    "items": [
      {
        "orderSnackItemId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "amount": 0,
        "productName": "string",
        "price": 0,
        "quantity": 1,
        "product_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
      }
    ]
  }
]

```

## Licença

Este projeto é licenciado sob a MIT License - veja o arquivo [LICENSE](https://opensource.org/license/mit) para mais detalhes.
