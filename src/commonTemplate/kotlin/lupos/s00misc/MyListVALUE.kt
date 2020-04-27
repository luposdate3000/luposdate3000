package lupos.s00misc

/* Substitutions :: VALUE,GDEF,GUSE,ARRAYTYPE,ARRAYINITIALIZER */
class MyListVALUEGDEF {
    @JvmField
    var size = 0
    @JvmField
    var capacity = 10
    @JvmField
    var data: ARRAYTYPE

    inline fun reserve(capacity: Int) {
        if (this.capacity < capacity) {
            this.capacity = capacity
            val tmp = ARRAYTYPE(capacity) ARRAYINITIALIZER
                    data.copyInto(tmp)
            data = tmp
        }
    }

    constructor() {
        data = ARRAYTYPE(capacity) ARRAYINITIALIZER
    }

    constructor(value: VALUE) {
        data = ARRAYTYPE(capacity) ARRAYINITIALIZER
                data[size++] = value
    }

    constructor(initialCapacity: Int, init: (Int) -> VALUE) {
        capacity = initialCapacity
        size = capacity
        data = ARRAYTYPE(capacity) { init(it) }
    }

    fun clear() {
        size = 0
    }

    fun add(value: VALUE) {
        if (size + 1 >= capacity) {
            reserve(capacity * 2)
        }
        data[size++] = value
    }

    inline operator fun get(idx: Int) = data.get(idx) as VALUE
    inline operator fun set(idx: Int, key: VALUE) = data.set(idx, key)
    fun remove(value: VALUE): Boolean {
        for (idx in 0 until size) {
            if (data[idx] == value) {
                removeAt(idx)
                return true
            }
        }
        return false
    }

    fun removeAt(idx: Int): VALUE {
        val res = data[idx]
        require(idx < size)
        for (i in idx until size) {
            data[i] = data[i + 1]
        }
        size--
        return res as VALUE
    }

    fun add(idx: Int, value: VALUE) {
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

    inline operator fun iterator() = MyListVALUEIterator(this)
    class MyListVALUEIteratorGDEF(@JvmField val data: MyListVALUEGUSE) : Iterator<VALUE> {
        var index = 0
        override fun hasNext() = index < data.size
        override fun next() = data.data[index++] as VALUE
    }
}
