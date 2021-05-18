# Final Challenge Demo

Development of an API RESTful with the purpose of updating marketing processes and the requirements imposed by the international company "Delux", dedicated to the distribution of luxury vehicles, buses, trucks and their respective spare parts. This company has its headquarters in Brazil, and has different headquarters in different countries.


## 🚀 Postman Collection

- Import this collection into Postman.
- Set environment variables.
  - {{token}} - Leave empty.
  - {{hostname}} : localhost:8080/ - or Fury URL.
  
_https://www.getpostman.com/collections/50b92cd36c867030dd98_

# To access the app it is necessary to authenticate.
> **POST /api/v1/login**

_Request params:_ - (Needed to access)

- username
- password

_Query parameters example_

* "user" : "gembleton0"
* "password" : "*******"

_Response body:_

```json
{
  "user": "gembleton0",
  "token": "6GllcyI6WyIxIl0sImlhdCITk5NywiZXhwIjoxNjIxODg5OTk3fQ.GHxo79uiFTwI0yQ33GCUbB..."
}
```

### ⌨️ Application endpoints 

### ml-parts-01
> **GET /api/v1/parts/list**

_You can get a list of all the parts._
```
{
    "parts": [
        {
            "partCode": 1,
            "description": "Llanta",
            "maker": "Fiat",
            "quantity": 12,
            "discountType": "AA",
            "widthDimension": 100,
            "tallDimension": 100,
            "longDimension": 100,
            "netWeight": 100,
            "normalPrice": 762.0,
            "urgentPrice": 900.0,
            "lastModification": "2021-03-02",
            "partStatus": "N"
        },
        {
            "partCode": 2,
            "description": "Puerta trasera derecha",
            "maker": "Fiat",
            "quantity": 5,
            "discountType": "AA",
            "widthDimension": 30,
            "tallDimension": 40,
            "longDimension": 30,
            "netWeight": 40,
            "normalPrice": 862.0,
            "urgentPrice": 900.0,
            "lastModification": "2021-03-02",
            "partStatus": "N"
        },
    ]
}
```
_It also has access through input parameters with the following filters:_
-if required date and qurytype are required.

* queryType: 
  - P - Spare parts modified from the date of consultation to the current.
    - order:
      - 1 - Alphabetically ascending by description.
      - 2 - Alphabetically descending by description.
      - 3 - Alphabetically descending by last modification.
  - V - Spare parts that varied in price from the consultation date to the current one.
    - order:
      - 1 - Alphabetically ascending by description.
      - 2 - Alphabetically descending by description.
      - 3 - Alphabetically descending by last price modification.
  - C - Complete.
* date: 01/01/2021 (dd/MM/yyyy)


### ml-parts-02
> **GET api/v1/parts/orders?dealerNumber=3**

_You can get a list of all orders through your number._
```
{
    "dealerNumber": 3,
    "orders": [
        {
            "orderNumber": 3,
            "orderDate": "2021-04-03",
            "deliveryDate": "2021-05-03",
            "daysDelay": 3,
            "deliveryStatus": "F",
            "orderDetails": [
                {
                    "partCode": 4,
                    "description": "Puerta delantera derecha",
                    "quantity": 4,
                    "accountType": "G",
                    "reason": "sin motivo"
                },
                {
                    "partCode": 5,
                    "description": "Puerta delantera izquierda",
                    "quantity": 2,
                    "accountType": "R",
                    "reason": "sin motivo"
                }
            ]
    }
}
```
_It also has access through input parameters with the following filters:_
-if required dealer number are required.

* dealerNumber: number.
* deliveryStatus:
  - P - Pending delivery.
  - D - Delayed.
  - F - Finalized.
  - C - Cancelled.
* order:
  - 1 - sort in ascending order.

### ml-parts-03
> **GET api/v1/parts/orders/0000-0000-00000003**

_You can get a list of all orders through your order number._

```json
{
    "orderNumberCE": "0000-00000003",
    "orderDate": "2021-04-03",
    "orderStatus": "F",
    "orderDetails": [
        {
            "partCode": 5,
            "description": "Puerta delantera izquierda",
            "quantity": 2,
            "accountType": "R",
            "reason": "sin motivo",
            "partStatus": "N"
        },
        {
            "partCode": 4,
            "description": "Puerta delantera derecha",
            "quantity": 4,
            "accountType": "G",
            "reason": "sin motivo",
            "partStatus": "N"
        }
    ]
}
```

