package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableSet<T>() {
    val global_values = AtomicReference(mutableSetOf<T>().freeze())
    val mutex = ReadWriteLock()
    inline fun forEach(crossinline action: (T) -> Unit) = mutex.withReadLock {
Coverage.funStart(17320)
        global_values.value.forEach(action)
Coverage.statementStart(17321)
    }
    inline suspend fun forEachSuspend(crossinline action: suspend (T) -> Unit) = mutex.withReadLockSuspend {
Coverage.funStart(17322)
        for (v in global_values.value) {
Coverage.forLoopStart(17323)
            action(v)
Coverage.statementStart(17324)
        }
Coverage.statementStart(17325)
    }
    fun size(): Int {
Coverage.funStart(17326)
        var res = 0
Coverage.statementStart(17327)
        mutex.withReadLock {
Coverage.statementStart(17328)
            res = global_values.value.size
Coverage.statementStart(17329)
        }
Coverage.statementStart(17330)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(17331)
        var res = true
Coverage.statementStart(17332)
        mutex.withReadLock {
Coverage.statementStart(17333)
            res = size() == 0
Coverage.statementStart(17334)
        }
Coverage.statementStart(17335)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(17336)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17337)
        values.add(value)
Coverage.statementStart(17338)
        global_values.value = values.freeze()
Coverage.statementStart(17339)
    }
    fun remove(value: T) = mutex.withWriteLock {
Coverage.funStart(17340)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17341)
        values.remove(value)
Coverage.statementStart(17342)
        global_values.value = values.freeze()
Coverage.statementStart(17343)
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17344)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17345)
        values.clear()
Coverage.statementStart(17346)
        global_values.value = values.freeze()
Coverage.statementStart(17347)
    }
}
