package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


class ThreadSafeMutableAny<T>(@JvmField var value: T) {
    fun get(): T {
        return value
    }

    fun set(value: T) {
        this.value = value
    }
}
