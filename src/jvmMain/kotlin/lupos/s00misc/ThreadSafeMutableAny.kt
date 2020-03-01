package lupos.s00misc


class ThreadSafeMutableAny<T>(var value: T) {
    fun get(): T {
        return value
    }

    fun set(value: T) {
        this.value = value
    }
}
