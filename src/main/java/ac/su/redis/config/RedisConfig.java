package ac.su.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(
                        redisHost, redisPort
                )
        );
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory0() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration(
                redisHost, redisPort
        );
        redisConf.setDatabase(0);
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory1() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration(
                redisHost, redisPort
        );
        redisConf.setDatabase(0);
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory2() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration(
                redisHost, redisPort
        );
        redisConf.setDatabase(0);
        return new LettuceConnectionFactory(redisConf);
    }

    // ============== Redis 접속 객체 생성 완료 =================
    // 커맨드 구조를 미리 정의하는 Template 객체를 사용해야 실제 Redis 호출 가능

    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory redisConnectionFactory
    ) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        // [AntiPattern] redisTemplate 메서드 내에서 커넥션을 새롭게 생성
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
        // Bean으로 제작한 객체는 주입받아서 사용하자 -> 인자 부분 없애지 말기!

        // [Good Pattern]
        // 파라미터 주입 방식으로 Bean을 받아서 Singleton 사용법을 준수
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate redisTemplateDb0() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate redisTemplateDb1() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate redisTemplateDb2() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}
