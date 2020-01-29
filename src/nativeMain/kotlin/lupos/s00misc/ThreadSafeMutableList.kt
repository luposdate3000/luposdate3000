package lupos.s00misc

expect class ThreadSafeMutableList<T>() {
    fun forEach(action: (T) -> Unit)
    fun size(): Int
    fun isEmpty(): Boolean
    fun lastOrNull(): T?
    fun add(value: T)
    fun removeAt(idx: Int)
}
