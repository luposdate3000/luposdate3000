package lupos.s00misc

class ThreadSafeMutableList<T>() {
    val values = mutableListOf<T>()
    val mutex = ReadWriteLock()
    fun size(): Int {
        var res: Int = 0
        mutex.withReadLock {
            res = values.size
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
            res = values.lastOrNull()
        }
        return res
    }

    fun add(value: T) = mutex.withWriteLock {
        values.add(value)
    }

    fun removeAt(idx: Int) = mutex.withWriteLock {
        values.removeAt(idx)
    }

    fun forEach(action: (T) -> Unit) = mutex.withReadLock {
        values.forEach(action)
    }

    operator fun get(key: Int): T? {
        var res: T? = null
        mutex.withReadLock {
            res = values[key]
        }
        return res
    }
}
