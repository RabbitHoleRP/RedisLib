package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashRead;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class HashGet implements Command, HashRead, Execute<String> {
    private final String key;
    private final String field;

    @Override
    public String commandName() {
        return "hashGet";
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
    public Optional<String> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            String result = jedis.hget(getKey(), getField());
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (!result.equals("nil") ? Optional.of(result) : Optional.empty());
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private HashGet(Builder builder) {
        this.key = builder.key;
        this.field = builder.field;
    }

    private Query<HashGet> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<String> {
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

        public Query<HashGet> build() {
            return new HashGet(this).query();
        }

        @Override
        public Optional<String> execute() {
            return build().getCommand().execute();
        }
    }
}
