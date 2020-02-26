package lupos.s00misc

import kotlinx.coroutines.CoroutineScope
import lupos.s00misc.EOperatorID


class ThreadSafeMutableSet<T>() {
    val values = mutableSetOf<T>()
    val mutex = ReadWriteLock()

    inline fun forEach(crossinline action: (T) -> Unit) = mutex.withReadLock {
        values.forEach(action)
    }

    inline suspend fun forEachSuspend(crossinline action: suspend (T) -> Unit) = mutex.withReadLockSuspend {
        for (v in values)
            action(v)
    }

    fun size(): Int {
        var res = 0
        mutex.withReadLock {
            res = values.size
        }
        return res
    }

    fun isEmpty(): Boolean {
        var res = true
        mutex.withReadLock {
            res = size() == 0
        }
        return res
    }

    fun add(value: T) = mutex.withWriteLock {
        values.add(value)
    }

    fun remove(value: T) = mutex.withWriteLock {
        values.remove(value)
    }

    fun clear() = mutex.withWriteLock {
        values.clear()
    }
}
