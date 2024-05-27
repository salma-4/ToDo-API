# User Service
Api for user management that allow user to :
- register then send OTP to active account, login
- activate and deactivate user 
- handle forget and change password via sending Otp code
- update, delete, search, list users

## Test the API
- [API DOC](http://localhost:8080/v3/api-docs)
- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/31722824-444e0956-2942-4d55-9426-65b805c7818d?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D31722824-444e0956-2942-4d55-9426-65b805c7818d%26entityType%3Dcollection%26workspaceId%3D2f8749ed-66fa-4b6a-b9ac-c7b32f4100c6)

## Requirements
- Java Development Kit (JDK) 17 or above
- MySQL Database [Customize yours](https://github.com/salma-4/ToDo-API/blob/main/UserService/src/main/resources/application.yml)

## Features

| No. | Feature               | Body             | Header | Description                                              | Endpoint                                         |
|----:|-----------------------|------------------|--------|----------------------------------------------------------|--------------------------------------------------|
|  1. | Login                 | Email , password |        | generate new token for user                              | `POST /app/auth/login `                          |
|  2. | Register              | Data of user     |        | Add new user and send otp to user email                  | `POST /app/auth/register `                       |
|  3. | Activate user account |                  | OTP    | Activate user account by email and OTP send              | `GET  /app/auth/activate?username= `             |
|  4. | Token validty         |                  | Token  | Check if token is valid or not                           | `GET  /user/token/validation `                   |
|  5. | Forget password       |                  | Token  | Generate OTP and send it to user email                   | `Post /users/forgetPassword `                    |
|  6. | Change password       |                  | Token  | Change password by using OTP sent in previous endpoint ^ | `Post /users/changePassword?otp= &newPassword= ` |
|  7. | Regenerate OTP        |                  | Token  | Regenerate new OTP for user and send it                  | `Post /users/regenerateOtp?email= `              |
|  8. | Activate user         |                  | Token  | Activate user using id                                   | `PUT  /users/id/{id}/activated `                 |
|  9. | Deactivate user       |                  | Token  | Deactivate user using id                                 | `PUT  /users/id/{id}/deactivated `               |
| 10. | All users             |                  | Token  | List all users                                           | `GET  /users  `                                  |
| 11. | Search for user       |                  | Token  | Search for user using email                              | `GET  /users/email/{email}  `                    |
| 12. | Update userData       | Updated user     | Token  | Update user                                              | `PUT  /users/id/{id}/user  `                     |
| 13. | Delete user           |                  | Token  | Delete user                                              | `DEL  /users/id/{id}   `                         |


## ERD

```mermaid
erDiagram
    USER {
        long id PK
        string email "Unique"
        string password 
        boolean enabled "Active or Not"
    }
    
    TOKEN {
        int id PK
        string token
        string tokenType
        datetime createdAT
        datetime expirationDate
        long user_id FK
    }
    
    OTP {
        long id PK
        string otp "Unique"
        datetime expirationTime
        long user_id FK
    }
    
    USER ||--o{ TOKEN : "has"
    USER ||--o{ OTP : "has"




