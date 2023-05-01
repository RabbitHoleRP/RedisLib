package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import br.com.rabbithole.core.Executor;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.commands.generics.Del;
import br.com.rabbithole.core.builder.commands.generics.Get;
import br.com.rabbithole.core.builder.commands.generics.Set;
import br.com.rabbithole.core.builder.options.SetOptions;
import redis.clients.jedis.JedisPool;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * @author Felipe Ros
 * @Usage Classe de acesso a Biblioteca.
 * @since 1.0
 */
public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private final Executor executor;
    private static Logger logger;

    public RedisLib(RedisConfig config) {
        executor = new Executor();
        redisConfiguration = new RedisConfiguration(config);
    }

    public Executor getExecutor() { //TODO: REMOVER APÓS DESENVOLVER EXECUTOR!
        return executor;
    }

    public static void init(RedisConfig redisConfig) {
        RedisLib redisLib = new RedisLib(redisConfig);
        System.out.println("Iniciado com Sucesso!");
    }

    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("Test", "", 6379, "default", "1234", 100));



        Query<Get> getQuery = new Get.Builder()
                .setKey("Foo")
                .build();

        Query<Del> delQuery = new Del.Builder()
                .setKey("Foo")
                .build();

        Query<Set> setQueryNotOptions = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .build();

        Query<Set> setQuery = new Set.Builder()
                .setKey("Foo")
                .setValue("Bar")
                .setOptions(new SetOptions.Builder()
                        .setExpire(100)
                        .build()
                ).build();

        Optional<Object> result = Executor.execute(getQuery);

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
    /* TODO: ALTERAR PARA LOGGER FACTORY DO
    public static Logger getLogger() {
        return
    }
     */
}
