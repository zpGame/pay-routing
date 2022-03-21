package com.hunk.route;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author hunk
 * @date 2022/3/21
 *     <p>
 */
public class RedisTest extends ApplicationTests {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Test
    public void set(){
        redisTemplate.opsForValue().set("aaa","bbb");
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }
}
