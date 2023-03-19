package br.com.rabbithole.builder;

import br.com.rabbithole.builder.options.SetOptions;

import java.util.Arrays;

public class Query {
    private final String key;
    private final String value;
    private final String options;

    private Query(String key, String value, String options) {
        this.key = key;
        this.value = value;
        this.options = options;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getOptions() {
        return options;
    }

    public static class Builder {
        private String key;
        private String value;
        private String options;

        public Builder get(String key) {
            this.key = key;
            return this;
        }

        public Builder set(String key, String value) {
            this.key = key;
            this.value = value;
            return this;
        }

        public Builder set(String key, String value, SetOptions options) {
            this.key = key;
            this.value = value;
            this.options = options.toString();
            return this;
        }

        public Builder del(String ...key) {
            this.key = "";
            if (key == null)
                return this;

            int iMax = key.length - 1;
            if (iMax == -1)
                return this;

            StringBuilder b = new StringBuilder();
            for (int i = 0; i <= iMax ; i++) {
                if(i != iMax) {
                    b.append(String.valueOf(key[i] + " "));
                } else {
                    b.append(String.valueOf(key[i]));
                }
            }
            this.key = b.toString();
            return this;
        }

        public Query build() {
            return new Query(key, value, options);
        }

    }
}
