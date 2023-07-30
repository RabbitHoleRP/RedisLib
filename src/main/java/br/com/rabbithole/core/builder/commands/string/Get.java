package br.com.rabbithole.core.builder.commands.string;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Represents the command Get on Redis database.
 *
 * @author Felipe Ros
 * @since 2.0.0
 * @version 1.0.1
 */
public class Get implements Command, Read, Execute<String> {
    private final String key;

    @Override
    public String commandName() {
        return "get";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<String> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            String result = jedis.get(getKey());
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return (!result.equals("nil") ? Optional.of(result) : Optional.empty());
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private Get(Builder builder) {
        this.key = builder.key;
    }

    private Query<Get> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<String> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Get> build() {
            return new Get(this).query();
        }

        @Override
        public Optional<String> execute() {
            return build().getCommand().execute();
        }
    }
}
