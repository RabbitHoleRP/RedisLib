package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.RedisLib;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.Execute;
import br.com.rabbithole.core.builder.base.actions.Write;
import java.util.Optional;
import redis.clients.jedis.Jedis;

/**
 * @author Felipe Ros @Usage Renomear uma chave caso o novo nome não esteja em uso.
 * @since 2.0
 * @version 1.0
 */
public class RenameNx implements Command, Write<String>, Execute<Boolean> {
  private final String key;
  private final String value;

  @Override
  public String commandName() {
    return "renameNx";
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
      if (RedisLib.inDebug())
        RedisLib.getLogger().info("Query: " + commandName() + " has executed!");
      return Optional.of(jedis.renamenx(getKey(), getValue()) != 0);
    } catch (Exception exception) {
      RedisLib.getLogger().error("Query: " + commandName(), exception);
      return Optional.empty();
    }
  }

  private RenameNx(Builder builder) {
    this.key = builder.key;
    this.value = builder.value;
  }

  private Query<RenameNx> query() {
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

    public Query<RenameNx> build() {
      return new RenameNx(this).query();
    }

    @Override
    public Optional<Boolean> execute() {
      return build().getCommand().execute();
    }
  }
}
