package br.com.rabbithole.core.builder.base;

import java.util.Optional;

/**
 * Represents the execution of a Redis Command.
 *
 * @author Felipe Ros
 * @since 2.0
 * @version 1.0.1
 */
public interface Execute<T> {

    /**
     * Used to get the value of the Executed command.
     *
     * @return Optional Generic - Returns the value of the Executed command.
     */
    Optional<T> execute();
}
