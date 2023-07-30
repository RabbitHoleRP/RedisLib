package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Remove um valor do banco de dados Redis.
 *
 * @author Felipe Ros
 * @since 2.0.0
 * @version 1.0.1
 */
public class Del implements Command, Read, Execute<Boolean> {
    private final String key;

    @Override
    public String commandName() {
        return "del";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.del(getKey()) != 0);
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.of(false);
        }
    }

    private Del(Builder builder) {
        this.key = builder.key;
    }

    private Query<Del> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Del> build() {
            return new Del(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
