package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import br.com.rabbithole.core.Executor;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.enums.Commands;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Classe de acesso a Biblioteca.
 * @since 1.0
 */
public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private final Executor executor;

    public RedisLib(RedisConfig config) {
        executor = new Executor();
        redisConfiguration = new RedisConfiguration(config);
    }

    public Executor getExecutor() {
        return executor;
    }

    public static void init(RedisConfig redisConfig) {
        RedisLib redisLib = new RedisLib(redisConfig);
    }

    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("", 6379, "", "", 100));

        Query query = new Query.QueryBuilder()
                .setCommand(Commands.SET)
                .setKey("")
                .setValue("")
                .build();

        //Optional<String> resultado = query.execute();
    }

    public static JedisPool getJedis() {
        return redisConfiguration.getJedis();
    }
}
