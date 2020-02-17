package lupos.s00misc


class ThreadSafeMutableMap<k, v>() {
    val values = mutableMapOf<k, v>()
    val mutex = ReadWriteLock()

    fun clear() = mutex.withWriteLock {
        values.clear()
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

    fun forEach(action: (k, v) -> Unit) = mutex.withReadLock {
        values.forEach(action)
    }

    fun forEachKey(action: (k) -> Unit) = mutex.withReadLock {
        values.keys.forEach(action)
    }

    fun forEachValue(action: (v) -> Unit) = mutex.withReadLock {
        values.values.forEach(action)
    }
}
