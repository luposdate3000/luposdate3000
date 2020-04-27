package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: KEY,VALUE,GDEF,GUSEKV,GUSEK,GUSEV,KNAME,VNAME */
class MyMapKNAMEVNAMEBinaryTreeGDEF() {
    @JvmField
    var keys = MySetKNAMEBinaryTreeGUSEK()
    @JvmField
    var values = MyListVNAMEGUSEV()
    var size: Int = 0
        get() = keys.size

    inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }

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

    inline fun iterator() = MyMapKNAMEVNAMEBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (KEY, VALUE) -> Unit) {
        for (i in 0 until values.size) {
            action(keys.data[i], values[i])
        }
    }

    class MyMapKNAMEVNAMEBinaryTreeIteratorGDEF(val data: MyMapKNAMEVNAMEBinaryTreeGUSEKV) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        IOSTART
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            for (i in 0 until size) {
                out.writeKEY(keys.data[i])
            }
            for (i in 0 until size) {
                out.writeVALUE(values[i])
            }
        }
        IOEND
    }

    fun loadFromFile(filename: String) {
        IOSTART
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readKEY())
            }
            for (i in 0 until size) {
                values.add(fis.readVALUE())
            }
        }
        IOEND
    }
}
