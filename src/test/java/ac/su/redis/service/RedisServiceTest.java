package ac.su.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisServiceTest {
    @Autowired
    RedisService redisService;

    @Test
    void test() {
        assert redisService.setIfAbsent(
                // 일정 시간 내에 중복 키가 redis에 입력될 수 없도록 보장
                "haha", "this is test"
        );
    }
}