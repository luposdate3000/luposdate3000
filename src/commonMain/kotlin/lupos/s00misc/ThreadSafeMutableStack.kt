package lupos.s00misc

class ThreadSafeMutableStack<T>() {
    val elements = ThreadSafeMutableList<T>()

     fun isEmpty() = elements.isEmpty()

     fun size() = elements.size()

     fun push(item: T) = elements.add(item)

     fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            val idx = elements.size() - 1
            elements.removeAt(idx)
        }
        return item
    }

     fun peek(): T? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}

