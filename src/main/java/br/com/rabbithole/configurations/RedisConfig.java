package br.com.rabbithole.configurations;

/**
 * @author Felipe Ros
 * @Usage Classe que representa a configuração do Redis.
 * @since 2.0
 * @version 1.0
 */
public class RedisConfig {
    private String prefix;
    private String host;
    private int port;
    private String user;
    private String password;
    private int connections;

    /**
     * @Usage Construir configurações de conexão do Redis.
     * @param prefix String - Prefix das Logs.
     * @param host String - Endereço para conexão do Redis.
     * @param port int - Porta para conexão do Redis.
     * @param user String - Usuário para conexão do Redis.
     * @param password String - Senha para conexão do Redis.
     * @param connections int - Número de conexões disponíveis no Redis.
     */
    public RedisConfig(String prefix, String host, int port, String user, String password, int connections) {
        this.prefix = prefix;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.connections = connections;
    }

    /**
     * @Usage Utilizado para pegar a Prefix.
     * @return String - Retorna a Prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @Usage Utilizado para definir a Prefix.
     * @param prefix String - Prefix das Logs.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @Usage Utilizado para pegar o Endereço de Conexão.
     * @return String - Retorna o Endereço de Conexão.
     */
    public String getHost() {
        return host;
    }

    /**
     * @Usage Utilizado para definir o Endereço de Conexão.
     * @param host String - Endereço de Conexão.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @Usage Utilizado para pegar a Porta de Conexão.
     * @return int - Retorna a Porta de Conexão.
     */
    public int getPort() {
        return port;
    }

    /**
     * @Usage Utilizado para definir a Porta de Conexão.
     * @param port int - Porta de Conexão.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @Usage Utilizado para pegar o Usuário de Conexão.
     * @return String - Retorna o Usuário de Conexão.
     */
    public String getUser() {
        return user;
    }

    /**
     * @Usage Utilizado para definir o Usuário de Conexão.
     * @param user String - Usuário de Conexão.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @Usage Utilizado para pegar a Senha de Conexão.
     * @return String - Senha de Conexão.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @Usage Utilizado para definir a Senha de Conexão.
     * @param password String - Senha de Conexão.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @Usage Utilizado para pegar o Número de Conexões disponíveis.
     * @return int - Número de Conexões disponíveis.
     */
    public int getConnections() {
        return connections;
    }

    /**
     * @Usage Utilizado para definir o Número de Conexões disponíveis.
     * @param connections int - Número de Conexões disponíveis.
     */
    public void setConnections(int connections) {
        this.connections = connections;
    }
}
