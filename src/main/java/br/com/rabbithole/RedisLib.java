package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

/**
 * Library's access class.
 *
 * @author Felipe Ros
 * @since 1.0.1
 */
public final class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private static boolean debug;
    private static Logger logger;

    public static void init(RedisConfig redisConfig) {
        debug = redisConfig.isDebug();
        logger = LoggerFactory.getLogger(redisConfig.getPrefix() + " (Redis)");
        redisConfiguration = new RedisConfiguration(redisConfig);
        logger.info("RedisLib has initialized!");
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
