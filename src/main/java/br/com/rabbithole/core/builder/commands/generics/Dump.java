package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Retorna o valor serializado de uma chave.
 * @since 2.0
 * @version 1.0
 */
public class Dump implements Command, Read, Execute<byte[]> {
  private final String key;

  @Override
  public String commandName() {
    return "dump";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public Optional<byte[]> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      byte[] dumpResult = jedis.dump(getKey());
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return (dumpResult.length == 0 ? Optional.empty() : Optional.of(dumpResult));
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private Dump(Builder builder) {
    this.key = builder.key;
  }

  private Query<Dump> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<byte[]> {
    private String key;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Query<Dump> build() {
      return new Dump(this).query();
    }

    @Override
    public Optional<byte[]> execute() {
      return build().getCommand().execute();
    }
  }
}
