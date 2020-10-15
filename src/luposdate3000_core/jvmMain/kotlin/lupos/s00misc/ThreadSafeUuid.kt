package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage

class ThreadSafeUuid {
    companion object {
        @JvmField
        var global_uuid = 0L
    }

    fun next(): Long {
        synchronized(global_uuid) {
            return global_uuid++
        }
/*Coverage Unreachable*/
    }
}
