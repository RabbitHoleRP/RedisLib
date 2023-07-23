package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Renomeia o nome de uma Chave.
 * @since 2.0
 * @version 1.0
 */
public class Rename implements Command, Write<String>, Execute<Boolean> {
  private final String key;
  private final String value;

  @Override
  public String commandName() {
    return "rename";
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
  public Optional<Boolean> execute() {
    try (Jedis jedis = RedisLib.getJedis().getResource()) {
      if (!jedis.exists(getKey())) return Optional.of(false);
      String resultOfAction = jedis.rename(getKey(), getValue());
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(resultOfAction.equalsIgnoreCase("ok"));
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.of(false);
    }
  }

  private Rename(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
  }

  private Query<Rename> query() {
    return new Query<>(this);
  }

  public static class Builder implements Execute<Boolean> {
    private String key;
    private String value;

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setValue(String value) {
      this.value = value;
      return this;
    }

    public Query<Rename> build() {
      return new Rename(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
