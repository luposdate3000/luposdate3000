package lupos.modulename
import lupos.s00misc.SanityCheck
internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }
    val uuid = uuidCounter++
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun getUUID() = uuid
    var locked = false
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun lock() {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun unlock() {
        SanityCheck {
            if (!locked) {
                throw Exception("unlock without previous lock")
            }
            locked = false
        }
    }
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun tryLock(): Boolean {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
        return true
    }
    internal actual inline fun <T> withLock(crossinline action: () -> T): T {
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
