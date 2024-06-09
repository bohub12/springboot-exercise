package org.yellowsunn.couponconcurrency.service.v4

import org.springframework.stereotype.Component
import org.yellowsunn.couponconcurrency.repository.lock.LockRepository
import java.time.Duration

/**
 * Redis Spin Lock
 */
@Component
class CouponFacadeV4(
    private val couponServiceV4: CouponServiceV4,
    private val redissonLockRepository: LockRepository,
) {
    fun giveCoupon(
        couponId: Long,
        userId: String,
    ) {
        redissonLockRepository.executeWithLock("test:$userId:$couponId", Duration.ofSeconds(1)) {
            couponServiceV4.giveCoupon(couponId, userId)
        }
    }
}
