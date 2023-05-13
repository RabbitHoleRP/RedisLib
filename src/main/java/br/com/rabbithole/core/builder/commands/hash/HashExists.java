package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashRead;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class HashExists implements Command, HashRead, Execute<Boolean> {
    private final String key;
    private final String field;

    @Override
    public String commandName() {
        return "hashExists";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getField() {
        return this.field;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            return Optional.of(jedis.hexists(getKey(), getField()));
        }
    }

    private HashExists(Builder builder) {
        this.key = builder.key;
        this.field = builder.field;
    }

    private Query<HashExists> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;
        private String field;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Query<HashExists> build() {
            return new HashExists(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
