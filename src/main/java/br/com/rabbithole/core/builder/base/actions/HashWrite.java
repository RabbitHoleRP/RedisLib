package br.com.rabbithole.core.builder.base.actions;

public interface HashWrite<Q> {
    String getKey();
    String getField();
    Q getValue();
}
