package org.yellowsunn.couponconcurrency.controller.ui

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.yellowsunn.couponconcurrency.service.v1.CouponServiceV1

@Controller
class WebController(
    private val couponServiceV1: CouponServiceV1,
) {
    companion object {
        const val TEST_COUPON_ID = 1L
    }

    @GetMapping
    fun index(
        model: Model,
        @CookieValue(value = "userId", required = false) userId: String?,
    ): String {
        if (userId != null) {
            model.addAttribute("userId", userId)
        }

        val remainCouponCount = couponServiceV1.getRemainCouponCounts(couponId = TEST_COUPON_ID)
        model.addAttribute("remainCouponCount", remainCouponCount)
        model.addAttribute("couponId", TEST_COUPON_ID)
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}
