package lupos.s00misc
import lupos.s00misc.EOperatorID

import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock


class ThreadSafeMutableList<T> {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(mutableListOf<T>().freeze())

    fun size(): Int {
        var res: Int = 0
        mutex.withReadLock {
            res = global_values.value.size
        }
        return res
    }

    fun isEmpty(): Boolean {
        var res = false
        mutex.withReadLock {
            res = size() == 0
        }
        return res
    }

    fun lastOrNull(): T? {
        var res: T? = null
        mutex.withReadLock {
            res = global_values.value.lastOrNull()
        }
        return res
    }

    fun add(value: T) = mutex.withWriteLock {
        val values = global_values.value.toMutableList()
        values.add(value)
        global_values.value = values.freeze()
    }

    fun removeAt(idx: Int) = mutex.withWriteLock {
        val values = global_values.value.toMutableList()
        values.removeAt(idx)
        global_values.value = values.freeze()
    }

    fun forEach(action: (T) -> Unit) {
        mutex.withReadLock {
            global_values.value.forEach(action)
        }
    }

    operator fun get(key: Int): T? {
        var value: T? = null
        mutex.withReadLock {
            value = global_values.value[key]
        }
        return value
    }

    fun clear() = mutex.withWriteLock {
        val values = global_values.value.toMutableSet()
        values.clear()
        global_values.value = values.freeze()
    }
}
