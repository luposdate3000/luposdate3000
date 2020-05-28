package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class ThreadSafeMutableSet<T> {
    @JvmField
    val values = mutableSetOf<T>()
    @JvmField
    val mutex = ReadWriteLock()
    inline fun forEach(crossinline action: (T) -> Unit) = mutex.withReadLock {
Coverage.funStart(16337)
        values.forEach(action)
Coverage.statementStart(16338)
    }
    suspend inline fun forEachSuspend(crossinline action: suspend (T) -> Unit) = mutex.withReadLockSuspend {
Coverage.funStart(16339)
        for (v in values) {
Coverage.forLoopStart(16340)
            action(v)
Coverage.statementStart(16341)
        }
Coverage.statementStart(16342)
    }
    fun size(): Int {
Coverage.funStart(16343)
        var res = 0
Coverage.statementStart(16344)
        mutex.withReadLock {
Coverage.statementStart(16345)
            res = values.size
Coverage.statementStart(16346)
        }
Coverage.statementStart(16347)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(16348)
        var res = true
Coverage.statementStart(16349)
        mutex.withReadLock {
Coverage.statementStart(16350)
            res = size() == 0
Coverage.statementStart(16351)
        }
Coverage.statementStart(16352)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(16353)
        values.add(value)
Coverage.statementStart(16354)
    }
    fun remove(value: T) = mutex.withWriteLock {
Coverage.funStart(16355)
        values.remove(value)
Coverage.statementStart(16356)
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(16357)
        values.clear()
Coverage.statementStart(16358)
    }
}
