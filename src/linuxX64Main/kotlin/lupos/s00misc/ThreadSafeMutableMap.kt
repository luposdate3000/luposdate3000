package lupos.s00misc

import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock


class ThreadSafeMutableMap<k, v>() {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(mutableMapOf<k, v>().freeze())
    fun clear() = mutex.withWriteLock {
        val values = global_values.value
        values.clear()
        global_values.value = values.freeze()
    }

    operator fun set(key: k, value: v) = mutex.withWriteLock {
        val values = global_values.value
        values.put(key, value)
        global_values.value = values.freeze()
    }

    fun forEach(action: (k, v) -> Unit) = mutex.withReadLock {
        global_values.value.forEach { it ->
            action(it.key, it.value)
        }
    }

    operator fun get(key: k): v? {
        var value: v? = null
        mutex.withReadLock {
            value = global_values.value[key]
        }
        return value
    }

}
