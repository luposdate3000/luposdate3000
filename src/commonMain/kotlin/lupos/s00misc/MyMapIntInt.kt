package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapIntInt() {
    @JvmField
    var keys = MySetInt()
    @JvmField
    var values = MyListInt()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<Int, Int>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Int): Int? {
        var res: Int? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Int, value: Int) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> Int): Int {
        var value: Int? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: Int): Int {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapIntIntIterator(this)
    class MyMapIntIntIterator(val data: MyMapIntInt) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }
}
