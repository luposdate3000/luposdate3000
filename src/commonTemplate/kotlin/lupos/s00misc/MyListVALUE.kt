/* Substitutions :: VALUE */
package lupos.s00misc

class MyListVALUE {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: VALUEArray

    constructor() {
        data = VALUEArray(capacity)
    }

    constructor(value: VALUE) {
        data = VALUEArray(capacity)
        data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> VALUE) {
        capacity = initialCapacity
        size = capacity
        data = VALUEArray(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: VALUE) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = VALUEArray(capacity)
            data.copyInto(tmp)
            data = tmp
        }
        data[size++] = value
    }

    inline operator fun get(idx: Int) = data.get(idx)
    inline operator fun set(idx: Int, key: VALUE) = data.set(idx, key)
    fun removeAt(idx: Int): VALUE {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res
    }

    fun add(idx: Int, value: VALUE) {
        if (size + 1 >= capacity) {
            capacity = capacity * 2
            val tmp = VALUEArray(capacity)
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

    inline operator fun iterator() = MyListVALUEIterator(this)
    class MyListVALUEIterator(@JvmField val data: MyListVALUE) : Iterator<VALUE> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++]
    }
}
