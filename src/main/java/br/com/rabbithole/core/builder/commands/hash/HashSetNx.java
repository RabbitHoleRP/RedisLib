package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashWrite;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Define um valor dentro da Hash caso não exista o campo.
 * @since 2.0
 * @version 1.0
 */
public class HashSetNx implements Command, HashWrite<String>, Execute<Boolean> {
  private final String key;
  private final String field;
  private final String value;

  @Override
  public String commandName() {
    return "hashSetNx";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public String getField() {
    return this.field;
  }

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      boolean resultOfQuery = jedis.hsetnx(getKey(), getField(), getValue()) != 0;
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(resultOfQuery);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  private HashSetNx(Builder builder) {
    this.key = builder.key;
    this.field = builder.field;
    this.value = builder.value;
  }

  private Query<HashSetNx> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
    private String key;
    private String field;
    private String value;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setField(String field) {
      this.field = field;
      return this;
    }

    public Builder setValue(String value) {
      this.value = value;
      return this;
    }

    public Query<HashSetNx> build() {
      return new HashSetNx(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
