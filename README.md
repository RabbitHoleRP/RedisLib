
# RedLibs

**RedLibs** é um cliente de [redis](https://redis.io/) leve e simples de usar — porem poderoso — foi criado apos o uso de outros clientes de redis que julgamos muito complexos, pesados e  com *overengineering*, 💋.


## Referência

 - [Redis](https://redis.io/)
 - [Overengineering](https://en.wikipedia.org/wiki/Overengineering)
 - [💋](https://pt.wikipedia.org/wiki/Princ%C3%ADpio_KISS)


## Uso/Exemplos

```javascript
import Component from 'my-project'

function App() {
  return <Component />
}
```


## Funcionalidades



## Instalação

1. Adicione o repositorio ao seu projeto.

**Maven**:
```xml
<repository>
  <id>rabbithole-repo-snapshots</id>
  <name>Rabbit Hole</name>
  <url>https://repo.rabbithole.com.br/snapshots</url>
</repository>
```

**Gradle(Groovy)**:
```groovy
  maven {
    url "https://repo.rabbithole.com.br/snapshots"
}
```

**Gradle(Kotlin)**:
```kotlin
  maven {
    url = uri("https://repo.rabbithole.com.br/snapshots")
}
```
2. Adicione a dependencia.

**Maven**:
```xml
<dependency>
  <groupId>br.com.rabbithole</groupId>
  <artifactId>RedisLib</artifactId>
  <version>1.0.3-SNAPSHOT</version>
</dependency>
```

**Gradle(Groovy)**:
```groovy
implementation "br.com.rabbithole:RedisLib:1.0.3-SNAPSHOT"
```

**Gradle(Kotlin)**:
```kotlin
implementation("br.com.rabbithole:RedisLib:1.0.3-SNAPSHOT")
```
## Licença

[MIT](https://choosealicense.com/licenses/mit/)

