package lupos.s00misc
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
class ThreadSafeMutableAny<T>(@JvmField var value: T) {
    fun get(): T {
Coverage.funStart(16272)
        return value
    }
    fun set(value: T) {
Coverage.funStart(16273)
        this.value = value
Coverage.statementStart(16274)
    }
}
