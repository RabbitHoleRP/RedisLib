package br.com.rabbithole.builder.options;

public class SetOptions {
    private double ex;
    private double px;
    private double exat;
    private double pxat;
    private boolean nx;
    private boolean xx;
    private boolean keepttl;
    private boolean get;

    public SetOptions ex(double ex) {
        this.ex = ex;
        return this;
    }

    public SetOptions px(double px) {
        this.px = px;
        return this;
    }

    public SetOptions exat(double exat) {
        this.exat = exat;
        return this;
    }

    public SetOptions pxat(double pxat) {
        this.pxat = pxat;
        return this;
    }

    public SetOptions nx(boolean nx) {
        this.nx = nx;
        return this;
    }

    public SetOptions xx(boolean xx) {
        this.xx = xx;
        return this;
    }

    public SetOptions keepttl(boolean keepttl) {
        this.keepttl = keepttl;
        return this;
    }

    public SetOptions get(boolean get) {
        this.get = get;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(nx)
            result.append("NX");
        if(xx)
            result.append("XX");
        if(get)
            result.append("GET");
        if(ex != 0D)
            result.append(String.format("EX %s", ex));
        if(px != 0D)
            result.append(String.format("PX %s", px));
        if(exat != 0D)
            result.append(String.format("EXAT %s", exat));
        if(pxat != 0D)
            result.append(String.format("PXAT %s", pxat));
        if(keepttl)
            result.append("KEEPTTL");
        return result.toString();
    }
}
