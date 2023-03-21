package br.com.rabbithole.core.commands;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.annotations.Beta;
import br.com.rabbithole.core.annotations.Since;
import br.com.rabbithole.core.enums.Warn;
import redis.clients.jedis.Jedis;

public class GenericCommands {
    private static final GenericCommands INSTANCE = new GenericCommands();

    public GenericCommands() {
    }

    public static GenericCommands getInstance() {
        return INSTANCE;
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean setQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.set(args[0], args[1]);
            return true;
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public String getQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return jedis.get(args[0]);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
            exception.printStackTrace();
            return null;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean delQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.del(args[0], args[1]);
            return true;
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean existsQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return jedis.exists(args[0]);
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean expireQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.expire(args[0], Long.parseLong(args[1])); //Expire return 0 or 1
            return true;
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }
}
