package br.com.rabbithole.core.builder.base.actions;

/**
 * @author Gabriel Fekete
 * @Usage Representação da Ação Read no banco de dados Redis
 * @since 2.0
 */
public interface Write<Q> {
    String getKey();
    Q getValue();
}
