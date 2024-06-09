package org.yellowsunn.couponconcurrency.repository.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.yellowsunn.couponconcurrency.domain.UserCoupon

interface UserCouponJpaRepository : JpaRepository<UserCoupon, Long> {
    fun countByCouponId(couponId: Long): Long

    fun existsByCouponIdAndUserId(
        couponId: Long,
        userId: String,
    ): Boolean

    fun deleteByCouponId(couponId: Long)
}
