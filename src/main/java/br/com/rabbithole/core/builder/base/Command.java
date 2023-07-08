package br.com.rabbithole.core.builder.base;

/**
 * @author Felipe Ros
 * @Usage Represents a Redis command.
 * @since 2.0
 * @version 1.0
 */
public interface Command {

    /**
     * @Usage Used to get the Commands name.
     * @return String - Returns the Commands name.
     */
    String commandName();
}
