package br.com.rabbithole.configurations;

import java.time.Duration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Classe de Configuração do Redis.
 *
 * @author Felipe Ros
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

    protected JedisPoolConfig buildPoolConfig(int maxConnections) {
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
