package br.com.rabbithole.core.builder.base.options;

/**
 * @author Felipe Ros
 * @Usage Representation of Redis command options.
 * @since 2.0
 * @version 1.0
 * @param <T> Generic - Generic value that extends from the interface {@link Options}
 */
@Deprecated
public interface CommandOptions<T extends Options> {

    /**
     * @Usage Used to get the Options.
     * @return Generic - Returns the Option.
     */
    T getOptions();
}
