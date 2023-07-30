package br.com.rabbithole.core.builder.base.actions;

/**
 * Representation of the Action Write in Hash on Redis database.
 *
 * @author Felipe Ros
 * @since 2.0
 * @version 1.0.1
 * @param <Q> Generic - Generic value used on getValue() method.
 */
public interface HashWrite<Q> {

    /**
     * Used to get the Key.
     *
     * @return String - Returns the Key.
     */
    String getKey();

    /**
     * Used to get the Field.
     *
     * @return String - Returns the Field.
     */
    String getField();

    /**
     * @Usage Used to get the Value.
     *
     * @return Genérico - Returns the Value.
     */
    Q getValue();
}
