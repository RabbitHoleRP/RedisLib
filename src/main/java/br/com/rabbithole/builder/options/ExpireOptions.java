package br.com.rabbithole.builder.options;

public class ExpireOptions {
    private boolean nx;
    private boolean xx;
    private boolean gt;
    private boolean lt;

    public ExpireOptions nx(boolean nx) {
        this.nx = nx;
        return this;
    }

    public ExpireOptions xx(boolean xx) {
        this.xx = xx;
        return this;
    }

    public ExpireOptions gt(boolean gt) {
        this.gt = gt;
        return this;
    }

    public ExpireOptions lt(boolean lt) {
        this.lt = lt;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(nx)
            result.append("NX");
        if(xx)
            result.append("XX");
        if(gt)
            result.append("GT");
        if(lt)
            result.append("LT");
        return result.toString();
    }
}
