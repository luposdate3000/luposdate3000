package lupos.s00misc
import lupos.s00misc.EOperatorID

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

    fun keySize(): Int {
        var res = 0
        mutex.withReadLock {
            res = global_values.value.keys.size
        }
        return res
    }

    operator fun set(key: k, value: v) = mutex.withWriteLock {
        val values = global_values.value.toMutableMap()
        values[key] = value
        global_values.value = values.freeze()
    }

    fun remove(key: k) = mutex.withWriteLock {
        val values = global_values.value.toMutableMap()
        values.remove(key)
        global_values.value = values.freeze()
    }

    inline fun forEach(crossinline action: (k, v) -> Unit) = mutex.withReadLock {
        global_values.value.forEach { it ->
            action(it.key, it.value)
        }
    }

    inline fun forEachKey(crossinline action: (k) -> Unit) = mutex.withReadLock {
        global_values.value.keys.forEach {
            action(it)
        }
    }

    inline suspend fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
        global_values.value.keys.forEach {
            action(it)
        }
    }

    inline fun forEachValue(crossinline action: (v) -> Unit) = mutex.withReadLock {
        global_values.value.values.forEach {
            action(it)
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
