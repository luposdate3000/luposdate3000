/* Substitutions :: KEY,VALUE */
package lupos.s00misc

import lupos.s00misc.Coverage

class MyMapKEYVALUEBinaryTree() {
    @JvmField
    var keys = MySetKEY()
    @JvmField
    var values = MyListVALUE()
    var size: Int = 0
        get() = keys.size

    constructor(data: Pair<KEY, VALUE>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: KEY): VALUE? {
        var res: VALUE? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: KEY, value: VALUE) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: KEY, crossinline onCreate: () -> VALUE): VALUE {
        var value: VALUE? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: KEY, value: VALUE): VALUE {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapKEYVALUEBinaryTreeIterator(this)
    class MyMapKEYVALUEBinaryTreeIterator(val data: MyMapKEYVALUEBinaryTree) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            for (i in 0 until size) {
                out.writeKEY(keys.data[i])
            }
            for (i in 0 until size) {
                out.writeVALUE(values[i])
            }
        }
    }

    fun readFromFile(filename: String) {
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readKEY())
            }
            for (i in 0 until size) {
                values.add(fis.readVALUE())
            }
        }
    }
}
