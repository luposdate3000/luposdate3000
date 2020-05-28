package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableMap<k, v>() {
    val mutex = ReadWriteLock()
    val global_values = AtomicReference(mutableMapOf<k, v>().freeze())
    fun clear() = mutex.withWriteLock {
Coverage.funStart(17528)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17529)
        values.clear()
Coverage.statementStart(17530)
        global_values.value = values.freeze()
Coverage.statementStart(17531)
    }
    fun keySize(): Int {
Coverage.funStart(17532)
        var res = 0
Coverage.statementStart(17533)
        mutex.withReadLock {
Coverage.statementStart(17534)
            res = global_values.value.keys.size
Coverage.statementStart(17535)
        }
Coverage.statementStart(17536)
        return res
    }
    operator fun set(key: k, value: v) = mutex.withWriteLock {
Coverage.funStart(17537)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17538)
        values[key] = value
Coverage.statementStart(17539)
        global_values.value = values.freeze()
Coverage.statementStart(17540)
    }
    fun remove(key: k) = mutex.withWriteLock {
Coverage.funStart(17541)
        val values = global_values.value.toMutableMap()
Coverage.statementStart(17542)
        values.remove(key)
Coverage.statementStart(17543)
        global_values.value = values.freeze()
Coverage.statementStart(17544)
    }
    inline fun forEach(crossinline action: (k, v) -> Unit) = mutex.withReadLock {
Coverage.funStart(17545)
        global_values.value.forEach { it ->
Coverage.statementStart(17546)
            action(it.key, it.value)
Coverage.statementStart(17547)
        }
Coverage.statementStart(17548)
    }
    inline fun forEachKey(crossinline action: (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(17549)
        global_values.value.keys.forEach {
Coverage.forEachLoopStart(17550)
            action(it)
Coverage.statementStart(17551)
        }
Coverage.statementStart(17552)
    }
    inline suspend fun forEachKeySuspend(crossinline action: suspend (k) -> Unit) = mutex.withReadLock {
Coverage.funStart(17553)
        global_values.value.keys.forEach {
Coverage.forEachLoopStart(17554)
            action(it)
Coverage.statementStart(17555)
        }
Coverage.statementStart(17556)
    }
    inline fun forEachValue(crossinline action: (v) -> Unit) = mutex.withReadLock {
Coverage.funStart(17557)
        global_values.value.values.forEach {
Coverage.forEachLoopStart(17558)
            action(it)
Coverage.statementStart(17559)
        }
Coverage.statementStart(17560)
    }
    operator fun get(key: k): v? {
Coverage.funStart(17561)
        var value: v? = null
Coverage.statementStart(17562)
        mutex.withReadLock {
Coverage.statementStart(17563)
            value = global_values.value[key]
Coverage.statementStart(17564)
        }
Coverage.statementStart(17565)
        return value
    }
}
