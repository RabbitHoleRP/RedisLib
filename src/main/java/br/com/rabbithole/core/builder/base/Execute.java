package br.com.rabbithole.core.builder.base;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Representa a Execução de um Comando do Redis.
 * @since 2.0
 * @version 1.0
 * @param <T> Genérico - Valor genérico utilizado para definir o Retorno no método execute().
 */
public interface Execute<T> {

    /**
     * @Usage Utilizado para pegar o Valor do comando Executado!
     * @return Optional Genérico - Retorna o Valor do comando Executado.
     */
    Optional<T> execute();
}
