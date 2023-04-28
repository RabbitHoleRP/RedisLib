package br.com.rabbithole.core.builder;

import java.util.Optional;

public interface QueryInterface {
    default Optional<String> get (String key) {
        return Optional.empty();
    }
    default Optional<String> set (String key, String value) {
        return Optional.empty();
    }
}
