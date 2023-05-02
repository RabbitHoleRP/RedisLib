package br.com.rabbithole.core.builder.base;

import java.util.Optional;

public interface Execute<T> {
    Optional<T> execute();
}
