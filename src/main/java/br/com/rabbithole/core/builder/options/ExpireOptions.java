package br.com.rabbithole.core.builder.options;

import br.com.rabbithole.core.builder.base.options.Options;

//TODO: CÓDIGO TEMPORÁRIO NOMES SERÃO ALTERADOS DPS
@Deprecated
public class ExpireOptions implements Options {
    private final boolean nx;
    private final boolean xx;
    private final boolean gt;
    private final boolean lt;

    public boolean isNx() {
        return nx;
    }

    public boolean isXx() {
        return xx;
    }

    public boolean isGt() {
        return gt;
    }

    public boolean isLt() {
        return lt;
    }

    private ExpireOptions(Builder builder) {
        this.nx = builder.nx;
        this.xx = builder.xx;
        this.gt = builder.gt;
        this.lt = builder.lt;
    }

    public static class Builder {
        private boolean nx;
        private boolean xx;
        private boolean gt;
        private boolean lt;

        public Builder setNx() {
            this.nx = true;
            return this;
        }

        public Builder setXx() {
            this.xx = true;
            return this;
        }

        public Builder setGt() {
            this.gt = true;
            return this;
        }

        public Builder setLt() {
            this.lt = true;
            return this;
        }

        public ExpireOptions build() {
            return new ExpireOptions(this);
        }
    }
}
