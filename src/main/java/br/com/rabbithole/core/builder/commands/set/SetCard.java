package br.com.rabbithole.core.builder.commands.set;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Retorna o número de membros de uma coleção (Set).
 *
 * @author Felipe Ros
 * @since 2.4.0
 * @version 1.0
 */
public class SetCard implements Command, Read, Execute<Long> {
    private final String key;

    @Override
    public String commandName() {
        return "setCard";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Long> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.scard(getKey()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private SetCard(Builder builder) {
        this.key = builder.key;
    }

    private Query<SetCard> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Long> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<SetCard> build() {
            return new SetCard(this).query();
        }

        @Override
        public Optional<Long> execute() {
            return build().getCommand().execute();
        }
    }
}
