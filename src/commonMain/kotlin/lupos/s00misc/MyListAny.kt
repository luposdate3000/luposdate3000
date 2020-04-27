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
            reserve(capacity * 2)
        }
        data[size++] = value
    }

    inline operator fun get(key: Int) = data.get(key) as T
    inline operator fun set(idx: Int, value: T) = data.set(idx, value)
    fun remove(value: T): Boolean {
        for (idx in 0 until size) {
            if (data[idx] == value) {
                removeAt(idx)
                return true
            }
        }
        return false
    }

    fun removeAt(idx: Int): T {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res as T
    }

    inline fun reserve(capacity: Int) {
        if (this.capacity < capacity) {
            this.capacity = capacity
            val tmp = Array<Any?>(capacity) { null }
            data.copyInto(tmp)
            data = tmp
        }
    }

    fun add(idx: Int, value: T) {
        if (size + 1 >= capacity) {
            reserve(capacity * 2)
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
