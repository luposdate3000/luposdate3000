package lupos.s00misc

inline fun <T> MyLock.withLock(crossinline action: () -> T): T {
    lock()
    try {
        return action()
    } finally {
        unlock()
    }
}
