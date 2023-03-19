package br.com.rabbithole.core.commands;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.enums.Warn;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class GenericCommands {
    private static final GenericCommands INSTANCE = new GenericCommands();

    public GenericCommands() {
    }

    public static GenericCommands getInstance() {
        return INSTANCE;
    }

    public Optional<Boolean> setQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.set(args[0], args[1]);
            return Optional.of(true);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<String> getQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return Optional.of(jedis.get(args[0]));
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Boolean> delQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.del(args[0], args[1]);
            return Optional.of(true);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Boolean> existsQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return Optional.of(jedis.exists(args[0]));
        }
    }

    public Optional<Boolean> expireQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.expire(args[0], Long.parseLong(args[1]));
            return Optional.of(true);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
