package br.com.rabbithole.core.builder.commands.string.sets;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class SetEx implements Command, Write<String>, Execute<Boolean> {
    private final String key;
    private final String value;
    private final int expireTime;

    @Override
    public String commandName() {
        return "setEx";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = RedisLib.getJedis().getResource()) {
            if (RedisLib.inDebug())
                RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
            return Optional.of(jedis.setex(getKey(), getExpireTime(), getValue()).equals("OK"));
        } catch (Exception exception) {
            RedisLib.getLogger().error("Query: " + commandName(), exception);
            return Optional.of(false);
        }
    }

    private SetEx(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.expireTime = builder.expireTime;
    }

    private Query<SetEx> query() {
        return new Query<>(this);
    }

    public static class Builder implements Execute<Boolean> {
        private String key;
        private String value;
        private int expireTime;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setExpire(int time) {
            this.expireTime = time;
            return this;
        }

        public Query<SetEx> build() {
            return new SetEx(this).query();
        }

        @Override
        public Optional<Boolean> execute() {
            return build().getCommand().execute();
        }
    }
}
