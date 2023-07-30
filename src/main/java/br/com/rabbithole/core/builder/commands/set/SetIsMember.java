package br.com.rabbithole.core.builder.commands.set;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashRead;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Retorna se um membro faz parte de uma coleção (Set).
 *
 * @author Felipe Ros
 * @since 2.4.0
 * @version 1.0
 */
public class SetIsMember implements Command, HashRead, Execute<Boolean> {
    private final String key;
    private final String field;

    @Override
    public String commandName() {
        return "setIsMember";
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
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.sismember(getKey(), getField()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private SetIsMember(Builder builder) {
        this.key = builder.key;
        this.field = builder.field;
    }

    private Query<SetIsMember> query() {
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

        public Query<SetIsMember> build() {
            return new SetIsMember(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
