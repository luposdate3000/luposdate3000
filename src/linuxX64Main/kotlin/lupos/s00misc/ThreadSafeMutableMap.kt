package lupos.s00misc

import kotlinx.cinterop.cValue
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_unlock
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

actual class ThreadSafeMutableMap<k, v> {
    val mutex = cValue<pthread_mutex_t>();
    val global_map = AtomicReference(mutableMapOf<k, v>().freeze())
    actual fun clear() {
        pthread_mutex_lock(mutex)
        val map = global_map.value.toMutableMap()
        map.clear()
        global_map.value = map.freeze()
        pthread_mutex_unlock(mutex)
    }

    actual operator fun set(key: k, value: v) {
        pthread_mutex_lock(mutex)
        val map = global_map.value.toMutableMap()
        map.put(key, value)
        global_map.value = map.freeze()
        pthread_mutex_unlock(mutex)
    }

    actual fun forEach(action: (k, v) -> Unit) {
        pthread_mutex_lock(mutex)
        global_map.value.forEach { it ->
            //here is some type conversion - inconsistent between native and jvm ...
            action(it.key, it.value)
        }
        pthread_mutex_unlock(mutex)
    }

    actual constructor() {
        pthread_mutex_init(mutex, null)
    }

    actual operator fun get(key: k): v? {
        pthread_mutex_lock(mutex)
        val value = global_map.value[key]
        pthread_mutex_unlock(mutex)
        return value
    }

    actual fun put(key: k, value: v) {
        set(key, value)
    }
}
//pthread_mutex_destroy
