package br.com.rabbithole.core.builder.base;

/**
 * Represents a Redis command.
 *
 * @author Felipe Ros
 * @since 2.0
 * @version 1.0.1
 */
public interface Command {

    /**
     * Used to get the Commands name.
     *
     * @return String - Returns the Commands name.
     */
    String commandName();
}
