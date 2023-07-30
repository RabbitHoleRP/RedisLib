package br.com.rabbithole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockConstruction;

import br.com.rabbithole.configurations.RedisConfig;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.junit.jupiter.MockitoExtension;
import redis.clients.jedis.JedisPool;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class RedisLibTest {

    PodamFactory factory = new PodamFactoryImpl();

    @Test
    void initShouldReturnSuccess() {
        RedisConfig mockedConfig = factory.manufacturePojo(RedisConfig.class);
        mockedConfig.setDebug(true);

        try (MockedConstruction<JedisPool> mockJedisPool = mockConstruction(JedisPool.class)) {
            RedisLib.init(mockedConfig);

            List<JedisPool> constructedJedisPool = mockJedisPool.constructed();
            assertEquals(1, constructedJedisPool.size());
        }

        assertNotNull(RedisLib.getLogger());
        assertNotNull(RedisLib.getJedis());
        assertTrue(RedisLib.inDebug());
    }

    @Test
    void initShouldReturnSuccessWhenDebugIsFalse() {
        RedisConfig mockedConfig = factory.manufacturePojo(RedisConfig.class);
        mockedConfig.setDebug(false);

        try (MockedConstruction<JedisPool> mockJedisPool = mockConstruction(JedisPool.class)) {
            RedisLib.init(mockedConfig);

            List<JedisPool> constructedJedisPool = mockJedisPool.constructed();
            assertEquals(1, constructedJedisPool.size());
        }

        assertNotNull(RedisLib.getLogger());
        assertNotNull(RedisLib.getJedis());
        assertFalse(RedisLib.inDebug());
    }
}
