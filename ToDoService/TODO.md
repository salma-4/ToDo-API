# TO-DO service 
This service is todo API that allow user to manage his to-do items add,update,delete,search,activate deactivate item and list all items. It built using spring boot and MYSQL DB. 
## Our EndPoints

- [SWAG-UI](http://localhost:8081/swagger-ui/index.html) 
- [API-DOC](http://localhost:8081/v3/api-docs)

[<img src="https://run.pstmn.io/button.svg" alt="Postman collection" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/31722824-5ee4669c-9ef3-4698-82e9-e9c11f71fc09?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D31722824-5ee4669c-9ef3-4698-82e9-e9c11f71fc09%26entityType%3Dcollection%26workspaceId%3D2f8749ed-66fa-4b6a-b9ac-c7b32f4100c6)

## Requirements

- Java Development Kit (JDK) 17 or above
- MySQL Database [Change settings](https://github.com/salma-4/ToDo-API/blob/main/ToDoService/src/main/resources/application.yml)

## Features

| No. | Feature         | header | Description                   | Endpoint                           |
|----:|-----------------|--------|-------------------------------|------------------------------------|
|  1. | Show  items     |        | List all to-do items for user | `GET /todo/item/items`             |
|  2. | ADD item        |        | Add new to-do item            | `POST /todo/item`                  |
|  3. | Update item     |        | Update item using id          | `PUT /todo/item/id/{id}/item`      |
|  4. | Search for item |        | Search item by title          | `GET /todo/item/title/{title}`     |
|  5. | Get item data   |        | Get item details using id ^   | `GET /todo/item/id/{id}/item`      |
|  6. | Delete item     |        | Delete item using id          | `DEL /todo/item/id/{id}`           |
|  7. | Reactivate item |        | Reactivate item  using id     | `GET /todo/item/id/{id}/activated` |
|  8. | Deactivate item |        | Deactivate item               | `Get /todo/item/id/1/deactivated`  |

## ERD
```mermaid
erDiagram
    USERS {
      long id PK
      string email "Unique"
      string password
     boolean enabled "Active or Not"
     }
     ITEMS {
        long id PK
        string title 
        long item_details_id FK
        long user_id FK
     }

     ITEM_DETAILS {
        long id PK
        string description 
        datetime created_at "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
        int priority
        boolean status
     }

     USERS ||--o{ ITEMS : "has"
     ITEM_DETAILS ||--o| ITEMS : "has"

