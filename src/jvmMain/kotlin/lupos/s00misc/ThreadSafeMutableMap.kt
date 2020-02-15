package lupos.s00misc


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
