package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query


class ThreadSafeMutableMap<k, v> {
    @JvmField
    val values = mutableMapOf<k, v>()
    @JvmField
    val mutex = ReadWriteLock()

    fun clear() = mutex.withWriteLock {
        values.clear()
    }

    fun keySize(): Int {
        var res = 0
        mutex.withReadLock {
            res = values.keys.size
        }
        return res
    }

    operator fun get(key: k): v? {
        var res: v? = null
        mutex.withReadLock {
            res = values[key]
        }
        return res
    }

    operator fun set(key: k, value: v) = mutex.withWriteLock {
        values[key] = value
    }

    fun remove(key: k) = mutex.withWriteLock {
        values.remove(key)
    }

    inline fun forEach(crossinline action: (k, v) -> Unit) = mutex.withReadLock {
        values.forEach { a, b ->
            action(a, b)
        }
    }

    inline fun forEachKey(crossinline action: (k) -> Unit) = mutex.withReadLock {
        values.keys.forEach {
            action(it)
        }
    }

    suspend inline fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
        values.keys.forEach {
            action(it)
        }
    }

    inline fun forEachValue(crossinline action: (v) -> Unit) = mutex.withReadLock {
        values.values.forEach {
            action(it)
        }
    }
}
