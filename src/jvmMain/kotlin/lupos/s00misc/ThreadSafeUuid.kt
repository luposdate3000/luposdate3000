package lupos.misc
import lupos.s00misc.*
import lupos.misc.*

class ThreadSafeUuid() {

    companion object {
        var global_uuid = 0L
    }

    fun next(): Long {
        synchronized(global_uuid) {
            return global_uuid++
        }
    }
}
