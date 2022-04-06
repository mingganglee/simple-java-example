# Gradle Create Multi Module Project

* [gs-multi-module git](https://github.com/spring-guides/gs-multi-module)

## Create Basic Project

Create `gradle_test` folder and enter 

```bash
mkdir ~/Downloads/gradle_test && cd ~/Downloads/gradle_test
```

Initialize the project, terminal input command 

```bash
gradle init --type basic
```

Output:

```text
Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Project name (default: gradle_test):

> Task :init
Get more help with your project: Learn more about Gradle by exploring our samples at https://docs.gradle.org/7.2/samples

BUILD SUCCESSFUL in 9s
2 actionable tasks: 2 executed
```

* **Select build script DSL: 1**
* **Project name (default: gradle_test): Enter**

## Download Spring Project

* [start.spring.io - library](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.6.6&packaging=jar&jvmVersion=11&groupId=com&artifactId=library&name=library&description=Demo%20project%20for%20Spring%20Boot&packageName=com.library)
* [start.spring.io - application](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.6.6&packaging=jar&jvmVersion=11&groupId=com&artifactId=application&name=application&description=Demo%20project%20for%20Spring%20Boot&packageName=com.application&dependencies=web)
* [start.spring.io - user](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.6.6&packaging=jar&jvmVersion=11&groupId=com&artifactId=user&name=user&description=Demo%20project%20for%20Spring%20Boot&packageName=com.user&dependencies=web)

**After downloading the project unzip and move the files into the gradle_test project**

## `gradle_test` Project Import Module

**edit `gradle_test/settings.gradle` file**

```text
rootProject.name = 'gradle_test'
include('library')
include('application')
include('user')
```

## `application/user` Import Library Model

**`application/build.gradle` and `user/build.gradle` import library**

```text
implementation project(':library')
```

## Coding Your Api

View specific projects ...

## Run Project

**application project run**

```bash
gradle :application:bootRun
```

**user project run**

```bash
gradle :user:bootRun
```
