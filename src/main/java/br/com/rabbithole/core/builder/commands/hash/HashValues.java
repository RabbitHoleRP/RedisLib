package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.List;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Retorna todos os valores de uma Hash.
 *
 * @author Felipe Ros
 * @since 2.3.0
 * @version 1.0.1
 */
public class HashValues implements Command, Read, Execute<List<String>> {
    private final String key;

    @Override
    public String commandName() {
        return "hashValues";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<List<String>> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            List<String> values = jedis.hvals(getKey());
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (values.isEmpty() ? Optional.empty() : Optional.of(values));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private HashValues(Builder builder) {
        this.key = builder.key;
    }

    private Query<HashValues> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<List<String>> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<HashValues> build() {
            return new HashValues(this).query();
        }

        @Override
        public Optional<List<String>> execute() {
            return build().getCommand().execute();
        }
    }
}
