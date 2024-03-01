package com.fish.keXieOpenJudge.config

import com.fish.keXieOpenJudge.utils.JsonUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(factory: LettuceConnectionFactory): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        val jsonRedisSerializer = Jackson2JsonRedisSerializer(JsonUtil.mapper, Any::class.java)
        val stringRedisSerializer = StringRedisSerializer()
        template.connectionFactory = factory
        template.keySerializer = stringRedisSerializer
        template.valueSerializer = jsonRedisSerializer
        template.hashKeySerializer = stringRedisSerializer
        template.hashValueSerializer = jsonRedisSerializer
        template.afterPropertiesSet()
        return template
    }
}
