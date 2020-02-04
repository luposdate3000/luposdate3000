package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlinx.cinterop.cValue
import platform.posix.pthread_mutex_init
import platform.posix.pthread_mutex_lock
import platform.posix.pthread_mutex_t
import platform.posix.pthread_mutex_unlock



actual class ThreadSafeMutableList<T> {
    val mutex = cValue<pthread_mutex_t>();
    val global_list = AtomicReference(mutableListOf<T>().freeze())

    actual constructor() {
        pthread_mutex_init(mutex, null)
    }

    actual fun size(): Int {
        return global_list.value.size
    }

    actual fun isEmpty(): Boolean {
        return size() == 0
    }

    actual fun lastOrNull(): T? {
        pthread_mutex_lock(mutex)
        val res = global_list.value.lastOrNull()
        pthread_mutex_unlock(mutex)
        return res
    }

    actual fun add(value: T) {
        pthread_mutex_lock(mutex)
        val list = global_list.value.toMutableList()
        list.add(value)
        global_list.value = list.freeze()
        pthread_mutex_unlock(mutex)
    }

    actual fun removeAt(idx: Int) {
        pthread_mutex_lock(mutex)
        val list = global_list.value.toMutableList()
        list.removeAt(idx)
        global_list.value = list.freeze()
        pthread_mutex_unlock(mutex)
    }

    actual fun forEach(action: (T) -> Unit) {
        pthread_mutex_lock(mutex)
        val list = global_list.value
        list.forEach(action)
        pthread_mutex_unlock(mutex)
    }
}
//pthread_mutex_destroy
