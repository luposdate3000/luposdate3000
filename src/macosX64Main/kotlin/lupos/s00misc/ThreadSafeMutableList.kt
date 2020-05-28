package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableList<T>(values: MutableList<T> = mutableListOf<T>()) {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(values.freeze())
    constructor(value: T) : this(mutableListOf(value))
    fun size(): Int {
Coverage.funStart(17488)
        var res: Int = 0
Coverage.statementStart(17489)
        mutex.withReadLock {
Coverage.statementStart(17490)
            res = global_values.value.size
Coverage.statementStart(17491)
        }
Coverage.statementStart(17492)
        return res
    }
    fun isEmpty(): Boolean {
Coverage.funStart(17493)
        var res = false
Coverage.statementStart(17494)
        mutex.withReadLock {
Coverage.statementStart(17495)
            res = size() == 0
Coverage.statementStart(17496)
        }
Coverage.statementStart(17497)
        return res
    }
    fun lastOrNull(): T? {
Coverage.funStart(17498)
        var res: T? = null
Coverage.statementStart(17499)
        mutex.withReadLock {
Coverage.statementStart(17500)
            res = global_values.value.lastOrNull()
Coverage.statementStart(17501)
        }
Coverage.statementStart(17502)
        return res
    }
    fun add(value: T) = mutex.withWriteLock {
Coverage.funStart(17503)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17504)
        values.add(value)
Coverage.statementStart(17505)
        global_values.value = values.freeze()
Coverage.statementStart(17506)
    }
    fun add(idx: Int, value: T) = mutex.withWriteLock {
Coverage.funStart(17507)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17508)
        values.add(idx, value)
Coverage.statementStart(17509)
        global_values.value = values.freeze()
Coverage.statementStart(17510)
    }
    fun removeAt(idx: Int) = mutex.withWriteLock {
Coverage.funStart(17511)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17512)
        values.removeAt(idx)
Coverage.statementStart(17513)
        global_values.value = values.freeze()
Coverage.statementStart(17514)
    }
    fun forEach(action: (T) -> Unit) {
Coverage.funStart(17515)
        mutex.withReadLock {
Coverage.statementStart(17516)
            global_values.value.forEach(action)
Coverage.statementStart(17517)
        }
Coverage.statementStart(17518)
    }
    operator fun get(key: Int): T? {
Coverage.funStart(17519)
        var value: T? = null
Coverage.statementStart(17520)
        mutex.withReadLock {
Coverage.statementStart(17521)
            value = global_values.value[key]
Coverage.statementStart(17522)
        }
Coverage.statementStart(17523)
        return value
    }
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17524)
        val values = global_values.value.toMutableList()
Coverage.statementStart(17525)
        values.clear()
Coverage.statementStart(17526)
        global_values.value = values.freeze()
Coverage.statementStart(17527)
    }
}
