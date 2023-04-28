package br.com.rabbithole.core.enums;

import br.com.rabbithole.core.builder.QueryInterface;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Lista de Comandos Redis disponíveis.
 * @since 1.0
 */
public enum Commands implements QueryInterface {
    GET {
        @Override
        public Optional<String> get(String key) {
            return Optional.empty();
        }
    },

    SET {
        @Override
        public Optional<String> set(String key, String value) {
            return Optional.empty();
        }
    }
}
