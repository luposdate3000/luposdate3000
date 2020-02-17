package lupos.s00misc

import kotlinx.coroutines.CoroutineScope


class ThreadSafeMutableSet<T>() {
    val values = mutableSetOf<T>()

    inline fun forEach(crossinline action: (T) -> Unit) {
        synchronized(values) {
            values.forEach(action)
        }
    }

    inline suspend fun forEachSuspend(crossinline action: suspend (T) -> Unit) {
        val valuesCopy = mutableSetOf<T>()
        synchronized(values) {
            valuesCopy.addAll(values)
        }
        for (v in valuesCopy)
            action(v)
    }

    fun size(): Int {
        return values.size
    }

    fun isEmpty(): Boolean {
        return size() == 0
    }

    fun add(value: T) {
        synchronized(values) {
            values.add(value)
        }
    }

    fun remove(value: T) {
        synchronized(values) {
            values.remove(value)
        }
    }

    fun clear() {
        synchronized(values) {
            values.clear()
        }
    }
}
