package lupos.misc

import lupos.s00misc.XMLElement
import lupos.s00misc.ByteHelper

expect class ThreadSafeUuid() {
    fun next(): Long
}
