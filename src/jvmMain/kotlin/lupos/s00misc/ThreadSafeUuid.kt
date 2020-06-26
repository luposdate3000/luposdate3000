package lupos.s00misc

import lupos.s00misc.Coverage
import kotlin.jvm.JvmField
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
