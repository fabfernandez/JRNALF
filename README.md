# Final Challenge Demo

Development of an API Rest-Full with the purpose of updating marketing processes and the requirements imposed by the international company "Delux", dedicated to the distribution of luxury vehicles, buses, trucks and their respective spare parts. This company has its headquarters in Brazil, and has different headquarters in different countries.


### Pre Requirements 📋

_Be members of the organization_
_Have the following versions available in your local environment:_

- VPN enabled
- Apache Maven 3.5.4
- Python 2.7.16
- Fury version 1.2.1
- Docker version 20.10.6
- Postman (optional)

### Installation 🔧

_Positioning ourselves in the chosen directory and through the terminal we will execute:_

```
Fury get...
```
Luego
```
Fury run
```

## Running the tests ⚙️

```
Fury test
```

## Comenzando 🚀

# To access the app it is necessary to authenticate.
> **POST /api/v1/login**

Request params: (Needed to access)

- username
- password

- Query parameters example:

"user": "gembleton0"
"password": "*******",

Response body:

```json
{
  "user": "gembleton0",
  "token": "6GllcyI6WyIxIl0sImlhdCITk5NywiZXhwIjoxNjIxODg5OTk3fQ.GHxo79uiFTwI0yQ33GCUbB..."
}
```

### Application endpoints ⌨️

> **GET /api/v1/parts/list**

_You can get a list of all the parts_
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
  P - Spare parts modified from the date of consultation to the current. 
  V - Spare parts that varied in price from the consultation date to the current one.
  C - Complete.
* date: 01/01/2021 (example)
* order: 1, 2, 3

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
}
```
_It also has access through input parameters with the following filters:_
-if required dealer number are required.

* dealerNumber: number.
* deliveryStatus:
  P - Pending delivery.
  D - Delayed.
  F - Finalized.
  C - Cancelled.
* order: 1 - sort in ascending order.

> **POST api/v1/parts/**

_You can add an answer via a payload in the body._

Response body:

```
  Part saved.
```

## Versioned in Fury 📌

fury create-version 0.X.X

## Deployment 📦

_deploy_

## Built with 🛠️

_The tools used to carry out the project were:_

* [SPRING-BOOT](https://spring.io/projects/spring-boot) - The framework used.
* [Maven](https://maven.apache.org/) - Dependency manager.
* [JWT](https://jwt.io/) - Authentication methods.
* [Swagger](https://swagger.io/) - Project documentation.

## Authors ✒️

_This project was carried out by the collaboration of:_

* **Romina Caicedo Molano** - [romycaicedo](https://github.com/romycaicedo)
* **Fabrizio Fernandez** - [fabfernandez](https://github.com/fabfernandez)
* **Juan Ignacio Aguirre** - [juan-aguirre-ml](https://github.com/juan-aguirre-ml)
* **Lucas Martin Klassen** - [klassenlucas](https://github.com/klassenlucas)
* **Nicolas Martin Sogliano Suarez** - [sogliano](https://github.com/sogliano)
* **Alexis Gabriel Barrientos** - [alebarrientos](https://github.com/alebarrientos)

## Licencia 📄

Project is under the Mercado Libre SRL License.

## Expressions of Gratitude 🎁

* Tell others about this project. 📢
* Invite a beer 🍺 or a coffee ☕ someone from the team.
* Give thanks publicly 🤓.
* etc.

---
