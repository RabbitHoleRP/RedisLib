package br.com.rabbithole.core.builder.base;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Representa a Execução de um Comando do Redis.
 * @since 2.0
 * @version 1.0
 */
public interface Execute {

    /**
     * @Usage Utilizado para pegar o Valor do comando Executado!
     * @return Optional Genérico - Retorna o Valor do comando Executado.
     */
    Optional<?> execute();
}
