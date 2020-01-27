package lupos.misc

import lupos.s00misc.XMLElement
import lupos.s00misc.ByteHelper

expect class ThreadSafeMutableMap<k, v>() {
    operator fun get(key: k): v?
    fun put(key: k, value: v)
}
