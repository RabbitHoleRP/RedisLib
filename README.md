# RedisLib

<div align="center">

**RedisLib** is a library for [Redis](https://redis.io/) which,
even though it is light and simple to use, proves itself to be powerful and efficient.
It was created to facilitate the development of applications that use **Redis**

</div>

---

>### Topics
>
>> [References](#references)
>
>> [Use/Examples](#useexamples)
>
>> [Functionalities](#functionalities)
>
>> [Installation](#installation)
>
>> [Developers](#developers)
>
>> [Contributors](#contributors)
>
>> [License](#license)

---

### References

<div align="center">

<a href="https://redis.io"><img style="display: inline-block; vertical-align: middle;" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="90" height="90"></a>

<a href="https://pt.wikipedia.org/wiki/Princípio_KISS"><img src="https://github.githubassets.com/images/icons/emoji/unicode/1f48b.png" width="60" height="60"></a>
<a href="https://en.wikipedia.org/wiki/Overengineering"><img src="https://github.githubassets.com/images/icons/emoji/unicode/2699.png" width="60" height="60"></a>

</div>

---

### Use/Examples

```java
import br.com.rabbithole.RedisLibTest;
import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.commands.string.Get;
import br.com.rabbithole.core.builder.commands.string.Sets;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Starts a connection with Redis, passing the connection parameters
        //RedisConfig(prefix, debug, ip, port, user, password, number Of Connections)
        RedisLib.init(new RedisConfig("prefix", false, "localhost", 6379, "user", "password", 100));

        //A simple Get Query
        Query<Get> getQuery = new Get.Builder()
                .setKey("Foo")
                .build();

        //A Get Query execution after construction
        Optional<String> resultOfGetQuery = getQuery.getCommand().execute();

        //A Get Query with execution in the construction
        Optional<String> getQueryWithExecute = new Get.Builder()
                .setKey("Foo")
                .execute();

        //A simple Set Query
        Query<Sets<S>> setQuery = new Sets.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        //A Set Query execution after construction
        Optional<Boolean> resultOfSetQuery = setQuery.getCommand().execute();

        //A Set Query with execution in the construction
        Optional<Boolean> setQueryWithExecute = new Sets.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();
    }
}
```

---

### Functionalities

<div align="center">

    "Work in progress"
    
</div>

---

### Installation

> 1. Add the repo to your project

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

> 2. Add the dependency

**Maven**:

```xml
<dependency>
  <groupId>br.com.rabbithole</groupId>
  <artifactId>RedisLib</artifactId>
  <version>2.4.0-SNAPSHOT</version>
</dependency>
```

**Gradle (Groovy):**

```groovy
implementation "br.com.rabbithole:RedisLib:2.4.0-SNAPSHOT"
```

**Gradle (Kotlin):**

```kotlin
implementation("br.com.rabbithole:RedisLib:2.4.0-SNAPSHOT")
```

---

> ### Developers
>
>> [Felipe Ros Segundo Simão](https://github.com/FelipeRos19)

> ### Contributors
> 
>> [Gabriel Monção Fekete](https://github.com/gabrielfeket)
> 
>> [Murilo Gotardo Pommerening](https://github.com/Murilo-Gotardo)
>
>> [Artur Chiarello Pozzo](https://github.com/Pozzoo)
>
>> [gump](https://gump.dev)

---

### License

[MIT](https://choosealicense.com/licenses/mit/)

---

<p align="center">Felipe Ros Segundo Simão OS 2023 - RedisLib</p>

---

<div align="center">

    2.4.0
    
</div> 
