package lupos.s00misc



class ThreadSafeMutableList<T>() {
    val list = mutableListOf<T>()

    fun forEach(action: (T) -> Unit) {
        synchronized(list) {
            list.forEach(action)
        }
    }

    fun size(): Int {
        return list.size
    }

    fun isEmpty(): Boolean {
        return size() == 0
    }

    fun lastOrNull(): T? {
        synchronized(list) {
            return list.lastOrNull()
        }
    }

    fun add(value: T) {
        synchronized(list) {
            list.add(value)
        }
    }

    fun removeAt(idx: Int) {
        synchronized(list) {
            list.removeAt(idx)
        }
    }
}
