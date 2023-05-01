package br.com.rabbithole.core.builder.commands.generics;

import br.com.rabbithole.core.builder.Query;
import br.com.rabbithole.core.builder.base.Command;
import br.com.rabbithole.core.builder.base.options.CommandOptions;
import br.com.rabbithole.core.builder.base.actions.Write;
import br.com.rabbithole.core.builder.options.SetOptions;

public class Set implements Command, Write, CommandOptions<SetOptions> {
    private final String key;
    private final String value;
    private final SetOptions options;

    private Set(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
        this.options = builder.options;
    }

    @Override
    public String commandName() {
        return "set";
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public SetOptions getOptions() {
        return this.options;
    }

    private Query<Set> query() {
        return new Query<>(this);
    }

    public static class Builder {
        private String key;
        private String value;
        private SetOptions options;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOptions(SetOptions options) {
            this.options = options;
            return this;
        }

        public Query<Set> build() {
            return new Set(this).query();
        }
    }
}
