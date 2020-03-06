package lupos.s00misc


class ThreadSafeMutableAny<T>(@JvmField var value: T) {
    fun get(): T {
        return value
    }

    fun set(value: T) {
        this.value = value
    }
}
