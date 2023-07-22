package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import br.com.rabbithole.core.builder.commands.generics.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

/**
 * @author Felipe Ros
 * @Usage Library's access class.
 * @since 1.0
 */
public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private static boolean debug;
    private static Logger logger;

    public static void init(RedisConfig redisConfig) {
        debug = redisConfig.isDebug();
        logger = LoggerFactory.getLogger(redisConfig.getPrefix() + " (Redis)");
        redisConfiguration = new RedisConfiguration(redisConfig);
        logger.info("RedisLib has initialized!");
    }

    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("teste", true, "199.204.160.178", 6379, "default", "teste123", 100));

        var keys = new Keys.Builder().setKey("*teste*").execute();
        var persist = new Persist.Builder().setKey("testeString").execute();
        var randomKey = new RandomKey.Builder().execute();
        //var rename = new Rename.Builder().setKey("rename").setValue("newName").execute();
        //var renameNx = new RenameNx.Builder().setKey("testeRename").setValue("newTesteRename").execute();
        var ttl = new TTL.Builder().setKey("testeString").execute();
        var type = new Type.Builder().setKey("teste").execute();

        /*
        keys.ifPresent(strings -> {
            for (String key : strings) {
                System.out.println(key);
            }
        });
        persist.ifPresent(System.out::println);
        randomKey.ifPresent(System.out::println);
        rename.ifPresent(System.out::println);
        ttl.ifPresent(System.out::println);
         */

        type.ifPresent(System.out::println);
    }

    public static JedisPool getJedis() {
        return redisConfiguration.getJedis();
    }

    public static boolean inDebug() {
        return debug;
    }

    public static Logger getLogger() {
        return logger;
    }
}
