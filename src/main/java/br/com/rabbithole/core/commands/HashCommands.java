package br.com.rabbithole.core.commands;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.enums.Warn;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class HashCommands {
    private static final HashCommands INSTANCE = new HashCommands();

    public HashCommands() {
    }

    public static HashCommands getInstance() {
        return INSTANCE;
    }

    public Optional<Boolean> hashSetQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.hset(args[0], args[1], args[2]);
            return Optional.of(true);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<String> hashGetQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return Optional.of(jedis.hget(args[0], args[1]));
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Boolean> hashDelQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.hdel(args[0], args[1]);
            return Optional.of(true);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Boolean> hashExistsQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return Optional.of(jedis.hexists(args[0], args[1]));
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
