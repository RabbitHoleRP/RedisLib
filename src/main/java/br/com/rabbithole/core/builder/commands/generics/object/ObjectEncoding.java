package br.com.rabbithole.core.builder.commands.generics.object;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Retorna a codificação interna do objeto armazenado.
 *
 * @author Felipe Ros
 * @since 2.3.0
 * @version 1.0.1
 */
public class ObjectEncoding implements Command, Read, Execute<String> {
    private final String key;

    @Override
    public String commandName() {
        return "objectEncoding";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<String> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            String result = jedis.objectEncoding(getKey());
            return (result == null ? Optional.empty() : Optional.of(result));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private ObjectEncoding(Builder builder) {
        this.key = builder.key;
    }

    private Query<ObjectEncoding> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<String> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<ObjectEncoding> build() {
            return new ObjectEncoding(this).query();
        }

        @Override
        public Optional<String> execute() {
            return build().getCommand().execute();
        }
    }
}
