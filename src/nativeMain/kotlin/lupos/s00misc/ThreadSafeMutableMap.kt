package lupos.s00misc
import lupos.s00misc.Stacktrace
import lupos.s00misc.classNameToString
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.Stacktrace
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.Stacktrace
import lupos.s00misc.classNameToString
import lupos.s00misc.Stacktrace

import lupos.s00misc.XMLElement
import lupos.s00misc.ByteHelper

expect class ThreadSafeMutableMap<k, v>() {
    operator fun get(key: k): v?
    fun put(key: k, value: v)
}
