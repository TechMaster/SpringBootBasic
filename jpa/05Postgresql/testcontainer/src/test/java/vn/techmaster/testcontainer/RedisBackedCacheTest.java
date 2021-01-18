package vn.techmaster.testcontainer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import redis.clients.jedis.Jedis;
import vn.techmaster.testcontainer.cache.Cache;
import vn.techmaster.testcontainer.cache.RedisBackedCache;

@Testcontainers
public class RedisBackedCacheTest {

    @Container
    public GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:alpine"))
                                            .withExposedPorts(6379);
    private Cache cache;

    @BeforeEach
    public void setUp() throws Exception {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));

        cache = new RedisBackedCache(jedis, "test");
    }

    @Test
    public void testFindingAnInsertedValue() {
        cache.put("foo", "FOO");
        Optional<String> foundObject = cache.get("foo", String.class);

        assertThat(foundObject).isPresent();
        assertThat(foundObject.get()).isEqualTo("FOO");
    }

    @Test
    public void testNotFindingAValueThatWasNotInserted() {
        Optional<String> foundObject = cache.get("bar", String.class);
        assertThat(foundObject).isNotPresent();
    }
}