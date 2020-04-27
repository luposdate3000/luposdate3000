/* this File is autogenerated by generate-buildfile.kts */

/* DO NOT MODIFY DIRECTLY */

/* to Substitute:: Long,Double */
package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapLongDoubleBinaryTree() {
    @JvmField
    var keys = MySetLong()
    @JvmField
    var values = MyListDouble()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<Long, Double>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Long): Double? {
        var res: Double? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Long, value: Double) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Long, crossinline onCreate: () -> Double): Double {
        var value: Double? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Long, value: Double): Double {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapLongDoubleBinaryTreeIterator(this)
    class MyMapLongDoubleBinaryTreeIterator(val data: MyMapLongDoubleBinaryTree) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            for (i in 0 until size) {
                out.writeLong(keys.data[i])
            }
            for (i in 0 until size) {
                out.writeDouble(values[i])
            }
        }
    }

    fun readFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readLong())
            }
            for (i in 0 until size) {
                values.add(fis.readDouble())
            }
        }
    }
}
