package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableList<T>(values: MutableList<T> = mutableListOf<T>()) {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(values.freeze())
    constructor(value: T) : this(mutableListOf(value))
    fun size(): Int {
Coverage.funStart(17242)
        var res: Int = 0
Coverage.statementStart(17243)
        mutex.withReadLock {
Coverage.statementStart(17244)
            res = global_values.value.size
Coverage.statementStart(17245)
        }
Coverage.statementStart(17246)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(17247)
        var res = false
Coverage.statementStart(17248)
        mutex.withReadLock {
Coverage.statementStart(17249)
            res = size() == 0
Coverage.statementStart(17250)
        }
Coverage.statementStart(17251)
        return res
    }
    fun lastOrNull(): T? {
Coverage.funStart(17252)
        var res: T? = null
Coverage.statementStart(17253)
        mutex.withReadLock {
Coverage.statementStart(17254)
            res = global_values.value.lastOrNull()
Coverage.statementStart(17255)
        }
Coverage.statementStart(17256)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(17257)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17258)
        values.add(value)
Coverage.statementStart(17259)
        global_values.value = values.freeze()
Coverage.statementStart(17260)
    }
    fun add(idx: Int, value: T) = mutex.withWriteLock {
Coverage.funStart(17261)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17262)
        values.add(idx, value)
Coverage.statementStart(17263)
        global_values.value = values.freeze()
Coverage.statementStart(17264)
    }
    fun removeAt(idx: Int) = mutex.withWriteLock {
Coverage.funStart(17265)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17266)
        values.removeAt(idx)
Coverage.statementStart(17267)
        global_values.value = values.freeze()
Coverage.statementStart(17268)
    }
    fun forEach(action: (T) -> Unit) {
Coverage.funStart(17269)
        mutex.withReadLock {
Coverage.statementStart(17270)
            global_values.value.forEach(action)
Coverage.statementStart(17271)
        }
Coverage.statementStart(17272)
    }
    operator fun get(key: Int): T? {
Coverage.funStart(17273)
        var value: T? = null
Coverage.statementStart(17274)
        mutex.withReadLock {
Coverage.statementStart(17275)
            value = global_values.value[key]
Coverage.statementStart(17276)
        }
Coverage.statementStart(17277)
        return value
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17278)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17279)
        values.clear()
Coverage.statementStart(17280)
        global_values.value = values.freeze()
Coverage.statementStart(17281)
    }
}
