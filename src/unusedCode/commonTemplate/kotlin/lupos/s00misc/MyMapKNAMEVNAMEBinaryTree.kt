package lupos.s00misc
import kotlin.jvm.JvmField
/* Substitutions :: KEY,VALUE,GDEF,GUSEKV,GUSEK,GUSEV,KNAME,VNAME */
public class MyMapKNAMEVNAMEBinaryTreeGDEF() {
    @JvmField
    var keys = MySetKNAMEBinaryTreeGUSEK()
    @JvmField
    var values = MyListVNAMEGUSEV()
    var size: Int = 0
        get() = keys.size
    internal inline fun reserve(capacity: Int) {
        keys.reserve(capacity)
        values.reserve(capacity)
    }
    public constructor(data: Pair<KEY, VALUE>) : this() {
        set(data.first, data.second)
    }
    internal inline operator fun get(key: KEY): VALUE? {
        var res: VALUE? = null
        keys.find(key, { res = values[it] })
        return res
    }
    internal inline operator fun set(key: KEY, value: VALUE) {
        keys.add(key, { values.add(it, value) }, { values[it] = value })
    }
    internal inline fun getOrCreate(key: KEY, crossinline onCreate: () -> VALUE): VALUE {
        var value: VALUE? = null
        keys.add(
            key,
            {
                value = onCreate()
                values.add(it, value!!)
            },
            {
                value = values[it]
            }
        )
        return value!!
    }
    public fun withFastInitializer(action: (MyMapKNAMEVNAMEBinaryTreeGUSEKV) -> Unit) = action(this)
    public fun appendAssumeSorted(key: KEY, value: VALUE): VALUE {
        keys.add(key)
        values.add(value)
        return value
    }
    public fun clear() {
        keys.clear()
        values.clear()
    }
    internal inline fun iterator() = MyMapKNAMEVNAMEBinaryTreeIterator(this)
    internal inline fun forEach(crossinline action: (KEY, VALUE) -> Unit) {
        val iteratorK = keys.iterator()
        val iteratorV = values.iterator()
        while (iteratorK.hasNext()) {
            val k = iteratorK.next()
            val v = iteratorV.next()
            action(k, v)
        }
    }
    public class MyMapKNAMEVNAMEBinaryTreeIteratorGDEF(val data: MyMapKNAMEVNAMEBinaryTreeGUSEKV) {
        var index = 0
        public fun hasNext() = index < data.values.size
        public fun next() = data.keys.data[index++]
        public fun value() = data.values[index - 1]
    }
    public fun safeToFile(filename: String) {
        IOSTART1
        File(filename).dataOutputStream { out ->
            out.writeInt(size)
            val iteratorK = keys.iterator()
            val iteratorV = values.iterator()
            while (iteratorK.hasNext()) {
                out.writeKEY(iteratorK.next())
                out.writeVALUE(iteratorV.next())
            }
        }
        IOEND1
    }
    public fun loadFromFile(filename: String) {
        IOSTART2
        File(filename).dataInputStream { fis ->
            var size = fis.readInt()
            for (i in 0 until size) {
                keys.appendAssumeSorted(fis.readKEY())
                values.add(fis.readVALUE())
            }
        }
        IOEND2
    }
}
