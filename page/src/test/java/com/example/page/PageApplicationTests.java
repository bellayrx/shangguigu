package com.example.page;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class PageApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForList().set("dajb"+i,i,"1"+i);
        }
    }

}
