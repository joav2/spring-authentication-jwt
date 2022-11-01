# springboot-json-web-token

## Gerador de token de acesso JWT

  `Spring Boot` Aplicativo de back-end Web Java que expõe uma API Rest para criar tokens de acesso JWT. Com certo nivel de 
  
  | Endpoint                                                      | Secured | Roles           |
  | ------------------------------------------------------------- | ------- | --------------- |
  | `POST /auth/authenticate -d {"username","password"}`          | No      |                 |
  | `POST /auth/signup -d {"username","password","name","email"}` | No      |                 |
