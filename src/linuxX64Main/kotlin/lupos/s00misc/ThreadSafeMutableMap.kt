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
        val values = global_values.value.toMutableMap()
        values.clear()
        global_values.value = values.freeze()
    }

    operator fun set(key: k, value: v) = mutex.withWriteLock {
        val values = global_values.value.toMutableMap()
        values[key]=value
        global_values.value = values.freeze()
    }

    fun remove( key: k) = mutex.withWriteLock {
        val values = global_values.value.toMutableMap()
        values.remove( key)
        global_values.value = values.freeze()
    }

    fun forEach(action: (k, v) -> Unit) = mutex.withReadLock {
        global_values.value.forEach { it ->
            action(it.key, it.value)
        }
    }

    fun forEachKey(action: (k) -> Unit) = mutex.withReadLock {
        global_values.value.keys.forEach (action)
    }

    fun forEachValue(action: (v) -> Unit) = mutex.withReadLock {
        global_values.value.values.forEach (action)
    }

    operator fun get(key: k): v? {
        var value: v? = null
        mutex.withReadLock {
            value = global_values.value[key]
        }
        return value
    }

}
