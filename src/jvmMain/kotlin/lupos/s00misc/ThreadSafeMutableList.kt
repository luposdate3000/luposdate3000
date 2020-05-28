package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class ThreadSafeMutableList<T>(@JvmField val values: MutableList<T> = mutableListOf<T>()) {
    @JvmField
    val mutex = ReadWriteLock()
    constructor(value: T) : this(mutableListOf(value))
    fun size(): Int {
Coverage.funStart(16275)
        var res = 0
Coverage.statementStart(16276)
        mutex.withReadLock {
Coverage.statementStart(16277)
            res = values.size
Coverage.statementStart(16278)
        }
Coverage.statementStart(16279)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(16280)
        var res = false
Coverage.statementStart(16281)
        mutex.withReadLock {
Coverage.statementStart(16282)
            res = size() == 0
Coverage.statementStart(16283)
        }
Coverage.statementStart(16284)
        return res
    }
    fun lastOrNull(): T? {
Coverage.funStart(16285)
        var res: T? = null
Coverage.statementStart(16286)
        mutex.withReadLock {
Coverage.statementStart(16287)
            res = values.lastOrNull()
Coverage.statementStart(16288)
        }
Coverage.statementStart(16289)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(16290)
        values.add(value)
Coverage.statementStart(16291)
    }
    fun add(idx: Int, value: T) = mutex.withWriteLock {
Coverage.funStart(16292)
        values.add(idx, value)
Coverage.statementStart(16293)
    }
    fun removeAt(idx: Int) = mutex.withWriteLock {
Coverage.funStart(16294)
        values.removeAt(idx)
Coverage.statementStart(16295)
    }
    fun forEach(action: (T) -> Unit) = mutex.withReadLock {
Coverage.funStart(16296)
        values.forEach(action)
Coverage.statementStart(16297)
    }
    operator fun get(key: Int): T? {
Coverage.funStart(16298)
        var res: T? = null
Coverage.statementStart(16299)
        mutex.withReadLock {
Coverage.statementStart(16300)
            res = values[key]
Coverage.statementStart(16301)
        }
Coverage.statementStart(16302)
        return res
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(16303)
        values.clear()
Coverage.statementStart(16304)
    }
}
