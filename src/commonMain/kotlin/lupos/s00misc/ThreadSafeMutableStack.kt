package lupos.s00misc

class ThreadSafeMutableStack<T>() {
    val elements = ThreadSafeMutableList<T>()

    inline fun isEmpty() = elements.isEmpty()

    inline fun size() = elements.size()

    inline fun push(item: T) = elements.add(item)

    inline fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            val idx = elements.size() - 1
            elements.removeAt(idx)
        }
        return item
    }

    inline fun peek(): T? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}

