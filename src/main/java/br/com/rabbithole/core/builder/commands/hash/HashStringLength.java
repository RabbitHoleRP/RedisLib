package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashRead;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Retorna o tamanho da String de um campo em uma Hash.
 * @since 2.0
 * @version 1.0
 */
public class HashStringLength implements Command, HashRead, Execute<Long> {
  private final String key;
  private final String field;

  @Override
  public String commandName() {
    return "hashStringLength";
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
  public Optional<Long> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      long lengthOfString = jedis.hstrlen(getKey(), getField());
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return (lengthOfString == 0 ? Optional.empty() : Optional.of(lengthOfString));
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private HashStringLength(Builder builder) {
    this.key = builder.key;
    this.field = builder.field;
  }

  private Query<HashStringLength> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Long> {
    private String key;
    private String field;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setField(String field) {
      this.field = field;
      return this;
    }

    public Query<HashStringLength> build() {
      return new HashStringLength(this).query();
    }

    @Override
    public Optional<Long> execute() {
      return build().getCommand().execute();
    }
  }
}
