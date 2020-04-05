package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeMutableList
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query

class ResultSetDictionary {
    companion object {
        @JvmField
        val undefValue = 0
        @JvmField
        val undefValue2 = ValueUndef()
    }

    @JvmField
    val mapSTL = mutableMapOf<ValueDefinition, Value>(undefValue2 to undefValue)
    @JvmField
    val mapLTS = mutableListOf<ValueDefinition>(undefValue2)

    inline fun createValue(value: String?) = createValue(ValueDefinition(value))
    inline fun createValue(value: ValueDefinition): Value {
        var res: Value = undefValue
        if (value is ValueUndef || value is ValueError) {
            return res
        }
        val o = mapSTL[value]
        if (o != null) {
            res = o
        } else {
            val l = mapLTS.size
            mapSTL[value] = l
            mapLTS.add(value)
            res = l
        }
        return res
    }

    inline fun getValue(value: Value): ValueDefinition {
        return mapLTS[value]
    }
}
