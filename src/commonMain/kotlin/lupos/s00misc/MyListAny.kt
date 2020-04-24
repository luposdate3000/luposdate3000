package lupos.s00misc

class MyListAny<T> {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: Array<Any?>

    constructor() {
        data = Array<Any?>(capacity) { null }
    }

    constructor(value: T) {
        data = Array<Any?>(capacity) { null }
        data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> T) {
        capacity = initialCapacity
        size = capacity
        data = Array<Any?>(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: T) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = Array<Any?>(capacity) { null }
            data.copyInto(tmp)
            data = tmp
        }
        data[size++] = value
    }

    inline operator fun get(key: Int) = data.get(key) as T
    inline operator fun set(idx: Int, value: T) = data.set(idx, value)
    fun removeAt(idx: Int): T {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res as T
    }

    fun add(idx: Int, value: T) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = Array<Any?>(capacity) { null }
            data.copyInto(tmp)
            data = tmp
        }
        if (idx < size) {
            size++
            for (i in size downTo idx + 1) {
                data[i] = data[i - 1]
            }
            data[idx] = value
        } else {
            data[size++] = value
        }
    }

    inline operator fun iterator() = MyListAnyIterator(this)
    class MyListAnyIterator<T>(@JvmField val data: MyListAny<T>) : Iterator<T> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++] as T
    }
}
