package lupos.misc

import lupos.s00misc.XMLElement
import lupos.s00misc.ByteHelper


actual class ThreadSafeMutableMap<k, v> {
    val mutex = cValue<pthread_mutex_t>();
    val global_map = AtomicReference(mutableMapOf<k, v>().freeze())

    actual constructor() {
        pthread_mutex_init(mutex, null)
    }

    actual operator fun get(key: k): v? {
        pthread_mutex_lock(mutex)
        val value = global_map.value[key]
        pthread_mutex_unlock(mutex)
        return value
    }

    actual fun put(key: k, value: v) {
        pthread_mutex_lock(mutex)
        val map = global_map.value.toMutableMap()
        map.put(key, value)
        global_map.value = map.freeze()
        pthread_mutex_unlock(mutex)
    }
}
//pthread_mutex_destroy
