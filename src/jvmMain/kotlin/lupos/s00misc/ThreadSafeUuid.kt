package lupos.s00misc

import kotlin.jvm.JvmField


class ThreadSafeUuid {

    companion object {
        var global_uuid = 0L
    }

    fun next(): Long {
        synchronized(global_uuid) {
            return global_uuid++
        }
    }
}
