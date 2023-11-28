package com.fish.utils;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${jwt.expireTime}")
    private Long expireTime;
    /**
     * <p>默认存放key-value类型值的方法，不指定方法默认为七天时间，和jwt过期时间一致</p>
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }
    /**
     * <p>默认存放key-value类型值的方法，可以自定义过期时间</p>
     * @param key 键
     * @param value 值
     * @param time 过期时间,单位为秒
     */
    public void set(String key, Object value, Long time) {
        if (time > 0)
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        else
            set(key, value);
    }
    /**
     * <p>获取key-value类型的数据</p>
     * @param key 键
     * @return 存放在redis中的数据
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * @param key 键
     * @return 过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
    /**
     * @param key 键
     * @param time 值
     * @return 是否成功
     */
    public Boolean expire(String key, Long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        else
            return false;
    }
    /**
     * 判断 key 是否存在
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}