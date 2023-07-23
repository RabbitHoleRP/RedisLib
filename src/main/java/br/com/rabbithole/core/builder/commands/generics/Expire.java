package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class Expire implements Command, Write<Integer>, Execute<Boolean> {
  private final String key;
  private final int value;

  @Override
  public String commandName() {
    return "expire";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public Integer getValue() {
    return this.value;
  }

  @Override
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + "has executed!");
      return Optional.of(jedis.expire(getKey(), getValue()) != 0);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  private Expire(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
  }

  private Query<Expire> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
    private String key;
    private int value;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setValue(int value) {
      this.value = value;
      return this;
    }

    public Query<Expire> build() {
      return new Expire(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
