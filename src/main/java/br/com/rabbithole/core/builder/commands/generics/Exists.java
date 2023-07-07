package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Optional;

public class Exists implements Command, Read, Execute<Boolean> {
    private final String key;

    @Override
    public String commandName() {
        return "exists";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + "has executed!");
            return Optional.of(jedis.exists(getKey()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.of(false);
        }
    }

    private Exists(Builder builder) {
        this.key = builder.key;
    }

    private Query<Exists> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Exists> build() {
            return new Exists(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
