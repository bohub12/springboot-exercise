package org.yellowsunn.couponconcurrency

import jakarta.persistence.EntityListeners
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@SpringBootApplication
@EntityListeners(AuditingEntityListener::class)
class CouponConcurrencyApplication

fun main(args: Array<String>) {
    runApplication<CouponConcurrencyApplication>(*args)
}
