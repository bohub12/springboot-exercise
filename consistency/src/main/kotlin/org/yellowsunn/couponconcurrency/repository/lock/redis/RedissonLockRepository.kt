package org.yellowsunn.couponconcurrency.repository.lock.redis

import org.apache.logging.log4j.util.Supplier
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Repository
import org.yellowsunn.couponconcurrency.exception.BadRequestException
import org.yellowsunn.couponconcurrency.repository.lock.LockRepository
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Redisson 을 활용한 분산락 구현
 */
@Repository
class RedissonLockRepository(
    private val redissonClient: RedissonClient,
) : LockRepository {
    override fun <T> executeWithLock(
        lockName: String,
        timeout: Duration,
        supplier: Supplier<T>,
    ): T {
        val lock = redissonClient.getLock(lockName)

        // topic 구독한 listener 생성 후 별도 스레드에서 블록
        // RedissonLock.tryLockInnerAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) 참고
        if (!lock.tryLock(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
            throw BadRequestException("락을 획득하지 못했습니다")
        }

        try {
            return supplier.get()
        } finally {
            // 해제 완료됐다는 메시지 publish
            // RedissonLock.unlockInnerAsync(threadId, requestId, timeout)
            lock.unlock()
        }
    }
}
