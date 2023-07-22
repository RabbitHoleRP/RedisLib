package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Optional;
import java.util.Set;

/**
 * @author Felipe Ros
 * @Usage Retorna todas as chaves que correspondem ao padrão.
 * @since 2.0
 * @version 1.0
 */
public class Keys implements Command, Read, Execute<Set<String>> {
    private final String key;

    @Override
    public String commandName() {
        return "keys";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Set<String>> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            Set<String> keys = jedis.keys(getKey());
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(keys);
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private Keys(Builder builder) {
        this.key = builder.key;
    }

    private Query<Keys> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Set<String>> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Keys> build() {
            return new Keys(this).query();
        }

        @Override
        public Optional<Set<String>> execute() {
            return build().getCommand().execute();
        }
    }
}
