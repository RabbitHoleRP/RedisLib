package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import br.com.rabbithole.core.builder.base.options.CommandOptions;
import br.com.rabbithole.core.builder.options.ExpireOptions;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class Expire implements Command, Write<Integer>, CommandOptions<ExpireOptions>, Execute<Boolean> {
    private final String key;
    private final int value;
    private final ExpireOptions options;

    @Override
    public String commandName() {
        return "expire";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public ExpireOptions getOptions() {
        return this.options;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            jedis.expire(getKey(), getValue());
            return Optional.of(true);
        } catch (Exception exception) {
            exception.printStackTrace();
            return Optional.of(false);
        }
    }

    private Expire(Builder builder) {
        this.key = builder.key;;
        this.value = builder.value;
        this.options = builder.options;
    }

    private Query<Expire> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;
        private int value;
        private ExpireOptions options;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Builder setOptions(ExpireOptions options) {
            this.options = options;
            return this;
        }

        public Query<Expire> build() {
            return new Expire(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
