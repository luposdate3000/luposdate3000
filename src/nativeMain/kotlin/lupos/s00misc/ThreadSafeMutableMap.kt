package lupos.s00misc

import lupos.s00misc.*

expect class ThreadSafeMutableMap<k, v>() {
    operator fun get(key: k): v?
    fun put(key: k, value: v)
}
