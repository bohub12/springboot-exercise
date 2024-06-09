package org.yellowsunn.couponconcurrency.repository.persistence

import org.yellowsunn.couponconcurrency.domain.UserCoupon

interface CouponRepository {
    fun countRemainCoupons(couponId: Long): Long

    fun existsByCouponIdAndUserId(
        couponId: Long,
        userId: String,
    ): Boolean

    fun saveUserCoupon(userCoupon: UserCoupon): UserCoupon

    fun deleteUserCoupons(couponId: Long)
}