### ml-parts-04
> **POST api/v1/parts/**

_You can add a part to stock through a payload in the body._

_Request body:_ - (Needed to save)

```json
{
  "partCode": 123,
  "description": "Test part entity",
  "discountType": "A00",
  "normalPrice": 762,
  "quantity": 45,
  "urgentPrice": 900,
  "netWeight": 100,
  "longDimension": 100,
  "widthDimension": 100,
  "tallDimension": 100,
  "maker": "new part entity",
  "lastModification": "2021-03-02",
  "partStatus": "N"
}
```

_Response body:_

```
  Part saved.
```

### ml-parts-05
> **POST api/v1/parts/**

_You, as a country central subsidiary, can make orders to the 'Casa Matriz' sending a payload through the body._

_Request body:_ - (Needed to make order)

```json
{
    "orderDetails": [
        {
            "partCode": 3,
            "description": "Puerta delantera derecha",
            "quantity": 1,
            "accountType": "G"
        },
        {
            "partCode": 2,
            "description": "Puerta delantera izquierda",
            "quantity": 1,
            "accountType": "Z"
        }
    ]
}
```

_Response body:_

```json
{
  "orderNumber": 10,
  "orderDate": "2021-05-18",
  "deliveryDate": "2021-05-25",
  "daysDelay": 0,
  "orderDetails": [
    {
      "partCode": 3,
      "quantity": 1,
      "accountType": "G",
      "reason": "Sin motivo"
    },
    {
      "partCode": 2,
      "quantity": 1,
      "accountType": "Z",
      "reason": "Sin motivo"
    }
  ]
}
```

### ml-parts-06
> **GET api/v1/parts/orders/dealers**

_You can get a list order details._

_Response body:_

```json
[
    {
        "orderNumber": 1,
        "orderDate": "2021-04-30",
        "orderStatus": "P",
        "deliveryDate": "2021-05-15",
        "daysDelay": 0,
        "orderDetails": [
            {
                "partCode": 1,
                "description": "Llanta",
                "quantity": 2,
                "accountType": "G",
                "reason": "sin motivo"
            }
        ],
        "dealerId": 7,
        "subsidiaryId": 5
    },
    {
        "orderNumber": 2,
        "orderDate": "2020-09-15",
        "orderStatus": "D",
        "deliveryDate": "2020-09-20",
        "daysDelay": 0,
        "orderDetails": [
            {
                "partCode": 2,
                "description": "Puerta trasera derecha",
                "quantity": 2,
                "accountType": "R",
                "reason": "sin motivo"
            },
            {
                "partCode": 3,
                "description": "Puerta trasera izquierda",
                "quantity": 1,
                "accountType": "G",
                "reason": "sin motivo"
            }
        ],
        "dealerId": 2,
        "subsidiaryId": 5
    },
    {
        "orderNumber": 3,
        "orderDate": "2021-04-03",
        "orderStatus": "F",
        "deliveryDate": "2021-05-03",
        "daysDelay": 3,
        "orderDetails": [
            {
                "partCode": 4,
                "description": "Puerta delantera derecha",
                "quantity": 4,
                "accountType": "G",
                "reason": "sin motivo"
            },
            {
                "partCode": 5,
                "description": "Puerta delantera izquierda",
                "quantity": 2,
                "accountType": "R",
                "reason": "sin motivo"
            }
        ],
        "dealerId": 3,
        "subsidiaryId": 1
    }
]
```

## 🛠️ Built with 

_The tools used to carry out the project were:_

* [SPRING-BOOT](https://spring.io/projects/spring-boot) - The framework used.
* [Maven](https://maven.apache.org/) - Dependency manager.
* [JWT](https://jwt.io/) - Authentication methods.
* [Swagger](https://swagger.io/) - Project documentation.

## ✒️ Authors 

_This project was carried out by the collaboration of:_

* **Romy Caicedo Molano** - [romycaicedo](https://github.com/romycaicedo)
* **Fabrizio Fernandez** - [fabfernandez](https://github.com/fabfernandez)
* **Juan Ignacio Aguirre** - [juan-aguirre-ml](https://github.com/juan-aguirre-ml)
* **Lucas Martin Klassen** - [klassenlucas](https://github.com/klassenlucas)
* **Nicolas Martin Sogliano Suarez** - [sogliano](https://github.com/sogliano)
* **Alexis Gabriel Barrientos** - [alebarrientos](https://github.com/alebarrientos)

## 📄 License 

Project is under the Mercado Libre SRL License.

## 🎁 Expressions of Gratitude 

* Tell others about this project. 📢
* Invite a beer 🍺 or a coffee ☕ someone from the team.
* Give thanks publicly 🤓.
* etc.

---
