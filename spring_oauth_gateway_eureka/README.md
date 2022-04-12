# Eurake and Gateway Example

* [oauth2-demo](https://gitee.com/GuoDapeng/oauth2-demo)
* [mall-swarm](https://github.com/macrozheng/mall-swarm)

## Service describe

| project        | port |
| -------------- | ---- |
| auth-server    | 8000 |
| user-server    | 8001 |
| hello-server   | 8002 |
| eureka-server  | 8800 |
| gateway-server | 9000 |

## How to run

1. start termianl
2. enter the current folder
3. terminal input command to start the project

### Start Project Way 1

```bash
gradle :auth-server:bootRun
gradle :eureka-server:bootRun
gradle :gateway-server:bootRun
gradle :user_server:bootRun
gradle :hello_server:bootRun
```

![gradle_start](material/gradle_start.png)

### Start Project Way 2

```bash
gradle clean build

java -jar ./eureka-server/build/libs/eureka-server-0.0.1-SNAPSHOT.jar --server.port=8800
java -jar ./gateway-server/build/libs/gateway-server-0.0.1-SNAPSHOT.jar --server.port=9000
java -jar ./auth-server/build/libs/auth-server-0.0.1-SNAPSHOT.jar --server.port=8000
java -jar user-server/build/libs/user-server-0.0.1-SNAPSHOT.jar --server.port=8001
java -jar ./hello-server/build/libs/hello-server-0.0.1-SNAPSHOT.jar --server.port=8002
```

![java -jar start](material/java_jar_start.png)

## Test

terminal input command

```bash
curl -X POST \
 http://127.0.0.1:9000/oauth/token \
 -H "Authorization:Basic Y2xpZW50OmNsaWVudA==" \
 -d "grant_type=password&username=1&password=123"
```

Output:

```json
{"code":200,"message":"操作成功","data":{"token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhYWEiLCJzY29wZSI6WyJhbGwiXSwiaWQiOjEsImV4cCI6MTY0OTczNjE1NiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODYxYTljNmItMjQ4Ni00NDY0LWFjNzItNGZhOGY1YjYxNDBhIiwiY2xpZW50X2lkIjoiY2xpZW50In0.X5VGiNpDuWu0g0B9mSm6y-GUTMB8WNgiV2fC7G7Qd6JaLAuIs4y8E7V2QBHtc1hCNwyvZvEQ9ukD1upSZR4vBFxU7gYQ6-wCmdGI0mSdlS2nSj7kJXKN8Okbw1Od3H0AT4qdUHnn9J1HnyZ6YBv05KG_xItypKfrhGZioNZ0TQvK1oxn2lJM-v9yWmCn0hIk8ce8tYBwk6hqTBFLKpkPI8AT9O3GTa93jQBkySA0FzHzZQ9XjUIus5W6Bxoj1V8dRiazZUjF95C4b3A-ZX-gY3PhoqFtRR9TXglItF4jwZv0copy2wTJRew9v4ffROlsAiUJjLYC52ulp9ycmUV0GQ","refreshToken":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhYWEiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiODYxYTljNmItMjQ4Ni00NDY0LWFjNzItNGZhOGY1YjYxNDBhIiwiaWQiOjEsImV4cCI6MTY0OTgxODk1NiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNDY5YzQxNzktNjIzMC00Y2YzLWE5MWEtZGExNGQ0YmUxMTA0IiwiY2xpZW50X2lkIjoiY2xpZW50In0.PExvcuqrrbcLYKLZn0OqAWpXVRbVgQi0uV6fiPNvSBugdailKehDXum-5JXGOZNo6gq4N8oiAtnHFSAdxEhcYf1-4VGPcHQ6Xu8FBa5mkN9NkjpVQB88s6LtcXO8ExW6jFIn3N0aaqWy9wrR7I1DEPDRMc01z_4dgX5eB5p-Fj1H_QJo9-LkdkWazEEsOMSm0czaSuR921_O8eygbLjj6gtEqSKrKX06LDHIX5WC59VRJYcKHxamUFH55P4sC7LFMnc5PivDoYtB_QUra3fdBwKsb19GZGFhM9HmDpxklFRjuB5n7pUXwfHtBL0St_M1L0Wzvyff4vC6I5M_X2xthQ","tokenHead":"Bearer ","expiresIn":3599}}
```

write token to header and request

```bash
curl -X GET http://localhost:9000/hello/hello \
 -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhYWEiLCJzY29wZSI6WyJhbGwiXSwiaWQiOjEsImV4cCI6MTY0OTczNjE1NiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiODYxYTljNmItMjQ4Ni00NDY0LWFjNzItNGZhOGY1YjYxNDBhIiwiY2xpZW50X2lkIjoiY2xpZW50In0.X5VGiNpDuWu0g0B9mSm6y-GUTMB8WNgiV2fC7G7Qd6JaLAuIs4y8E7V2QBHtc1hCNwyvZvEQ9ukD1upSZR4vBFxU7gYQ6-wCmdGI0mSdlS2nSj7kJXKN8Okbw1Od3H0AT4qdUHnn9J1HnyZ6YBv05KG_xItypKfrhGZioNZ0TQvK1oxn2lJM-v9yWmCn0hIk8ce8tYBwk6hqTBFLKpkPI8AT9O3GTa93jQBkySA0FzHzZQ9XjUIus5W6Bxoj1V8dRiazZUjF95C4b3A-ZX-gY3PhoqFtRR9TXglItF4jwZv0copy2wTJRew9v4ffROlsAiUJjLYC52ulp9ycmUV0GQ"
```

Output:

```text
hello hello server.
```

![request](material/request.png)

## Generate `auth-server/src/main/resources/jwt.jks` File

```bash
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```
