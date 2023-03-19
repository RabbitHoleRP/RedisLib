package br.com.rabbithole.core.enums;

import br.com.rabbithole.RedisLib;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@Deprecated
public enum RedisCommands {
    SET {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                jedis.set(args[0], args[1]);
                return Optional.of(true);
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    DEL {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                jedis.del(args[0]);
                return Optional.of(true);
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    EXISTS {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                return Optional.of(jedis.exists(args[0]));
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    GET {
        @Override
        public Optional<String> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                return Optional.of(jedis.get(args[0]));
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    EXPIRE {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                jedis.expire(args[0], Long.parseLong(args[1]));
                return Optional.of(true);
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    HASH_SET {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                jedis.hset(args[0], args[1], args[2]);
                return Optional.of(true);
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.INSERT_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    HASH_DEL {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                jedis.hdel(args[0], args[1]);
                return Optional.of(true);
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.DELETE_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    HASH_EXISTS {
        @Override
        public Optional<Boolean> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                return Optional.of(jedis.hexists(args[0], args[1]));
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    },
    HASH_GET {
        @Override
        public Optional<String> query(String... args) {
            try (Jedis jedis = RedisLib.getJedis().getResource()) {
                return Optional.of(jedis.hget(args[0], args[1]));
            } catch (Exception exception) {
                RedisLib.getWarn().sendWarn(Warn.GET_CACHE_ERROR);
                exception.printStackTrace();
                return Optional.empty();
            }
        }
    };

    public <T> Optional<T> query(String... args) {
        return Optional.empty();
    }

}
