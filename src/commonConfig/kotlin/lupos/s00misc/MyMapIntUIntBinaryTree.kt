/* this File is autogenerated by generate-buildfile.kts */

/* DO NOT MODIFY DIRECTLY */

/* to Substitute:: Int,UInt */
package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapIntUIntBinaryTree() {
    @JvmField
    var keys = MySetInt()
    @JvmField
    var values = MyListUInt()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<Int, UInt>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Int): UInt? {
        var res: UInt? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Int, value: UInt) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> UInt): UInt {
        var value: UInt? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: UInt): UInt {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapIntUIntBinaryTreeIterator(this)
    class MyMapIntUIntBinaryTreeIterator(val data: MyMapIntUIntBinaryTree) {
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
                out.writeUInt(values[i])
            }
        }
    }

    fun readFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readInt())
            }
            for (i in 0 until size) {
                values.add(fis.readUInt())
            }
        }
    }
}
