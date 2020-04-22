package lupos.s00misc

class MyListAny<T> {
    var data = mutableListOf<T>()
    var size: Int = 0
        get() = data.size

    constructor() {
    }

    constructor(value: T) {
        data.add(value)
    }

    constructor(initialCapacity: Int, init: (Int) -> T) {
        for (i in 0 until initialCapacity) {
            data.add(init(i))
        }
    }

    fun clear() = data.clear()
    fun add(value: T) = data.add(value)
    inline operator fun get(key: Int): T = data.get(key)
    inline operator fun set(idx: Int, key: T): T = data.set(idx, key)
    fun removeAt(idx: Int): T = data.removeAt(idx)
    fun add(idx: Int, value: T) = data.add(idx, value)
    inline operator fun iterator() = data.iterator()
}
