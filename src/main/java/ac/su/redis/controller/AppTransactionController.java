package ac.su.redis.controller;

import ac.su.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class AppTransactionController {
    private final RedisService redisService;

    @GetMapping("/transaction-key")
    public String getTransactionKey(

    ) {
        return UUID.randomUUID().toString();
    }

    // 키 검증
    @GetMapping("/transaction-result-test")
    public ResponseEntity<String> transactionResultTest(
            @RequestParam String key
    ) {
        boolean isTransactionSuccess = redisService.setIfAbsent(
                key, LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(
                isTransactionSuccess ? "Transaction success" : "Transaction fail",
                isTransactionSuccess ? HttpStatus.OK : HttpStatus.CONFLICT
        );
    }
}
