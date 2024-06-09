package org.yellowsunn.couponconcurrency.repository.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.yellowsunn.couponconcurrency.domain.Coupon

interface CouponJpaRepository : JpaRepository<Coupon, Long>
