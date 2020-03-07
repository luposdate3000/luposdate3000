package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


class ThreadSafeMutableList<T>(@JvmField val values: MutableList<T> = mutableListOf<T>()) {
    @JvmField
    val mutex = ReadWriteLock()

    constructor(value: T) : this(mutableListOf(value))

    fun size(): Int {
        var res = 0
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

    fun add(idx: Int, value: T) = mutex.withWriteLock {
        values.add(idx, value)
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

    fun clear() = mutex.withWriteLock {
        values.clear()
    }
}
