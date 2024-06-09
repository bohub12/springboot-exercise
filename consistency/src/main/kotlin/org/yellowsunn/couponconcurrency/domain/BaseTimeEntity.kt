package org.yellowsunn.couponconcurrency.domain

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

@MappedSuperclass
abstract class BaseTimeEntity {
    @CreatedDate
    var createdAt: ZonedDateTime = ZonedDateTime.now()
        protected set

    @LastModifiedDate
    var modifiedAt: ZonedDateTime = ZonedDateTime.now()
        protected set
}