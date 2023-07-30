package br.com.rabbithole.core.builder.base.actions;

/**
 * Representation of the Action Read on Redis database.
 *
 * @author Felipe Ros
 * @since 2.0
 * @version 1.0.1
 */
public interface Read {

    /**
     * Used to get the Key.
     *
     * @return String - Returns the Key.
     */
    String getKey();
}
