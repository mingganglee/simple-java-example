# Eurake and Gateway Example

## Service describe

| project         | port |
| --------------- | ---- |
| provider_server | 8000 |
| consumer_server | 8001 |
| eureka_server   | 8800 |
| gateway_server  | 9000 |

## How to run

1. start termianl
2. enter the current folder
3. terminal input command to start the project

```bash
gradle :eureka_server:bootRun
gradle :gateway_server:bootRun
gradle :provider_server:bootRun
gradle :consumer_server:bootRun
```

## Test

terminal input command

```bash
curl -X GET http://localhost:9000/consumer/hello
```

Output:

```text
hello get provider --- hello post provider
```

other try

```bash
curl -X GET http://localhost:9000/provider/hello

curl -X POST http://localhost:9000/provider/hello
```

### TODO

* consumer api using `OpenFeign` remote call provider get and post api
* `consumer_server/service` file is defined
