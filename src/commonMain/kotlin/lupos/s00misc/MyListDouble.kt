package lupos.s00misc

class MyListDouble {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: DoubleArray

    constructor() {
        data = DoubleArray(capacity)
    }

    constructor(value: Double) {
        data = DoubleArray(capacity)
        data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> Double) {
        capacity = initialCapacity
        size = capacity
        data = DoubleArray(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: Double) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = DoubleArray(capacity)
            data.copyInto(tmp)
            data = tmp
        }
        data[size++] = value
    }

    inline operator fun get(idx: Int) = data.get(idx)
    inline operator fun set(idx: Int, key: Double) = data.set(idx, key)
    fun removeAt(idx: Int): Double {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res
    }

    fun add(idx: Int, value: Double) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = DoubleArray(capacity)
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

    inline operator fun iterator() = MyListDoubleIterator(this)
    class MyListDoubleIterator(@JvmField val data: MyListDouble) : Iterator<Double> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++]
    }
}
