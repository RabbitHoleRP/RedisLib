<style>
.highlight {
    background-color: #161b22;
    padding: 5px;
    border-radius: 4px;
    text-align: center;
}
</style>

# RedisLib

<div class="highlight">

<span>**RedisLib** é uma biblioteca para o [Redis](https://redis.io/) que, mesmo sendo leve e simples de usar, se mostra poderosa e eficiente. Foi criada para facilitar o desenvolvimento de aplicações que utilizam o **Redis**.</span>

</div>

>### Tópicos
>
>> - [Referências](#referências)
>
>> - [Uso/Exemplos](#usoexemplos)
>
>> - [Funcionalidades](#funcionalidades)
>
>> - [Instalação](#instalação)
>
>> - [Desenvolvedores](#desenvolvedores)
>
>> - [Contribuidores](#contribuidores)
>
>> - [Licença](#licença)

---

## Referências

<div align="center">

<a href="https://redis.io"><img style="display: inline-block; vertical-align: middle;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="90" height="90"></a>

<a href="https://pt.wikipedia.org/wiki/Princípio_KISS"><span style="font-size: 5em; display: inline-block; vertical-align: middle;">💋</span></a>
<a href="https://en.wikipedia.org/wiki/Overengineering"><span style="font-size: 5em; display: inline-block; vertical-align: middle;">⚙️</span></a>

</div>

---

## Uso/Exemplos

```java
import br.com.rabbithole.RedisLib;
import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.commands.generics.Get;
import br.com.rabbithole.core.builder.commands.generics.sets.Set;
import br.com.rabbithole.core.builder.options.SetOptions;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Inicia a Conexão com o Redis passando os parâmetros de conexão 
        //RedisConfig(IP, Porta, Usuário, Senha, Número de Conexões)
        RedisLib.init(new RedisConfig("localhost", 6379, "user", "password", 100));

        //Uma Get Query simples.
        Query<Get> getQuery = new Get.Builder()
                .setKey("Foo")
                .build();

        //Execução da Query após a construção.
        Optional<String> resultOfGetQuery = getQuery.getCommand().execute();

        //Uma Get Query com execução na construção.
        Optional<String> getQueryWithExecute = new Get.Builder()
                .setKey("Foo")
                .execute();

        //Uma Set Query simples.
        Query<Set> setQuery = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        //Uma Set Query com Opções.
        Query<Set> setQueryWithOptions = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .setOptions(new SetOptions.Builder()
                        .setExpire(100)
                        .setIfNotExists()
                        .setIfExists()
                        .setGet()
                ).build();
    }
}
```

---

## Funcionalidades

---

## Instalação

> 1. Adicione o repositório ao seu projeto.

**Maven:**

```xml
<repository>
  <id>rabbithole-repo-snapshots</id>
  <name>Rabbit Hole</name>
  <url>https://repo.rabbithole.com.br/snapshots</url>
</repository>
```

**Gradle (Groovy):**

```groovy
maven {
    url "https://repo.rabbithole.com.br/snapshots"
}
```

**Gradle (Kotlin):**

```kotlin
maven {
    url = uri("https://repo.rabbithole.com.br/snapshots")
}
```

---

> 2. Adicione a dependência.

**Maven**:

```xml
<dependency>
  <groupId>br.com.rabbithole</groupId>
  <artifactId>RedisLib</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
```

**Gradle (Groovy):**

```groovy
implementation "br.com.rabbithole:RedisLib:2.0.0-SNAPSHOT"
```

**Gradle (Kotlin):**

```kotlin
implementation("br.com.rabbithole:RedisLib:0.0.0-SNAPSHOT")
```

---

> ## Desenvolvedores
>
>> [Felipe Ros Segundo Simão](https://github.com/FelipeRos19)

> ## Contribuidores
> 
>> [Gabriel Monção Fekete](https://github.com/gabrielfeket)
> 
>> [Murilo Gotardo Pommerening](https://github.com/Murilo-Gotardo)

---

## Licença

[MIT](https://choosealicense.com/licenses/mit/)

---

<p align="center">Felipe Ros Segundo Simão OS 2023 - RedisLib</p>