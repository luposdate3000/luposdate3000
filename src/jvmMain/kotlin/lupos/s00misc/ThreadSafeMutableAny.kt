package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query


class ThreadSafeMutableAny<T>(@JvmField var value: T) {
    fun get(): T {
        return value
    }

    fun set(value: T) {
        this.value = value
    }
}
