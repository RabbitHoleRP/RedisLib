package br.com.rabbithole.core.builder.base.options;

/**
 * @author Felipe Ros
 * @Usage Representação de opções dos comandos Redis.
 * @since 2.0
 * @version 1.0
 * @param <T> Genérico - Valor genérico que estende da interface {@link Options}
 */
public interface CommandOptions<T extends Options> {

    /**
     * @Usage Utilizado para pegar as Opções.
     * @return Genérico - Retorna a Opção.
     */
    T getOptions();
}
