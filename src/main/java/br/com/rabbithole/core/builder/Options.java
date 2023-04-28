package br.com.rabbithole.core.builder;

public interface Options {
    public int setExpire(int seconds);
    public boolean setOnlyNotExists(boolean isActivated);
    public boolean setIfExists(boolean isActivated);
    public String get(boolean isActivated);
}
