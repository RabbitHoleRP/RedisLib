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

    public String toString() {
        String result = "";

        if(nx)
            result += "NX ";
        if(xx)
            result += " XX ";
        if(get)
            result += " GET ";
        if(ex != 0D)
            result += " EX " + ex;
        if(px != 0D)
            result += " PX " + px;
        if(exat != 0D)
            result += " EXAT " + exat;
        if(pxat != 0D)
            result += " PXAT " + pxat;
        if(keepttl)
            result += " KEEPTTL";

        return result;
    }
}
