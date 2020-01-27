package lupos.s00misc
import lupos.s00misc.classNameToString

class ThreadSafeMutableMap<k, v>() {
    val map = mutableMapOf<k, v>()
    operator fun get(key: k): v? {
        synchronized(map) {
            return map[key]
        }
    }

    fun put(key: k, value: v) {
        synchronized(map) {
            map[key] = value
        }
    }
}
