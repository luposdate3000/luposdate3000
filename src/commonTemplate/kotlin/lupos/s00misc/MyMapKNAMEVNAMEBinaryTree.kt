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
Coverage.funStart(14595)
        keys.reserve(capacity)
Coverage.statementStart(14596)
        values.reserve(capacity)
Coverage.statementStart(14597)
    }
    constructor(data: Pair<KEY, VALUE>) : this() {
        set(data.first, data.second)
    }
    inline operator fun get(key: KEY): VALUE? {
Coverage.funStart(14598)
        var res: VALUE? = null
Coverage.statementStart(14599)
        keys.find(key, { res = values[it] })
Coverage.statementStart(14600)
        return res
    }
    inline operator fun set(key: KEY, value: VALUE) {
Coverage.funStart(14601)
        keys.add(key, { values.add(it, value) }, { values[it] = value })
Coverage.statementStart(14602)
    }
    inline fun getOrCreate(key: KEY, crossinline onCreate: () -> VALUE): VALUE {
Coverage.funStart(14603)
        var value: VALUE? = null
Coverage.statementStart(14604)
        keys.add(key, {
Coverage.statementStart(14605)
            value = onCreate()
Coverage.statementStart(14606)
            values.add(it, value!!)
Coverage.statementStart(14607)
        }, {
Coverage.statementStart(14608)
            value = values[it]
Coverage.statementStart(14609)
        })
Coverage.statementStart(14610)
        return value!!
    }
    fun withFastInitializer(action: (MyMapKNAMEVNAMEBinaryTreeGUSEKV) -> Unit) = action(this)
    fun appendAssumeSorted(key: KEY, value: VALUE): VALUE {
Coverage.funStart(14611)
        keys.add(key)
Coverage.statementStart(14612)
        values.add(value)
Coverage.statementStart(14613)
        return value
    }
    fun clear() {
Coverage.funStart(14614)
        keys.clear()
Coverage.statementStart(14615)
        values.clear()
Coverage.statementStart(14616)
    }
    inline fun iterator() = MyMapKNAMEVNAMEBinaryTreeIterator(this)
    inline fun forEach(crossinline action: (KEY, VALUE) -> Unit) {
Coverage.funStart(14617)
        val iteratorK = keys.iterator()
Coverage.statementStart(14618)
        val iteratorV = values.iterator()
Coverage.statementStart(14619)
        while (iteratorK.hasNext()) {
Coverage.whileLoopStart(14620)
            val k = iteratorK.next()
Coverage.statementStart(14621)
            val v = iteratorV.next()
Coverage.statementStart(14622)
            action(k, v)
Coverage.statementStart(14623)
        }
Coverage.statementStart(14624)
    }
    class MyMapKNAMEVNAMEBinaryTreeIteratorGDEF(val data: MyMapKNAMEVNAMEBinaryTreeGUSEKV) {
        var index = 0
        fun hasNext() = index < data.values.size
        fun next() = data.keys.data[index++]
        fun value() = data.values[index - 1]
    }
    fun safeToFile(filename: String) {
Coverage.funStart(14625)
        IOSTART
Coverage.statementStart(14626)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(14627)
            out.writeInt(size)
Coverage.statementStart(14628)
            val iteratorK = keys.iterator()
Coverage.statementStart(14629)
            val iteratorV = values.iterator()
Coverage.statementStart(14630)
            while (iteratorK.hasNext()) {
Coverage.whileLoopStart(14631)
                out.writeKEY(iteratorK.next())
Coverage.statementStart(14632)
                out.writeVALUE(iteratorV.next())
Coverage.statementStart(14633)
            }
Coverage.statementStart(14634)
        }
Coverage.statementStart(14635)
        IOEND
Coverage.statementStart(14636)
    }
    fun loadFromFile(filename: String) {
Coverage.funStart(14637)
        IOSTART
Coverage.statementStart(14638)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(14639)
            var size = fis.readInt()
Coverage.statementStart(14640)
            for (i in 0 until size) {
Coverage.forLoopStart(14641)
                keys.appendAssumeSorted(fis.readKEY())
Coverage.statementStart(14642)
                values.add(fis.readVALUE())
Coverage.statementStart(14643)
            }
Coverage.statementStart(14644)
        }
Coverage.statementStart(14645)
        IOEND
Coverage.statementStart(14646)
    }
}
