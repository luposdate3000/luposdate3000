package lupos.s00misc
  inline  fun <T> MyReadWriteLock.withReadLock(crossinline action:  () -> T): T {
        readLock()
        try {
            return action()
        } finally {
            readUnlock()
        }
    }


    inline fun <T> MyReadWriteLock.withWriteLock(crossinline action:  () -> T): T {
writeLock()
try {
                return action()
} finally {
            writeUnlock()
        }
    }
