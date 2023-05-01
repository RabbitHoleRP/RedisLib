package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.actions.Read;

public class Get implements Command, Read {
    private final String key;

    @Override
    public String commandName() {
        return "get";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    private Get(Builder builder) {
        this.key = builder.key;
    }

    private Query<Get> query() {
        return new Query<>(this);
    }

    public static class Builder {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Get> build() {
            return new Get(this).query();
        }
    }
}
