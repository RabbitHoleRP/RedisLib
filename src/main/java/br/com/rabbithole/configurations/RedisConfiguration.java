package br.com.rabbithole.configurations;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Felipe Ros
 * @Usage Classe de Configuração do Redis.
 * @since 1.0
 */
public class RedisConfiguration {
    private final JedisPool jedis;

    public RedisConfiguration(RedisConfig redisConfig) {
        this.jedis = new JedisPool(
                buildPoolConfig(redisConfig.getConnections()),
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getUser(),
                redisConfig.getPassword());
    }

    private JedisPoolConfig buildPoolConfig(int maxConnections) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxConnections);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTime(Duration.ofSeconds(60));
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    public JedisPool getJedis() {
        return jedis;
    }
}
