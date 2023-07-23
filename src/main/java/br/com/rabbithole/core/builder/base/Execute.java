package br.com.rabbithole.core.builder.base;

import java.util.Optional;

/**
 * @author Felipe Ros @Usage Represents the execution of a Redis Command.
 * @since 2.0
 * @version 1.0
 */
public interface Execute<T> {

  /**
   * @Usage Used to get the value of the Executed command.
   *
   * @return Optional Generic - Returns the value of the Executed command.
   */
  Optional<T> execute();
}
