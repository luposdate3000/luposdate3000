/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc

import lupos.s00misc.Coverage

/* Substitutions :: Double,Int,,,,,Double,Int */
class MyMapDoubleIntBinaryTree() {
    @JvmField
    var keys = MySetDoubleBinaryTree()
    @JvmField
    var values = MyListInt()
    var size: Int = 0
        get() = keys.size

    inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }

    constructor(data: Pair<Double, Int>) : this() {
        set(data.first, data.second)
    }

    inline operator fun get(key: Double): Int? {
        var res: Int? = null
        keys.find(key, { res = values[it] })
        return res
    }

    inline operator fun set(key: Double, value: Int) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }

    inline fun getOrCreate(key: Double, crossinline onCreate: () -> Int): Int {
        var value: Int? = null
        keys.add(key, {
            value = onCreate()
            values.add(it, value!!)
        }, {
            value = values[it]
        })
        return value!!
    }

    fun appendAssumeSorted(key: Double, value: Int): Int {
        keys.add(key)
        values.add(value)
        return value
    }

    fun clear() {
        keys.clear()
        values.clear()
    }

    inline fun iterator() = MyMapDoubleIntBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (Double, Int) -> Unit) {
        val iteratorK = keys.iterator()
        val iteratorV = values.iterator()
        while (iteratorK.hasNext()) {
            val k = iteratorK.next()
            val v = iteratorV.next()
            action(k, v)
        }
    }

    class MyMapDoubleIntBinaryTreeIterator(val data: MyMapDoubleIntBinaryTree) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }

    fun safeToFile(filename: String) {
        
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            val iteratorK = keys.iterator()
            val iteratorV = values.iterator()
            while (iteratorK.hasNext()) {
                out.writeDouble(iteratorK.next())
                out.writeInt(iteratorV.next())
            }
        }
        
    }

    fun loadFromFile(filename: String) {
        
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readDouble())
                values.add(fis.readInt())
            }
        }
        
    }
}
