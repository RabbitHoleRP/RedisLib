import br.com.rabbithole.RedisLib;
import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.commands.generics.Get;
import br.com.rabbithole.core.builder.commands.generics.sets.Set;

import java.util.Optional;

public class TestClass {
    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("Test", true, "localhost", 6379, "default", "1234", 100));

        Query<Get> getQuery = new Get.Builder()
                .setKey("Foo")
                .build();

        Query<Set> setQuery = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        Optional<Boolean> resultOfSetQuery = setQuery.getCommand().execute();

        var getQueryResult = new Get.Builder()
                .setKey("")
                .execute();

    }
}
