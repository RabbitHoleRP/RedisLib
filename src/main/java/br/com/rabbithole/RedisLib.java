package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.commands.generics.Del;
import br.com.rabbithole.core.builder.commands.generics.Get;
import br.com.rabbithole.core.builder.commands.generics.Set;
import br.com.rabbithole.core.builder.options.SetOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Classe de acesso a Biblioteca.
 * @since 1.0
 */
public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private static Logger logger;

    public RedisLib(RedisConfig config) {
        redisConfiguration = new RedisConfiguration(config);
        logger = LoggerFactory.getLogger(config.getPrefix() + " - ");
    }

    public static void init(RedisConfig redisConfig) {
        RedisLib redisLib = new RedisLib(redisConfig);
        System.out.println("Iniciado com Sucesso!");
    }

    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("Test", "localhost", 6379, "default", "1234", 100));

        Query<Set> setQuery = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        Optional<Boolean> executeSet = setQuery.getCommand().execute();

        if (executeSet.isPresent()) {
            System.out.println("Resultado Set: " + executeSet.get());
        } else {
            System.out.println("Sem Resultado Set!");
        }

        Query<Get> getQuery = new Get.Builder()
                .setKey("Foo")
                .build();

        Query<Del> delQuery = new Del.Builder()
                .setKey("Foo")
                .build();

        Optional<String> getQueryWithReturn = new Get.Builder()
                .setKey("Foo")
                .execute();

        if (getQueryWithReturn.isPresent()) {
            System.out.println("Resultado A: " + getQueryWithReturn.get());
        } else {
            System.out.println("Sem resultado!");
        }

        Optional<String> executeGet = getQuery.getCommand().execute();
        if (executeGet.isPresent()) {
            System.out.println("Resultado B: " + executeGet.get());
        } else {
            System.out.println("Sem resultado Get!");
        }

        Query<Set> setQueryNotOptions = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        /*
        Query<Set> setQuery = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .setOptions(new SetOptions.Builder()
                        .setExpire(100)
                        .build()
                ).build();
         */


        String commandName = setQuery.getCommand().commandName();
        String key = setQuery.getCommand().getKey();
        String value = setQuery.getCommand().getValue();
        SetOptions options = setQuery.getCommand().getOptions();


        System.out.println("Comando: " + commandName);
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);
        System.out.println("Expire: " + options.getExpire());
        System.out.println("IfNotExists: " + options.isIfNotExists());
        System.out.println("IfExists: " + options.isIfExists());
        System.out.println("Get: " + options.isGet());
    }

    public static JedisPool getJedis() {
        return redisConfiguration.getJedis();
    }

    public static Logger getLogger() {
        return logger;
    }
}
