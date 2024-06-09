package org.yellowsunn.couponconcurrency.service.v3

import org.springframework.stereotype.Component
import org.yellowsunn.couponconcurrency.repository.lock.LockRepository
import java.time.Duration

/**
 * Redis Spin Lock
 */
@Component
class CouponFacadeV3(
    private val couponServiceV3: CouponServiceV3,
    private val redisSpinLockRepository: LockRepository,
) {
    fun giveCoupon(
        couponId: Long,
        userId: String,
    ) {
        redisSpinLockRepository.executeWithLock("test:$userId:$couponId", Duration.ofSeconds(1)) {
            couponServiceV3.giveCoupon(couponId, userId)
        }
    }
}
