package br.com.rabbithole.core.builder;

import br.com.rabbithole.core.enums.Commands;

/**
 * @author Felipe Ros and Gabriel Fekete
 * @Usage
 * @since 2.0
 */
public class Query {
    private final Commands command;
    private final String key;
    private final String field;
    private final String value;
    private final Options options;

    public Commands getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public Options getOptions() {
        return options;
    }

    private Query(QueryBuilder builder) { //TODO TRANSFORMAR EM PUBLIC
        this.command = builder.command;
        this.key = builder.key;
        this.field = builder.field;
        this.value = builder.value;
        this.options = builder.options;
    }

    public static class QueryBuilder { //SEPARAR CLASSE E CRIAR POR COMANDO
        private Commands command;
        private String key;
        private String field;
        private String value;
        private Options options;

        public QueryBuilder setCommand(Commands command) {
            this.command = command;
            return this;
        }

        public QueryBuilder setKey(String key) {
            this.key = key;
            return this;
        }

        public QueryBuilder setField(String field) {
            this.field = field;
            return this;
        }

        public QueryBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public QueryBuilder setOptions(Options options) {
            this.options = options;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }
}
