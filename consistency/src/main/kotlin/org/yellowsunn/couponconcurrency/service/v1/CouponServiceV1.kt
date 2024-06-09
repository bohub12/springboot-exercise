package org.yellowsunn.couponconcurrency.service.v1

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.yellowsunn.couponconcurrency.domain.UserCoupon
import org.yellowsunn.couponconcurrency.exception.BadRequestException
import org.yellowsunn.couponconcurrency.repository.persistence.CouponRepository

@Service
class CouponServiceV1(
    private val couponRepository: CouponRepository,
) {
    fun getRemainCouponCounts(couponId: Long): Long {
        return couponRepository.countRemainCoupons(couponId)
    }

    @Transactional
    fun giveCoupon(
        couponId: Long,
        userId: String,
    ) {
        val isAlreadyExist = couponRepository.existsByCouponIdAndUserId(couponId = couponId, userId = userId)
        if (isAlreadyExist) {
            throw BadRequestException("이미 쿠폰이 지급된 유저입니다.")
        }

        val remainCouponCount: Long = couponRepository.countRemainCoupons(couponId)
        if (remainCouponCount <= 0L) {
            throw BadRequestException("쿠폰이 전부 소진되어 지급이 불가능합니다.")
        }

        val newUserCoupon = UserCoupon(couponId = couponId, userId = userId)
        couponRepository.saveUserCoupon(newUserCoupon)
    }
}
