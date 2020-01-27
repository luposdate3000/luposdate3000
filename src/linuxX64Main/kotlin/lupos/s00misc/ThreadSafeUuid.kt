package lupos.s00misc
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.Stacktrace
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.Stacktrace
import lupos.s00misc.classNameToString
import lupos.s00misc.Stacktrace

import lupos.s00misc.XMLElement
import lupos.s00misc.ByteHelper

import kotlin.native.concurrent.AtomicLong

actual class ThreadSafeUuid {
    actual constructor() {
    }

    private var max_id = AtomicLong(0L);
    actual fun next(): Long {
        return max_id.addAndGet(1)
    }
}
