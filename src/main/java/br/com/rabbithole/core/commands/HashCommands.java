package br.com.rabbithole.core.commands;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.annotations.Beta;
import br.com.rabbithole.core.annotations.Since;
import br.com.rabbithole.core.enums.Warn;
import redis.clients.jedis.Jedis;

public class HashCommands {
    private static final HashCommands INSTANCE = new HashCommands();

    public HashCommands() {
    }

    public static HashCommands getInstance() {
        return INSTANCE;
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean hashSetQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.hset(args[0], args[1], args[2]);
            return true;
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public String hashGetQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return jedis.hget(args[0], args[1]);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
            exception.printStackTrace();
            return null;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean hashDelQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.hdel(args[0], args[1]);
            return true;
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }

    @Beta
    @Since(version = "1.1.2")
    public boolean hashExistsQuery(String... args) {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return jedis.hexists(args[0], args[1]);
        } catch (Exception exception) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
            exception.printStackTrace();
            return false;
        }
    }
}
