package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Retorna todos os campos e valores de uma Hash.
 * @since 2.0
 * @version 1.0
 */
public class HashGetAll implements Command, Read, Execute<Map<String, String>> {
    private final String key;

    @Override
    public String commandName() {
        return "hashGetAll";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Map<String, String>> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            Map<String, String> fields = jedis.hgetAll(getKey());
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (fields.isEmpty() ? Optional.empty() : Optional.of(fields));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private HashGetAll(Builder builder) {
        this.key = builder.key;
    }

    private Query<HashGetAll> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Map<String, String>> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<HashGetAll> build() {
            return new HashGetAll(this).query();
        }

        @Override
        public Optional<Map<String, String>> execute() {
            return build().getCommand().execute();
        }
    }
}
