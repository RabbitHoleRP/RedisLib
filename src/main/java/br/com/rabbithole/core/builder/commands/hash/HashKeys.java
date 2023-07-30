package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * Retorna todos os campos de uma Hash.
 *
 * @author Felipe Ros
 * @since 2.3.0
 * @version 1.0.1
 */
public class HashKeys implements Command, Read, Execute<Set<String>> {
    private final String key;

    @Override
    public String commandName() {
        return "hashKeys";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Set<String>> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            Set<String> fields = jedis.hkeys(getKey());
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (fields.isEmpty() ? Optional.empty() : Optional.of(fields));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private HashKeys(Builder builder) {
        this.key = builder.key;
    }

    private Query<HashKeys> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Set<String>> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<HashKeys> build() {
            return new HashKeys(this).query();
        }

        @Override
        public Optional<Set<String>> execute() {
            return build().getCommand().execute();
        }
    }
}
