package org.yellowsunn.couponconcurrency.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    companion object {
        const val REDISSON_HOST_PREFIX = "redis://"
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
        // RedisTemplate is the central class in Redis support
        // StringRedisTemplate 으로 대체 가능
        val redisTemplate = RedisTemplate<String, String>()

        redisTemplate.connectionFactory = redisConnectionFactory
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = StringRedisSerializer()

        return redisTemplate
    }

    @Bean
    fun redissonClient(redisProperties: RedisProperties): RedissonClient {
        val config: Config = Config()

        config.useSingleServer().apply {
            address = "$REDISSON_HOST_PREFIX${redisProperties.host}:${redisProperties.port}"
        }

        return Redisson.create(config)
    }
}
