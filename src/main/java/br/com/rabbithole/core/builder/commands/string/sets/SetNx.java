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

public class SetNx implements Command, Write<String>, CommandOptions<SetOptions>, Execute<Boolean> {
  private final String key;
  private final String value;
  private final SetOptions options;

  @Override
  public String commandName() {
    return "setNx";
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

  // TODO: REFATORAR ESTE COMANDO REMOVENDO OPTIONS!
  @Override
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (getOptions() != null) {
        int expireTime = getOptions().getExpire();
        if (expireTime != 0) {
          boolean resultOfQuery = jedis.setnx(getKey(), getValue()) != 0;
          if (resultOfQuery) return Optional.of(jedis.expire(getKey(), expireTime) != 0);
          if (RedisLib.inDebug())
            RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
          return Optional.of(false);
        }
      }
      return Optional.of(jedis.setnx(getKey(), getValue()) != 0);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  private SetNx(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
    this.options = builder.options;
  }

  private Query<SetNx> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
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

    public Query<SetNx> build() {
      return new SetNx(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
