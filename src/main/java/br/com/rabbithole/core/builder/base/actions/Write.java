package br.com.rabbithole.core.builder.base.actions;

/**
 * @author Felipe Ros
 * @Usage Representação da Ação Read no banco de dados Redis.
 * @since 2.0
 * @version 1.0
 * @param <Q> Genérico - Valor genérico utilizado no método getValue().
 */
public interface Write<Q> {

    /**
     * @Usage Utilizado para pegar a Chave.
     * @return String - Retorna a Chave.
     */
    String getKey();

    /**
     * @Usage Utilizado para pegar o Valor.
     * @return Genérico - Retorna o Valor.
     */
    Q getValue();
}
