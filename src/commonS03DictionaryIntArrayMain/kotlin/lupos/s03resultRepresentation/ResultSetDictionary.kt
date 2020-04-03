package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeMutableList
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query

class ResultSetDictionary {
    @JvmField
    val mapSTL = mutableMapOf<ValueDefinition, Value>()
    @JvmField
    val mapLTS = mutableListOf<ValueDefinition>()

    companion object {
        @JvmField
        val undefValue = Int.MAX_VALUE
        @JvmField
        val undefValue2 = ValueUndef()
    }

    fun createValue(value: String?) = createValue(ValueDefinition(value))
    fun createValue(value: ValueDefinition): Value {
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

    fun getValue(value: Value): ValueDefinition {
        if (value == undefValue) {
            return undefValue2
        }
        return mapLTS[value]!!
    }
}
