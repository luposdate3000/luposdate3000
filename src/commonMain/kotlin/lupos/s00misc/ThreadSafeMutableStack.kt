package lupos.s00misc

import kotlin.jvm.JvmField


class ThreadSafeMutableStack<T> {
    @JvmField
    val elements = ThreadSafeMutableList<T>()

    fun isEmpty() = elements.isEmpty()

    fun push(item: T) = elements.add(item)

    fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            val idx = elements.size() - 1
            elements.removeAt(idx)
        }
        return item
    }

    override fun toString(): String = elements.toString()
}

