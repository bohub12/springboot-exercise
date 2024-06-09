package org.yellowsunn.couponconcurrency.repository.lock.redis

import org.apache.logging.log4j.util.Supplier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import org.yellowsunn.couponconcurrency.repository.lock.LockRepository
import java.time.Duration

@Component
class RedisSpinLockRepository(
    private val redisTemplate: RedisTemplate<String, String>,
) : LockRepository {
    override fun <T> executeWithLock(
        lockName: String,
        timeout: Duration,
        supplier: Supplier<T>,
    ): T {
        while (true) {
            // Redis - SET key value NX
            val isAcquired: Boolean = redisTemplate.opsForValue().setIfAbsent(lockName, "1", timeout) ?: false

            if (isAcquired) {
                return try {
                    supplier.get()
                } finally {
                    redisTemplate.delete(lockName)
                }
            }
        }
    }
}
