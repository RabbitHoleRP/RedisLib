package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Verifica o tipo da Chave.
 * @since 2.0
 * @version 1.0
 */
public class Type implements Command, Read, Execute<String> {
  private final String key;

  @Override
  public String commandName() {
    return "type";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public Optional<String> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(jedis.type(getKey()));
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private Type(Builder builder) {
    this.key = builder.key;
  }

  private Query<Type> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<String> {
    private String key;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Query<Type> build() {
      return new Type(this).query();
    }

    @Override
    public Optional<String> execute() {
      return build().getCommand().execute();
    }
  }
}
