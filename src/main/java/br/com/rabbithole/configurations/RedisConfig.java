package br.com.rabbithole.configurations;

/**
 * @author Felipe Ros
 * @Usage Classe que representa a configuração do Redis.
 * @since 1.0
 */
public class RedisConfig {
    private String host;
    private int port;
    private String user;
    private String password;
    private int connections;

    public RedisConfig(String host, int port, String user, String password, int connections) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.connections = connections;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }
}
