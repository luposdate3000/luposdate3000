package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableSet<T>() {
    val global_values = AtomicReference(mutableSetOf<T>().freeze())
    val mutex = ReadWriteLock()
    inline fun forEach(crossinline action: (T) -> Unit) = mutex.withReadLock {
Coverage.funStart(17566)
        global_values.value.forEach(action)
Coverage.statementStart(17567)
    }
    inline suspend fun forEachSuspend(crossinline action: suspend (T) -> Unit) = mutex.withReadLockSuspend {
Coverage.funStart(17568)
        for (v in global_values.value) {
Coverage.forLoopStart(17569)
            action(v)
Coverage.statementStart(17570)
        }
Coverage.statementStart(17571)
    }
    fun size(): Int {
Coverage.funStart(17572)
        var res = 0
Coverage.statementStart(17573)
        mutex.withReadLock {
Coverage.statementStart(17574)
            res = global_values.value.size
Coverage.statementStart(17575)
        }
Coverage.statementStart(17576)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(17577)
        var res = true
Coverage.statementStart(17578)
        mutex.withReadLock {
Coverage.statementStart(17579)
            res = size() == 0
Coverage.statementStart(17580)
        }
Coverage.statementStart(17581)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(17582)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17583)
        values.add(value)
Coverage.statementStart(17584)
        global_values.value = values.freeze()
Coverage.statementStart(17585)
    }
    fun remove(value: T) = mutex.withWriteLock {
Coverage.funStart(17586)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17587)
        values.remove(value)
Coverage.statementStart(17588)
        global_values.value = values.freeze()
Coverage.statementStart(17589)
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17590)
        val values = global_values.value.toMutableSet()
Coverage.statementStart(17591)
        values.clear()
Coverage.statementStart(17592)
        global_values.value = values.freeze()
Coverage.statementStart(17593)
    }
}
