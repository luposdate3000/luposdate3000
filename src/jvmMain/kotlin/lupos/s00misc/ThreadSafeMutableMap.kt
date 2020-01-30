package lupos.s00misc

import lupos.s00misc.classNameToString


class ThreadSafeMutableMap<k, v>() {
    val map = mutableMapOf<k, v>()
    fun clear() {
        synchronized(map) {
            map.clear()
        }
    }

    operator fun get(key: k): v? {
        synchronized(map) {
            return map[key]
        }
    }

    fun put(key: k, value: v) {
        set(key, value)
    }

    operator fun set(key: k, value: v) {
        synchronized(map) {
            map[key] = value
        }
    }

    fun forEach(action: (k, v) -> Unit) {
        synchronized(map) {
            map.forEach(action)
        }
    }
}
