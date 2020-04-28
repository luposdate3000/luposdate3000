/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: Int,Generic,<Generic>,<Generic>,,<Generic>,Int,Generic */
class MyMapIntGenericBinaryTree<Generic>() {
    @JvmField
    var keys = MySetIntBinaryTree()
    @JvmField
    var values = MyListGeneric<Generic>()
    var size: Int = 0
        get() = keys.size

    inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }

    constructor(data: Pair<Int, Generic>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Int): Generic? {
        var res: Generic? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Int, value: Generic) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> Generic): Generic {
        var value: Generic? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: Generic): Generic {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapIntGenericBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (Int, Generic) -> Unit) {
        for (i in 0 until values.size) {
            action(keys.data[i], values[i])
        }
    }

    class MyMapIntGenericBinaryTreeIterator<Generic>(val data: MyMapIntGenericBinaryTree<Generic>) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        throw Exception("not Implemented")
    }
}
