package lupos.s00misc


class ThreadSafeMutableMap<k, v>() {
    val map = mutableMapOf<k, v>()
    val mutex = ReadWriteLock()
    fun clear() = mutex.withWriteLock {
        map.clear()
    }

    operator fun get(key: k): v? {
        var res: v? = null
        mutex.withReadLock {
            res = map[key]
        }
        return res
    }

    operator fun set(key: k, value: v) = mutex.withWriteLock {
        map[key] = value
    }

    fun forEach(action: (k, v) -> Unit) = mutex.withReadLock {
        map.forEach(action)
    }
}
