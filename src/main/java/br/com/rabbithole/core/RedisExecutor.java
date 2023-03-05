package br.com.rabbithole.core;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.enums.RedisCommands;
import br.com.rabbithole.core.enums.Warn;

import java.util.Optional;

public class RedisExecutor {
    public <T> Optional<T> redisQuery(RedisCommands command, String... args) {
        Optional<T> result = redisQueryExecutor(command, args);
        if (result.isEmpty()) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
        }
        return result;
    }

    private <T> Optional<T> redisQueryExecutor(RedisCommands command, String... args) {
        switch (command) {
            case EXISTS, GET -> {
                return command.query(args[0]);
            }
            case SET, DEL, EXPIRE, HASH_EXISTS, HASH_GET -> {
                return command.query(args[0], args[1]);
            }
            case HASH_SET, HASH_DEL -> {
                return command.query(args[0], args[1], args[0]);
            }
        }
        return Optional.empty();
    }
}
