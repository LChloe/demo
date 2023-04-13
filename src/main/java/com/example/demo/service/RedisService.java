package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public void addSet(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set<T> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void deleteSet(String key, T value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    public T execute(RedisCallback<T> redisCallback) {
        return redisTemplate.execute(redisCallback);
    }

}
