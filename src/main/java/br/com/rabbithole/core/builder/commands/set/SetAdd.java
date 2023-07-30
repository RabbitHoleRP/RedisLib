package br.com.rabbithole.core.builder.commands.set;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Adiciona um membro a coleção (Set). Caso este membro já esteja dentro da coleção a ação será desconsiderada.
 *
 * @author Felipe Ros
 * @since 2.4.0
 * @version 1.0
 */
public class SetAdd implements Command, Write<String>, Execute<Boolean> {
    private final String key;
    private final String value;

    @Override
    public String commandName() {
        return "setAdd";
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
            if (RedisLib.inDebug()) RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.sadd(getKey(), getValue()) != 0);
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private SetAdd(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
    }

    private Query<SetAdd> query() {
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

        public Query<SetAdd> build() {
            return new SetAdd(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
