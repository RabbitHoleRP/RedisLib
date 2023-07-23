package br.com.rabbithole.core.builder.commands.generics.time;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Define o tempo (em unix time) para uma chave ser deletada.
 * @since 1.0
 * @version 1.0
 */
public class ExpireAt implements Command, Write<Long>, Execute<Boolean> {
  private final String key;
  private final Long value;

  @Override
  public String commandName() {
    return "expireAt";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public Long getValue() {
    return this.value;
  }

  @Override
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(jedis.expireAt(getKey(), getValue()) != 0);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  private ExpireAt(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
  }

  private Query<ExpireAt> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
    private String key;
    private Long value;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setValue(Long value) {
      this.value = value;
      return this;
    }

    public Query<ExpireAt> build() {
      return new ExpireAt(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
