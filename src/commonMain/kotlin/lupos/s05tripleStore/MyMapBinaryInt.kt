package lupos.s05tripleStore

import lupos.s00misc.Coverage

class MyMapBinaryInt<T>() {
    var keys = MySetInt()
    var values = mutableListOf<T>()

constructor(data: Pair<Int, T>) : this() {
        set(data.first, data.second)
    }

inline     operator fun get(key: Int): T? {
        var res: T? = null
        keys.find(key, { res = values[it] })
        return res
    }

inline     operator fun set(key: Int, value: T) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

inline     fun getOrCreate(key: Int,crossinline onCreate: () -> T): T {
        var value: T? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: T): T {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapBinaryIntIterator(this)

class MyMapBinaryIntIterator<T>(val data: MyMapBinaryInt<T>) {
    var index = 0
    fun hasNext() = index < data.values.size
    fun next() = data.keys.data[index++]
    fun value() = data.values[index - 1]
}
}

