package lupos.s00misc

import lupos.s00misc.EOperatorID


class ThreadSafeMutableMap<k, v>() {
    val values = mutableMapOf<k, v>()
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

    inline suspend fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
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
