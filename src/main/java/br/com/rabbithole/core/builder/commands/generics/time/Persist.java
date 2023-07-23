package br.com.rabbithole.core.builder.commands.generics.time;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Remove o tempo limite de existência de uma Chave.
 * @since 2.0
 * @version 1.0
 */
public class Persist implements Command, Read, Execute<Boolean> {
    private final String key;

    @Override
    public String commandName() {
        return "persist";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (jedis.persist(getKey()) == 0 ? Optional.of(false) : Optional.of(true));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private Persist(Builder builder) {
        this.key = builder.key;
    }

    private Query<Persist> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Persist> build() {
            return new Persist(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
