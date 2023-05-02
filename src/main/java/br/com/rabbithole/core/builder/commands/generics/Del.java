package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.actions.Read;

public class Del implements Command, Read {
    private final String key;

    @Override
    public String commandName() {
        return "del";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    private Del(Builder builder) {
        this.key = builder.key;
    }

    private Query<Del> query() {
        return new Query<>(this);
    }

    public static class Builder {
        private String key;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Query<Del> build() {
            return new Del(this).query();
        }
    }
}
