package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapAny<K : Comparable<K>, V>() {
    @JvmField
    var keys = MySetAny<K>()
    @JvmField
    var values = MyListAny<V>()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<K, V>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: K): V? {
        var res: V? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: K, value: V) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: K, crossinline onCreate: () -> V): V {
        var value: V? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: K, value: V): V {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapAnyIterator(this)
    class MyMapAnyIterator<K : Comparable<K>, V>(val data: MyMapAny<K, V>) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }
}
