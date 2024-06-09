package org.yellowsunn.couponconcurrency.service.v4

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.yellowsunn.couponconcurrency.domain.UserCoupon
import org.yellowsunn.couponconcurrency.exception.BadRequestException
import org.yellowsunn.couponconcurrency.repository.persistence.CouponRepository
import org.yellowsunn.couponconcurrency.utils.setIfAbsent

@Service
class CouponServiceV4(
    private val couponRepository: CouponRepository,
    private val redisTemplate: RedisTemplate<String, String>,
) {
    fun getRemainCouponCounts(couponId: Long): Long {
        return couponRepository.countRemainCoupons(couponId)
    }

    @Transactional
    fun giveCoupon(
        couponId: Long,
        userId: String,
    ) {
        // Redis - SET key value NX
        redisTemplate.opsForValue().setIfAbsent("coupon:$couponId") {
            couponRepository.countRemainCoupons(couponId).toString()
        }

        // 한 유저에 대해서만 필요한 락
        val isAlreadyExist = couponRepository.existsByCouponIdAndUserId(couponId = couponId, userId = userId)
        if (isAlreadyExist) {
            throw BadRequestException("이미 쿠폰이 지급된 유저입니다.")
        }

        // lock - 모든 유저에 대허 (너무 범위 1요청)
        // 동시성 이슈를 해결하는 방법: lock, 원자성을 가진 명령으로 처리하면 된다.
        val remainCouponCount = redisTemplate.opsForValue().decrement("coupon:$couponId") ?: -1

        if (remainCouponCount < 0L) {
            throw BadRequestException("쿠폰이 전부 소진되어 지급이 불가능합니다.")
        }

        val newUserCoupon = UserCoupon(couponId = couponId, userId = userId)
        couponRepository.saveUserCoupon(newUserCoupon) // 터지면? save incremnte
    }
}
