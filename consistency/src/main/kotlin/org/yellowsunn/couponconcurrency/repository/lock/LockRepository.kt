package org.yellowsunn.couponconcurrency.repository.lock

import org.apache.logging.log4j.util.Supplier
import java.time.Duration

interface LockRepository {
    fun <T> executeWithLock(
        lockName: String,
        timeout: Duration,
        supplier: Supplier<T>,
    ): T
}
