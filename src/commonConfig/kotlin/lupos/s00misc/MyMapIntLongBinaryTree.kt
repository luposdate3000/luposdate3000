/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: Int,Long,,,,,Int,Long */
class MyMapIntLongBinaryTree() {
    @JvmField
    var keys = MySetIntBinaryTree()
    @JvmField
    var values = MyListLong()
    var size: Int = 0
        get() = keys.size

    inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }

    constructor(data: Pair<Int, Long>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Int): Long? {
        var res: Long? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Int, value: Long) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Int, crossinline onCreate: () -> Long): Long {
        var value: Long? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Int, value: Long): Long {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapIntLongBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (Int, Long) -> Unit) {
val iteratorK=keys.iterator()
val iteratorV=values.iterator()
while(iteratorK.hasNext()){
val k=iteratorK.next()
val v=iteratorV.next()
action(k,v)
}
    }

    class MyMapIntLongBinaryTreeIterator(val data: MyMapIntLongBinaryTree) {
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
                out.writeLong(values[i])
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
                values.add(fis.readLong())
            }
        }
        
    }
}
