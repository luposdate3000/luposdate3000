package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapLong<T>() {
    @JvmField
    var keys = MySetLong()
    @JvmField
    var values = mutableListOf<T>()

    constructor(data: Pair<Long, T>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Long): T? {
        var res: T? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Long, value: T) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Long, crossinline onCreate: () -> T): T {
        var value: T? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Long, value: T): T {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapLongIterator(this)
    class MyMapLongIterator<T>(val data: MyMapLong<T>) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }
}
