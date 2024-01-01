package com.fish.keXieOpenJudge.utils

import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisUtil {
    @Resource
    private val redisTemplate: RedisTemplate<String, Any>? = null

    @Value("\${jwt.expireTime}")
    private val expireTime: Long? = null

    /**
     *
     * 默认存放key-value类型值的方法，不指定方法默认为七天时间，和jwt过期时间一致
     * @param key 键
     * @param value 值
     */
    fun set(key: String, value: Any) {
        redisTemplate!!.opsForValue().set(key, value, expireTime!!, TimeUnit.SECONDS)
    }

    /**
     *
     * 默认存放key-value类型值的方法，可以自定义过期时间
     * @param key 键
     * @param value 值
     * @param time 过期时间,单位为秒
     */
    fun set(key: String, value: Any, time: Long) {
        if (time > 0)
            redisTemplate!!.opsForValue()[key, value, time] = TimeUnit.SECONDS
        else set(key, value)
    }

    /**
     *
     * 获取key-value类型的数据
     * @param key 键
     * @return 存放在redis中的数据
     */
    fun get(key: String): Any {
        return redisTemplate!!.opsForValue().get(key)!!
    }

    /**
     * @param key 键
     * @return 过期时间
     */
    fun getExpire(key: String): Long {
        return redisTemplate!!.getExpire(key)
    }

    /**
     * @param key 键
     * @param time 值
     * @return 是否成功
     */
    fun expire(key: String, time: Long): Boolean {
        return if (time > 0) {
            redisTemplate!!.expire(key, time, TimeUnit.SECONDS)
            true
        } else false
    }
    /**
     * 判断 key 是否存在
     * @param key 键
     * @return 如果存在 key 则返回 true，否则返回 false
     */
    fun exists(key: String): Boolean {
        return redisTemplate!!.hasKey(key)
    }
}