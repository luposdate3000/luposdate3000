package lupos.s00misc
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import lupos.s00misc.Coverage
class ThreadSafeMutableAny<T>(val value: T) {
    val global_value = AtomicReference(value.freeze())
    fun get(): T {
Coverage.funStart(17485)
        return global_value.value
    }
    fun set(value: T) {
Coverage.funStart(17486)
        global_value.value = value.freeze()
Coverage.statementStart(17487)
    }
}
