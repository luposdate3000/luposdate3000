package lupos.s00misc

import kotlin.jvm.JvmField
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class ThreadSafeMutableSet<T>() {
    val global_values = AtomicReference(mutableSetOf<T>().freeze())
    val mutex = ReadWriteLock()
    inline fun forEach(crossinline action: (T) -> Unit) = mutex.withReadLock {
        global_values.value.forEach(action)
    }

    inline suspend fun forEachSuspend(crossinline action: suspend (T) -> Unit) = mutex.withReadLockSuspend {
        for (v in global_values.value)
            action(v)
    }

    fun size(): Int {
        var res = 0
        mutex.withReadLock {
            res = global_values.value.size
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
        val values = global_values.value.toMutableSet()
        values.add(value)
        global_values.value = values.freeze()
    }

    fun remove(value: T) = mutex.withWriteLock {
        val values = global_values.value.toMutableSet()
        values.remove(value)
        global_values.value = values.freeze()
    }

    fun clear() = mutex.withWriteLock {
        val values = global_values.value.toMutableSet()
        values.clear()
        global_values.value = values.freeze()
    }
}
