package lupos.s00misc

import kotlin.jvm.JvmField
import kotlin.native.concurrent.AtomicLong
import lupos.s04logicalOperators.Query

class ThreadSafeUuid {
    constructor() {
    }

    private var max_id = AtomicLong(0L)
    fun next(): Long {
        return max_id.addAndGet(1)
    }
}
