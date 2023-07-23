package br.com.rabbithole.core.builder;

import br.com.rabbithole.core.builder.base.Command;

public class Query<T extends Command> {
  private final T command;

  public Query(T command) {
    this.command = command;
  }

  public T getCommand() {
    return command;
  }
}
