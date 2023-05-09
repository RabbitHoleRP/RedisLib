package br.com.rabbithole.core.builder.base;

/**
 * @author Felipe Ros
 * @Usage Representa um Comando do Redis.
 * @since 2.0
 * @version 1.0
 */
public interface Command {

    /**
     * @Usage Utilizado para pegar o Nome do Comando.
     * @return String - Retorna o Nome do Comando.
     */
    String commandName();
}
