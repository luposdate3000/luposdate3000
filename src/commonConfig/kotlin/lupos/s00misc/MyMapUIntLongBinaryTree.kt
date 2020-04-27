/* this File is autogenerated by generate-buildfile.kts */

/* DO NOT MODIFY DIRECTLY */

/* to Substitute:: UInt,Long */
package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapUIntLongBinaryTree() {
    @JvmField
    var keys = MySetUInt()
    @JvmField
    var values = MyListLong()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<UInt, Long>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: UInt): Long? {
        var res: Long? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: UInt, value: Long) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: UInt, crossinline onCreate: () -> Long): Long {
        var value: Long? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: UInt, value: Long): Long {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapUIntLongBinaryTreeIterator(this)
    class MyMapUIntLongBinaryTreeIterator(val data: MyMapUIntLongBinaryTree) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            for (i in 0 until size) {
                out.writeUInt(keys.data[i])
            }
            for (i in 0 until size) {
                out.writeLong(values[i])
            }
        }
    }

    fun readFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readUInt())
            }
            for (i in 0 until size) {
                values.add(fis.readLong())
            }
        }
    }
}
