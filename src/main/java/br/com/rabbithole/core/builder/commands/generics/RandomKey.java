package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Retorna uma Chave aleatória.
 * @since 2.0
 * @version 1.0
 */
public class RandomKey implements Command, Execute<String> {
  @Override
  public String commandName() {
    return "randomKey";
  }

  @Override
  public Optional<String> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(jedis.randomKey());
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private RandomKey(Builder builder) {}

  private Query<RandomKey> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<String> {
    public Query<RandomKey> build() {
      return new RandomKey(this).query();
    }

    @Override
    public Optional<String> execute() {
      return build().getCommand().execute();
    }
  }
}
