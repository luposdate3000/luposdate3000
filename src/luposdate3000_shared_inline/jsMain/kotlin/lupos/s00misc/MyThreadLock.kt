package lupos.s00misc

import lupos.s00misc.NotImplementedException

internal actual class MyThreadLock {
    internal companion object {
        var uuidCounter = 0L
    }

    val uuid = uuidCounter++
    actual inline fun getUUID() = uuid

    var locked = false
    actual inline fun lock() {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
    }

    actual inline fun unlock() {
        SanityCheck {
            if (!locked) {
                throw Exception("unlock without previous lock")
            }
            locked = false
        }
    }

    actual inline fun tryLock(): Boolean {
        SanityCheck {
            if (locked) {
                throw Exception("deadlock")
            }
            locked = true
        }
        return true
    }

    actual inline fun <T> withLock(crossinline action: () -> T): T {
        lock()
        try {
            return action()
        } finally {
            unlock()
        }
    }
}
