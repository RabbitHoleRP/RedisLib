package br.com.rabbithole.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class RedisConfigurationTest {

    PodamFactory factory = new PodamFactoryImpl();

    @Test
    void buildPoolConfigShouldReturnSuccess() {
        RedisConfig mockedRedisConfig = factory.manufacturePojo(RedisConfig.class);

        RedisConfiguration actualResponse = new RedisConfiguration(mockedRedisConfig);

        assertNotNull(actualResponse);
        assertEquals(mockedRedisConfig.getConnections(), actualResponse.getJedis().getMaxTotal());
        assertEquals(128, actualResponse.getJedis().getMaxIdle());
        assertEquals(16, actualResponse.getJedis().getMinIdle());
        assertTrue(actualResponse.getJedis().getTestOnBorrow());
        assertTrue(actualResponse.getJedis().getTestOnReturn());
        assertTrue(actualResponse.getJedis().getTestWhileIdle());
        assertEquals(Duration.ofSeconds(60), actualResponse.getJedis().getMinEvictableIdleDuration());
        assertEquals(Duration.ofSeconds(30), actualResponse.getJedis().getTimeBetweenEvictionRuns());
        assertEquals(3, actualResponse.getJedis().getNumTestsPerEvictionRun());
        assertTrue(actualResponse.getJedis().getBlockWhenExhausted());
    }
}
