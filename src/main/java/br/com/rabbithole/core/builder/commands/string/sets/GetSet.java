package br.com.rabbithole.core.builder.commands.string.sets;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import br.com.rabbithole.core.builder.base.options.CommandOptions;
import br.com.rabbithole.core.builder.options.SetOptions;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class GetSet implements Command, Write<String>, CommandOptions<SetOptions>, Execute<String> {
    private final String key;
    private final String value;
    private final SetOptions options;

    @Override
    public String commandName() {
        return "getSet";
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
    public SetOptions getOptions() {
        return this.options;
    }

    @Override
    public Optional<String> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (getOptions() != null) {
                int expireTime = getOptions().getExpire();
                if (expireTime != 0) {
                    String resultOfQuery = jedis.getSet(getKey(), getValue());
                    jedis.expire(getKey(), expireTime);
                    if (RedisLib.inDebug())
                        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
                    return Optional.of(resultOfQuery);
                }
            }
            return Optional.of(jedis.getSet(getKey(), getValue()));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.empty();
        }
    }

    private GetSet(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.options = builder.options;
    }

    private Query<GetSet> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<String> {
        private String key;
        private String value;
        private SetOptions options;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOptions(SetOptions options) {
            this.options = options;
            return this;
        }

        public Query<GetSet> build() {
            return new GetSet(this).query();
        }

        @Override
        public Optional<String> execute() {
            return build().getCommand().execute();
        }
    }
}
