package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import kotlin.native.concurrent.AtomicLong


class ThreadSafeUuid {
    constructor() {
    }

    private var max_id = AtomicLong(0L)
    fun next(): Long {
        return max_id.addAndGet(1)
    }
}
