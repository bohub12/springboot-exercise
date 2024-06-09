package org.yellowsunn.couponconcurrency.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Coupon(
    @Id
    val id: Long,
    val couponName: String,
    val total: Long,
) : BaseTimeEntity()
