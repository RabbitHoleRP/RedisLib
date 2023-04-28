
# RedisLib

**RedisLib** é um cliente de [redis](https://redis.io/) leve e simples de usar — porém poderoso — foi criado após o uso de outros clientes de **Redis** que julgamos muito complexos, pesados e  com *overengineering*, 💋.


## Referência

 - [Redis](https://redis.io/)
 - [Overengineering](https://en.wikipedia.org/wiki/Overengineering)
 - [💋](https://pt.wikipedia.org/wiki/Princ%C3%ADpio_KISS)


## Uso/Exemplos

```java
import br.com.rabbithole.RedisLib;
import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.enums.Commands;

public class Main {
    public static void main(String[] args) {
        //Inicia a Conexão com o Redis passando os parâmetros de conexão 
        //RedisConfig(IP, Porta, Usuário, Senha, Número de Conexões)
        RedisLib.init(new RedisConfig("localhost", 6379, "user", "password", 100));
        
        //Uma Get Query simples.
        Query query = new Query.QueryBuilder()
                .setCommand(Commands.GET)
                .setKey("Foo")
                .build();
        
        //Uma Set Query simples.
        Query query = new Query.QueryBuilder()
                .setCommand(Commands.SET)
                .setKey("Foo")
                .setValue("Bar")
                .build();
    }
}
```


## Funcionalidades



## Instalação

1. Adicione o repositório ao seu projeto.

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
2. Adicione a dependência.

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

