package br.com.rabbithole.configurations;

/**
 * @author Felipe Ros
 * @Usage Class that represents Redis's settings.
 * @since 2.0
 * @version 1.0
 */
public class RedisConfig {
    private String prefix;
    private boolean debug;
    private String host;
    private int port;
    private String user;
    private String password;
    private int connections;

    /**
     * @Usage Build Redis connection config.
     * @param prefix String - Logs Prefixes.
     * @param debug Boolean - Is debugging.
     * @param host String - Address for Redis connection.
     * @param port int - Port for Redis connection.
     * @param user String - User for Redis connection.
     * @param password String - Password for Redis connection.
     * @param connections int - Number of available connections in Redis.
     */
    public RedisConfig(String prefix, boolean debug, String host, int port, String user, String password, int connections) {
        this.prefix = prefix;
        this.debug = debug;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.connections = connections;
    }

    /**
     * @Usage Used to get the Prefix.
     * @return String - Returns the Prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @Usage Used to set the Prefix.
     * @param prefix String - Logs Prefix.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @Usage Used to get the debugging state.
     * @return Boolean - Returns debugging state
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @Usage Used to set the debugging state.
     * @param debug Boolean - Debugging state.
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @Usage Used to get the connection address.
     * @return String - Returns the connection address.
     */
    public String getHost() {
        return host;
    }

    /**
     * @Usage Used to set the connection address.
     * @param host String - Connection address.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @Usage Used to get the connection port.
     * @return int - Returns the connection port.
     */
    public int getPort() {
        return port;
    }

    /**
     * @Usage Used to set the connection port.
     * @param port int - Connection port.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @Usage Used to get the connection User.
     * @return String - Returns the connection User.
     */
    public String getUser() {
        return user;
    }

    /**
     * @Usage Used to set the connection User.
     * @param user String - connection User.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @Usage Used to get the connection password.
     * @return String - Connection password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @Usage Used to set the connection password.
     * @param password String - Connection password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @Usage Used to get the number of available connections.
     * @return int - Number of available connections.
     */
    public int getConnections() {
        return connections;
    }

    /**
     * @Usage Used to set the number of available connections.
     * @param connections int - Number of available connections.
     */
    public void setConnections(int connections) {
        this.connections = connections;
    }
}
