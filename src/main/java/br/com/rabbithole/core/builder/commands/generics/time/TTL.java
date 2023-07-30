package br.com.rabbithole.core.builder.commands.generics.time;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * Retorna o tempo restante de uma Chave (em segundos).
 *
 * @author Felipe Ros
 * @since 2.3.0
 * @version 1.0.1
 */
public class TTL implements Command, Read, Execute<Long> {
    private final String key;

    @Override
    public String commandName() {
        return "ttl";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Long> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.ttl(getKey()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private TTL(Builder builder) {
        this.key = builder.key;
    }

    private Query<TTL> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Long> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<TTL> build() {
            return new TTL(this).query();
        }

        @Override
        public Optional<Long> execute() {
            return build().getCommand().execute();
        }
    }
}
