package br.com.rabbithole.core;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.annotations.Beta;
import br.com.rabbithole.core.annotations.Since;
import br.com.rabbithole.core.commands.GenericCommands;
import br.com.rabbithole.core.commands.HashCommands;
import br.com.rabbithole.core.enums.Commands;
import br.com.rabbithole.core.enums.RedisCommands;
import br.com.rabbithole.core.enums.Warn;

import java.util.Optional;

public class RedisExecutor {
    @Deprecated
    public <T> Optional<T> redisQuery(RedisCommands command, String... args) {
        Optional<T> result = redisQueryExecutor(command, args);
        if (result.isEmpty()) {
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
        }
        return result;
    }

    @Beta
    @Since(version = "1.1.2")
    public <T> Optional<T> redisQuery(Commands command, String... args) {
        Optional<T> result = converter(redisQueryExecutor(command, args));
        if (result.isEmpty()) { //TODO: Refactoring Result Error
            RedisLib.getWarn().sendWarn(Warn.CHECK_CACHE_ERROR);
        }
        return result;
    }

    @Deprecated
    private <T> Optional<T> redisQueryExecutor(RedisCommands command, String... args) {
        switch (command) {
            case EXISTS, GET -> {
                return command.query(args[0]);
            }
            case SET, DEL, EXPIRE, HASH_EXISTS, HASH_GET, HASH_DEL -> {
                return command.query(args[0], args[1]);
            }
            case HASH_SET -> {
                return command.query(args[0], args[1], args[2]);
            }
        }
        return Optional.empty();
    }

    @Beta
    @Since(version = "1.1.2")
    private Optional<?> redisQueryExecutor(Commands command, String... args) {
        switch (command) {
            case SET -> {
                return Optional.of(GenericCommands.getInstance().setQuery(args));
            }
            case GET -> {
                return Optional.of(GenericCommands.getInstance().getQuery(args));
            }
            case DEL -> {
                return Optional.of(GenericCommands.getInstance().delQuery(args));
            }
            case EXISTS -> {
                return Optional.of(GenericCommands.getInstance().existsQuery(args));
            }
            case EXPIRE -> {
                return Optional.of(GenericCommands.getInstance().expireQuery(args));
            }
            case HASH_SET -> {
                return Optional.of(HashCommands.getInstance().hashSetQuery(args));
            }
            case HASH_GET -> {
                return Optional.of(HashCommands.getInstance().hashGetQuery(args));
            }
            case HASH_DEL -> {
                return Optional.of(HashCommands.getInstance().hashDelQuery(args));
            }
            case HASH_EXISTS -> {
                return Optional.of(HashCommands.getInstance().hashExistsQuery(args));
            }
        }
        return Optional.empty();
    }

    @Beta
    @Since(version = "1.1.2")
    private <T> Optional<T> converter(Optional<?> result) {
        return (Optional<T>) result;
    }
}
