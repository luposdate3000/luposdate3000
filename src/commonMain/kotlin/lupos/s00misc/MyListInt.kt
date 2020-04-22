package lupos.s00misc

class MyListInt {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: IntArray

    constructor() {
        data = IntArray(capacity)
    }

    constructor(value: Int) {
        data = IntArray(capacity)
        data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> Int) {
        capacity = initialCapacity
        size = capacity
        data = IntArray(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: Int) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = IntArray(capacity)
            data.copyInto(tmp)
            data = tmp
        }
        data[size++] = value
    }

    inline operator fun get(key: Int) = data.get(key)
    inline operator fun set(idx: Int, key: Int) = data.set(idx, key)
    fun removeAt(idx: Int): Int {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res
    }

    fun add(idx: Int, value: Int) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = IntArray(capacity)
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

    inline operator fun iterator() = MyListIntIterator(this)
    class MyListIntIterator(@JvmField val data: MyListInt) : Iterator<Int> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++]
    }
}
