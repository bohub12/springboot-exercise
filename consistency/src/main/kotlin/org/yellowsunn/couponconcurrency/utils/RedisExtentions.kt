package org.yellowsunn.couponconcurrency.utils

import org.springframework.data.redis.core.ValueOperations
import java.util.function.Supplier

fun <K : Any, V : Any> ValueOperations<K, V>.setIfAbsent(
    key: K,
    value: Supplier<V>,
): Boolean {
    if (this[key] != null) {
        return false
    }
    return this.setIfAbsent(key, value.get()) ?: false
}
