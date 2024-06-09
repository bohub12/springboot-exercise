package org.yellowsunn.couponconcurrency.repository.persistence

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.yellowsunn.couponconcurrency.domain.UserCoupon
import org.yellowsunn.couponconcurrency.repository.persistence.jpa.CouponJpaRepository
import org.yellowsunn.couponconcurrency.repository.persistence.jpa.UserCouponJpaRepository

@Repository
class CouponRepositoryImpl(
    private val couponJpaRepository: CouponJpaRepository,
    private val userCouponJpaRepository: UserCouponJpaRepository,
) : CouponRepository {
    @Transactional(readOnly = true)
    override fun countRemainCoupons(couponId: Long): Long {
        val totalCoupon =
            couponJpaRepository.findById(couponId)
                .map { it.total }
                .orElse(0L)

        val usedCoupon = userCouponJpaRepository.countByCouponId(couponId)

        return totalCoupon - usedCoupon
    }

    @Transactional(readOnly = true)
    override fun existsByCouponIdAndUserId(
        couponId: Long,
        userId: String,
    ): Boolean {
        return userCouponJpaRepository.existsByCouponIdAndUserId(userId = userId, couponId = couponId)
    }

    @Transactional
    override fun saveUserCoupon(userCoupon: UserCoupon): UserCoupon {
        return userCouponJpaRepository.save(userCoupon)
    }

    @Transactional
    override fun deleteUserCoupons(couponId: Long) {
        userCouponJpaRepository.deleteByCouponId(couponId)
    }
}
