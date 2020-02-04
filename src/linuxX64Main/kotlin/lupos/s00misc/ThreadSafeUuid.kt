package lupos.s00misc
import kotlin.native.concurrent.AtomicLong



actual class ThreadSafeUuid {
    actual constructor() {
    }

    private var max_id = AtomicLong(0L);
    actual fun next(): Long {
        return max_id.addAndGet(1)
    }
}
