package br.com.rabbithole.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RedisConfigTest {

  @Test
  void redisConfigShouldReturnSuccess() {
    String prefix = "pfx";
    boolean debug = false;
    String host = "mocked_host";
    int port = 999;
    String user = "mocked_user";
    String password = "mocked_password";
    int connections = 10;

    RedisConfig mockedRedisConfig =
        new RedisConfig(prefix, debug, host, port, user, password, connections);

    assertEquals(prefix, mockedRedisConfig.getPrefix());
    assertEquals(debug, mockedRedisConfig.isDebug());
    assertEquals(host, mockedRedisConfig.getHost());
    assertEquals(port, mockedRedisConfig.getPort());
    assertEquals(user, mockedRedisConfig.getUser());
    assertEquals(password, mockedRedisConfig.getPassword());
    assertEquals(connections, mockedRedisConfig.getConnections());
  }
}
