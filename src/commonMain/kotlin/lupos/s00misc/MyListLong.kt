package lupos.s00misc

class MyListLong {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: LongArray

    constructor() {
        data = LongArray(capacity)
    }

    constructor(value: Long) {
        data = LongArray(capacity)
        data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> Long) {
        capacity = initialCapacity
        size = capacity
        data = LongArray(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: Long) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = LongArray(capacity)
            data.copyInto(tmp)
            data = tmp
        }
        data[size++] = value
    }

    inline operator fun get(idx: Int) = data.get(idx)
    inline operator fun set(idx: Int, value: Long) = data.set(idx, value)
    fun removeAt(idx: Int): Long {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res
    }

    fun add(idx: Int, value: Long) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = LongArray(capacity)
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

    inline operator fun iterator() = MyListLongIterator(this)
    class MyListLongIterator(@JvmField val data: MyListLong) : Iterator<Long> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++]
    }
}
