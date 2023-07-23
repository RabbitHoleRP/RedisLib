package br.com.rabbithole.core.builder.commands.generics.object;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Read;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Retorna a contagem de referências do objeto armazenado.
 * @since 2.0
 * @version 1.0
 */
public class ObjectReferenceCount implements Command, Read, Execute<Long> {
  private final String key;

  @Override
  public String commandName() {
    return "objectIdleTime";
  }

  @Override
  public String getKey() {
    return this.key;
  }

  @Override
  public Optional<Long> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(jedis.objectRefcount(getKey()));
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private ObjectReferenceCount(Builder builder) {
    this.key = builder.key;
  }

  private Query<ObjectReferenceCount> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Long> {
    private String key;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Query<ObjectReferenceCount> build() {
      return new ObjectReferenceCount(this).query();
    }

    @Override
    public Optional<Long> execute() {
      return build().getCommand().execute();
    }
  }
}
