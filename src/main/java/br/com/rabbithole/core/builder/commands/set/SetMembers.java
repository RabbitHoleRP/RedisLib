package br.com.rabbithole.core.builder.commands.set;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import redis.clients.jedis.Jedis;

import java.util.Optional;
import java.util.Set;

/**
 * Retorna os membros de uma coleção (Set).
 *
 * @author Felipe Ros
 * @since 2.4.0
 * @version 1.0
 */
public class SetMembers implements Command, Read, Execute<Set<String>> {
    private final String key;

    @Override
    public String commandName() {
        return "setMembers";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Optional<Set<String>> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.smembers(getKey()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private SetMembers(Builder builder) {
        this.key = builder.key;
    }

    private Query<SetMembers> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Set<String>> {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<SetMembers> build() {
            return new SetMembers(this).query();
        }

        @Override
        public Optional<Set<String>> execute() {
            return build().getCommand().execute();
        }
    }
}
