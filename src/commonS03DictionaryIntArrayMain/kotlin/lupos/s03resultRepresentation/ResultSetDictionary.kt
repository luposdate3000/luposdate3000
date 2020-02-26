package lupos.s03resultRepresentation
import lupos.s00misc.EOperatorID

import lupos.s00misc.*


class ResultSetDictionary() {
    val mapSTL = ThreadSafeMutableMap<String, Value>()
    val mapLTS = ThreadSafeMutableList<String>()
    val undefValue = Value.MAX_VALUE
    val mutex = CoroutinesHelper.createLock()
    fun createValue(value: String): Value {
        var res: Value = undefValue
        CoroutinesHelper.runBlockWithLock(mutex, {
            val o = mapSTL[value]
            if (o != null)
                res = o
            else {
                val l = mapLTS.size()
                mapSTL[value] = l
                mapLTS.add(value)
                res = l
            }
        })
        return res
    }

    fun getValue(value: Value): String? {
        if (value == undefValue)
            return null
        var res: String? = null
        CoroutinesHelper.runBlockWithLock(mutex, {
            res = mapLTS[value.toInt()]
        })
        return res
    }
}
