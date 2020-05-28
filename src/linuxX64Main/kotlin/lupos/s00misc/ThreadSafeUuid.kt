package lupos.s00misc
import kotlin.native.concurrent.AtomicLong
import lupos.s00misc.Coverage
class ThreadSafeUuid {
    constructor() {
    }
    private var max_id = AtomicLong(0L)
    fun next(): Long {
Coverage.funStart(17348)
        return max_id.addAndGet(1)
    }
}
