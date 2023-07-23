package br.com.rabbithole.core.builder.commands.hash;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.HashRead;
import java.util.Optional;
import redis.clients.jedis.Jedis;

public class HashDel implements Command, HashRead, Execute<Boolean> {
  private final String key;
  private final String field;

  @Override
  public String commandName() {
    return "hashDel";
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
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + "has executed!");
      return Optional.of(jedis.hdel(getKey(), getField()) != 0);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  // Construtor
  private HashDel(Builder builder) {
    this.key = builder.key;
    this.field = builder.field;
  }

  // Query
  private Query<HashDel> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
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

    public Query<HashDel> build() {
      return new HashDel(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
