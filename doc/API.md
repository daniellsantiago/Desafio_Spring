# Endpoints

## Cadastrar Produtos:

`POST /api/v1/products`

`201 CREATED`

### Request Body:
```JSON
[
  {
    "name": "string",
    "category": "string",
    "brand": "string",
    "price": "float",
    "quantity": "integer",
    "freeShipping": "boolean",
    "prestige": "string"
  }
]
```

* Campos obrigatórios: `name`, `category`, `brand`, `price`, `quantity`, `freeShipping`, `prestige`
* `prestige`: valores de `*` à `*****`
* `quatity` e `price`: apenas valores positivos

### Response Body:
```JSON
[
  {
    "productId": "UUID",
    "name": "string",
    "quantity": "integer"
  }
]
```

## Listar Produtos

`GET /api/v1/products`

`200 OK`

### Response Body:
```JSON
[
  {
    "productId": "UUID",
    "name": "string",
    "category": "string",
    "brand": "string",
    "price": "float",
    "quantity": "integer",
    "freeShipping": "boolean",
    "prestige": "string"
  }
]
```

### Parâmetros opcionais

* `category`

* `freeShipping`: true ou false

* `prestige`: valores de `*` à `*****`

* `order`: 0, 1, 2 ou 3

  0: Alfabético crescente
  
  1: Alfabético decrescente

  2: Maior a menor preço

  3: Menor a maior preço

#### Combinação de Parâmetros permitida

1. category
2. category + freeShipping
3. freeShipping + prestige

## Compra de Produto

`POST /api/v1/purchase`

`201 CREATED`

### Request Body:
```JSON
{
  "productsPurchaseRequest": [
    {
      "productId": "UUID",
      "quantity": "integer"
    }
  ]
}
```

* Campos obrigatórios: `productId`, `quantity`
* `quatity`: apenas valores positivos

### Response Body:

```JSON
{
  "ticket": {
    "id": "UUID",
    "products": [
        {
          "name": "string",
          "category": "string",
          "brand": "string",
          "price": "float",
          "quantity": "integer",
          "freeShipping": "boolean",
          "prestige": "string"
        }
    ],
    "total": "float"
  }
}
```

## Cadastrar Cliente

`POST /api/v1/clients`

`201 CREATED`

### Request Body:
```JSON
[
  {
    "name": "string",
    "cpf": "string",
    "state": "string"
  }
]
```

* Campos obrigatórios: `name`, `cpf`, `state`
* `state`: tamanho 2 (Estado abreviado)

### Response Body:

```JSON
[
  {
    "id": "UUID",
    "name": "string",
    "cpf": "string",
    "state": "string"
  }
]
```

## Listar Clientes

`GET /api/v1/clients`

`200 OK`

### Response Body:
```JSON
[
  {
    "id": "UUID",
    "name": "string",
    "cpf": "string",
    "state": "string"
  }
]
```

### Parâmetros opcionais

* `state`: tamanho 2 (Estado abreviado)

## Buscar Cliente por ID

`GET /api/v1/clients/{id}`

`200 OK`

### Response Body:
```JSON
{
  "id": "UUID",
  "name": "string",
  "cpf": "string",
  "state": "string"
}
```