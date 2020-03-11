package lupos.s00misc

import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class ThreadSafeMutableList<T>(values: MutableList<T> = mutableListOf<T>()) {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(values.freeze())

    constructor(value: T) : this(mutableListOf(value))

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

    fun add(idx: Int, value: T) = mutex.withWriteLock {
        val values = global_values.value.toMutableList()
        values.add(idx, value)
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
        val values = global_values.value.toMutableList()
        values.clear()
        global_values.value = values.freeze()
    }
}
