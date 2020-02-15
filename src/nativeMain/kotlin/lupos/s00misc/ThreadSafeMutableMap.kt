package lupos.s00misc

expect class ThreadSafeMutableMap<k, v>() {
    fun clear()
    operator fun set(key: k, value: v)
    fun forEach(action: (k, v) -> Unit)
    operator fun get(key: k): v?
}
