# Gradle Spring Multi Model Example

## How to run

* Go to `gradle_spring_multi_model_example` folder

* Application run

```bash
gradle :application:bootRun
```

* User run

```bash
gradle :user:bootRun
```

## Test

```bash
curl -X GET http://localhost:8080/hello

curl -X GET http://localhost:8081/user/hello

curl -X GET http://localhost:8081/user/
```
