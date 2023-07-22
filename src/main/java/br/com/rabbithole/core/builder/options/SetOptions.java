package br.com.rabbithole.core.builder.options;

import br.com.rabbithole.core.builder.base.options.Options;

@Deprecated
public class SetOptions implements Options {
    private final boolean ifExpire;
    private final int expire;
    private final boolean ifNotExists;
    private final boolean ifExists;
    private final boolean get;

    public boolean isIfExpire() {
        return ifExpire;
    }

    public int getExpire() {
        return expire;
    }

    public boolean isIfNotExists() {
        return ifNotExists;
    }

    public boolean isIfExists() {
        return ifExists;
    }

    public boolean isGet() {
        return get;
    }

    private SetOptions(Builder builder) {
        this.ifExpire= builder.ifExpire;
        this.expire = builder.expire;
        this.ifNotExists = builder.ifNotExists;
        this.ifExists = builder.ifExists;
        this.get = builder.get;
    }

    public static class Builder {
        private boolean ifExpire = false;
        private int expire;
        private boolean ifNotExists;
        private boolean ifExists;
        private boolean get;

        public Builder setExpire(int seconds) {
            this.ifExpire = true;
            this.expire = seconds;
            return this;
        }

        /* TODO: DESATIVADO TEMPORARIAMENTE!
        public Builder setIfNotExists() {
            this.ifNotExists = true;
            return this;
        }

        public Builder setIfExists() {
            this.ifExists = true;
            return this;
        }

        public Builder setGet() {
            this.get = true;
            return this;
        }
         */
        public SetOptions build() {
            return new SetOptions(this);
        }
    }
}
