package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query

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
