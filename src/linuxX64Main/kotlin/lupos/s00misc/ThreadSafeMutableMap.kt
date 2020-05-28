package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableMap<k, v>() {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(mutableMapOf<k, v>().freeze())
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17282)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17283)
        values.clear()
Coverage.statementStart(17284)
        global_values.value = values.freeze()
Coverage.statementStart(17285)
    }
    fun keySize(): Int {
Coverage.funStart(17286)
        var res = 0
Coverage.statementStart(17287)
        mutex.withReadLock {
Coverage.statementStart(17288)
            res = global_values.value.keys.size
Coverage.statementStart(17289)
        }
Coverage.statementStart(17290)
        return res
    }
    operator fun set(key: k, value: v) = mutex.withWriteLock {
Coverage.funStart(17291)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17292)
        values[key] = value
Coverage.statementStart(17293)
        global_values.value = values.freeze()
Coverage.statementStart(17294)
    }
    fun remove(key: k) = mutex.withWriteLock {
Coverage.funStart(17295)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17296)
        values.remove(key)
Coverage.statementStart(17297)
        global_values.value = values.freeze()
Coverage.statementStart(17298)
    }
    inline fun forEach(crossinline action: (k, v) -> Unit) = mutex.withReadLock {
Coverage.funStart(17299)
        global_values.value.forEach { it ->
Coverage.statementStart(17300)
            action(it.key, it.value)
Coverage.statementStart(17301)
        }
Coverage.statementStart(17302)
    }
    inline fun forEachKey(crossinline action: (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(17303)
        global_values.value.keys.forEach {
Coverage.forEachLoopStart(17304)
            action(it)
Coverage.statementStart(17305)
        }
Coverage.statementStart(17306)
    }
    inline suspend fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(17307)
        global_values.value.keys.forEach {
Coverage.forEachLoopStart(17308)
            action(it)
Coverage.statementStart(17309)
        }
Coverage.statementStart(17310)
    }
    inline fun forEachValue(crossinline action: (v) -> Unit) = mutex.withReadLock {
Coverage.funStart(17311)
        global_values.value.values.forEach {
Coverage.forEachLoopStart(17312)
            action(it)
Coverage.statementStart(17313)
        }
Coverage.statementStart(17314)
    }
    operator fun get(key: k): v? {
Coverage.funStart(17315)
        var value: v? = null
Coverage.statementStart(17316)
        mutex.withReadLock {
Coverage.statementStart(17317)
            value = global_values.value[key]
Coverage.statementStart(17318)
        }
Coverage.statementStart(17319)
        return value
    }
}
