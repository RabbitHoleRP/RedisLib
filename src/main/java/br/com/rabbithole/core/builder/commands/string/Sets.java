package br.com.rabbithole.core.builder.commands.string;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Represents the command Set on Redis database.
 *
 * @author Felipe Ros
 * @since 2.0.0
 * @version 1.0.1
 */
public class Sets implements Command, Write<String>, Execute<Boolean> {
    private final String key;
    private final String value;

    @Override
    public String commandName() {
        return "set";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.set(getKey(), getValue()).equals("OK"));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.of(false);
        }
    }

    private Sets(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
    }

    private Query<Sets> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;
        private String value;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Query<Sets> build() {
            return new Sets(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
