package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
/* Substitutions :: Int,Double,,,,,Int,Double */
class MyMapIntDoubleBinaryTree() {
    @JvmField
    var keys = MySetIntBinaryTree()
    @JvmField
    var values = MyListDouble()
    var size: Int = 0
        get() = keys.size

    inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }

    constructor(data: Pair<Int, Double>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Int): Double? {
        var res: Double? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Int, value: Double) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> Double): Double {
        var value: Double? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: Double): Double {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapIntDoubleBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (Int, Double) -> Unit) {
        for (i in 0 until values.size) {
            action(keys.data[i], values[i])
        }
    }

    class MyMapIntDoubleBinaryTreeIterator(val data: MyMapIntDoubleBinaryTree) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            for (i in 0 until size) {
                out.writeInt(keys.data[i])
            }
            for (i in 0 until size) {
                out.writeDouble(values[i])
            }
        }
    }

    fun loadFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readInt())
            }
            for (i in 0 until size) {
                values.add(fis.readDouble())
            }
        }
    }
}
