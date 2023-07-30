package br.com.rabbithole.core.builder.base.options;

/**
 * Representation of Redis command options.
 *
 * @author Felipe Ros
 * @since 2.0
 * @version 1.0.1
 * @param <T> Generic - Generic value that extends from the interface {@link Options}
 */
@Deprecated
public interface CommandOptions<T extends Options> {

    /**
     * Used to get the Options.
     *
     * @return Generic - Returns the Option.
     */
    T getOptions();
}
