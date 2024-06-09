package org.yellowsunn.couponconcurrency.controller.rest

import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.yellowsunn.couponconcurrency.service.v1.CouponFacadeV1
import org.yellowsunn.couponconcurrency.service.v2.CouponFacadeV2
import org.yellowsunn.couponconcurrency.service.v3.CouponFacadeV3
import org.yellowsunn.couponconcurrency.service.v4.CouponFacadeV4

@RestController
class CouponController(
    private val couponFacadeV1: CouponFacadeV1,
    private val couponFacadeV2: CouponFacadeV2,
    private val couponFacadeV3: CouponFacadeV3,
    private val couponFacadeV4: CouponFacadeV4,
) {
    @PostMapping("/api/v1/coupons/{couponId}")
    fun giveCoupon(
        @CookieValue(value = "userId", required = true) userId: String,
        @PathVariable couponId: Long,
    ) {
        return couponFacadeV1.giveCoupon(couponId = couponId, userId = userId)
    }

    @PostMapping("/api/v2/coupons/{couponId}")
    fun giveCouponV2(
        @CookieValue(value = "userId", required = true) userId: String,
        @PathVariable couponId: Long,
    ) {
        return couponFacadeV2.giveCoupon(couponId = couponId, userId = userId)
    }

    @PostMapping("/api/v3/coupons/{couponId}")
    fun giveCouponV3(
        @CookieValue(value = "userId", required = true) userId: String,
        @PathVariable couponId: Long,
    ) {
        return couponFacadeV3.giveCoupon(couponId = couponId, userId = userId)
    }

    @PostMapping("/api/v4/coupons/{couponId}")
    fun giveCouponV4(
        @CookieValue(value = "userId", required = true) userId: String,
        @PathVariable couponId: Long,
    ) {
        return couponFacadeV4.giveCoupon(couponId = couponId, userId = userId)
    }

    @DeleteMapping("/api/v2/coupons/{couponId}")
    fun clearCouponsV2(
        @PathVariable couponId: Long,
    ) {
        return couponFacadeV2.clearCoupons(couponId)
    }
}
