package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashWrite;
import br.com.rabbithole.core.builder.base.options.CommandOptions;
import br.com.rabbithole.core.builder.options.HashSetOptions;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class HashSet implements Command, HashWrite<String>, CommandOptions<HashSetOptions>, Execute<Boolean> {
    private final String key;
    private final String field;
    private final String value;
    private final HashSetOptions options;

    @Override
    public String commandName() {
        return "hashSet";
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
    public String getValue() {
        return this.value;
    }

    @Override
    public HashSetOptions getOptions() {
        return this.options;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + "has executed!");
            return Optional.of(jedis.hset(getKey(), getField(), getValue()) >= 0);
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.of(false);
        }
    }

    private HashSet(Builder builder) {
        this.key = builder.key;
        this.field = builder.field;
        this.value = builder.value;
        this.options = builder.options;
    }

    private Query<HashSet> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;
        private String field;
        private String value;
        private HashSetOptions options;


        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOptions(HashSetOptions options) {
            this.options = options;
            return this;
        }

        public Query<HashSet> build() {
            return new HashSet(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
