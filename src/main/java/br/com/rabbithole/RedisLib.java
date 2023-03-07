package br.com.rabbithole;

import br.com.rabbithole.core.RedisExecutor;
import br.com.rabbithole.core.WarnExecutor;
import redis.clients.jedis.JedisPool;

public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private static RedisExecutor redis;
    private static WarnUtils warn;

    public RedisLib(String prefix, String host, int port, String user, String password) {
        warn = new WarnUtils("Redis - " + prefix);
        redis = new RedisExecutor();
        redisConfiguration = new RedisConfiguration(host, port, user, password);
    }

    public static WarnExecutor getWarn() {
        return warn.getWarn();
    }

    public RedisExecutor getRedis() {
        return redis;
    }

    public static JedisPool getJedis() {
        return redisConfiguration.getJedis();
    }
}
