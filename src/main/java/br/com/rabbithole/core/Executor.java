package br.com.rabbithole.core;

import br.com.rabbithole.core.annotations.Beta;
import br.com.rabbithole.core.annotations.Since;
import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Executor das consultas Redis criadas.
 * @since 2.0
 */
public class Executor {
    /*
    public <T extends Command> Optional<String> execute(Query<T> query) {
        return Optional.of("");
    }
     */

    public static <Q extends Command> Optional<Object> execute(Query<Q> query) {
        return Optional.of("");
    }
}
