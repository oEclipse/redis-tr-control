package ac.su.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplateDb0;
    private final StringRedisTemplate redisTemplateDb1;
    private final StringRedisTemplate redisTemplateDb2;

    // Transaction 검사용 메서드로 사용
    public boolean setIfAbsent(String key, String value) {
        return Boolean.TRUE.equals(
                redisTemplateDb0.opsForValue().setIfAbsent(
                        key, value, Duration.ofSeconds(10)
                )
        );
    }
}
