package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class ThreadSafeMutableMap<k, v> {
    @JvmField
    val values = mutableMapOf<k, v>()
    @JvmField
    val mutex = ReadWriteLock()
    fun clear() = mutex.withWriteLock {
Coverage.funStart(16305)
        values.clear()
Coverage.statementStart(16306)
    }
    fun keySize(): Int {
Coverage.funStart(16307)
        var res = 0
Coverage.statementStart(16308)
        mutex.withReadLock {
Coverage.statementStart(16309)
            res = values.keys.size
Coverage.statementStart(16310)
        }
Coverage.statementStart(16311)
        return res
    }
    operator fun get(key: k): v? {
Coverage.funStart(16312)
        var res: v? = null
Coverage.statementStart(16313)
        mutex.withReadLock {
Coverage.statementStart(16314)
            res = values[key]
Coverage.statementStart(16315)
        }
Coverage.statementStart(16316)
        return res
    }
    operator fun set(key: k, value: v) = mutex.withWriteLock {
Coverage.funStart(16317)
        values[key] = value
Coverage.statementStart(16318)
    }
    fun remove(key: k) = mutex.withWriteLock {
Coverage.funStart(16319)
        values.remove(key)
Coverage.statementStart(16320)
    }
    inline fun forEach(crossinline action: (k, v) -> Unit) = mutex.withReadLock {
Coverage.funStart(16321)
        values.forEach { a, b ->
Coverage.statementStart(16322)
            action(a, b)
Coverage.statementStart(16323)
        }
Coverage.statementStart(16324)
    }
    inline fun forEachKey(crossinline action: (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(16325)
        values.keys.forEach {
Coverage.forEachLoopStart(16326)
            action(it)
Coverage.statementStart(16327)
        }
Coverage.statementStart(16328)
    }
    suspend inline fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(16329)
        values.keys.forEach {
Coverage.forEachLoopStart(16330)
            action(it)
Coverage.statementStart(16331)
        }
Coverage.statementStart(16332)
    }
    inline fun forEachValue(crossinline action: (v) -> Unit) = mutex.withReadLock {
Coverage.funStart(16333)
        values.values.forEach {
Coverage.forEachLoopStart(16334)
            action(it)
Coverage.statementStart(16335)
        }
Coverage.statementStart(16336)
    }
}
