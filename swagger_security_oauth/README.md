# Spring Oauth2 Example

## Project Describe

| project                             | describe                                        |
| ----------------------------------- | ----------------------------------------------- |
| swagger_security_oauth_client       | resource server                                 |
| swagger_security_oauth_server_jwt   | auth server issue jwt token                     |
| swagger_security_oauth_server_redis | auth server issue uuid token and store to redis |

## Test

### Test auth server and client server

#### Run auth server

go to the `swagger_security_oauth_server_jwt` folder

```bash
cd swagger_security_oauth_server_jwt
```

run auth server

```bash
gradle bootRun
```

#### Run client server

go to the `swagger_security_oauth_client` folder

```bash
cd swagger_security_oauth_client
```

run client server

```bash
gradle bootRun
```

#### Swagger UI test

browers open url: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

start authorization

![auth swagger step 1](./material/auth_1.png)

close authorization pop

![auth swagger step 1](./material/auth_2.png)

request `/api/v1/hello` api

![auth swagger step 1](./material/client_1.png)

view request status

![auth swagger step 1](./material/client_2.png)

### Cmd request server

#### Get access_token

| name       | value     |
| ---------- | --------- |
| grant_type | password  |
| username   | any value |
| password   | 123456    |

```bash
curl -X POST \
 http://127.0.0.1:8080/oauth/token \
 -H "Authorization:Basic Y2xpZW50OmNsaWVudA==" \
 -d "grant_type=password&username=1&password=123456"
```

Outpu:

```json
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxIiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTY0OTMwMjA4OCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNTJmYzE0M2ItNWQxNS00OTkwLThmMWEtNzYwMGUzNmVmYjA2IiwiY2xpZW50X2lkIjoiY2xpZW50IiwiZW5oYW5jZSI6ImVuaGFuY2VyIGluZm8ifQ.U4Hvb5MVNA7EuWLn8lll7cW61JWUi14P91Ku9AwbcvM","token_type":"bearer","expires_in":3599,"scope":"all","enhance":"enhancer info","jti":"52fc143b-5d15-4990-8f1a-7600e36efb06"}
```

#### request `/api/v1/hello` api

```bash
curl -X 'GET' \
  'http://localhost:8081/api/v1/hello' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxIiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTY0OTMwMjA4OCwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNTJmYzE0M2ItNWQxNS00OTkwLThmMWEtNzYwMGUzNmVmYjA2IiwiY2xpZW50X2lkIjoiY2xpZW50IiwiZW5oYW5jZSI6ImVuaGFuY2VyIGluZm8ifQ.U4Hvb5MVNA7EuWLn8lll7cW61JWUi14P91Ku9AwbcvM'
```

Output:

```text
hello swagger security oauth client.
```

## How to check user using sql

edit file `swagger_security_oauth_server_jwt/service/impl/UserDetailsServiceImpl.java`, `username` and `password` modified to databases storage info

### NOTE: `Password` encode for `BCryptPasswordEncoder`

this option is modified in the `swagger_security_oauth_server_jwt/config/SecurityConfig.java` file
