package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ThreadSafeMutableList
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.Query

val nodeGlobalDictionary = ResultSetDictionary(true)

class ResultSetDictionary(val global: Boolean = false) {
    companion object {
        @JvmField
        val booleanTrueValue = 0//required by truth-tables
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        @JvmField
        val booleanFalseValue = 1//required by truth-tables
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        @JvmField
        val errorValue = 2//required by truth-tables
        @JvmField
        val errorValue2 = ValueError()
        @JvmField
        val undefValue = 3
        @JvmField
        val undefValue2 = ValueUndef()
    }

    inline fun toBooleanOrError(value: Value): Value {
        var res: Value = errorValue
        if (value < undefValue && value >= 0) {
            res = value
        } else {
            try {
                if (getValue(value).toBoolean()) {
                    res = booleanTrueValue
                } else {
                    res = booleanFalseValue
                }
            } catch (e: Throwable) {
            }
        }
        return res
    }

    @JvmField
    val mapSTL = mutableMapOf<ValueDefinition, Value>(undefValue2 to undefValue, errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue)
    @JvmField
    val mapLTS = mutableListOf<ValueDefinition>(booleanTrueValue2, booleanFalseValue2, errorValue2, undefValue2)

    inline fun createValue(value: String?) = createValue(ValueDefinition(value))
    inline fun checkValue(value: ValueDefinition): Value? {
        var res: Value?
        if (value is ValueUndef) {
            res = undefValue
        } else if (value is ValueError) {
            res = errorValue
        } else {
            if (global) {
                    res = mapSTL[value]
            } else {
                res = nodeGlobalDictionary.mapSTL[value]
                if (res == null) {
                    res = mapSTL[value]
			if(res!=null){
res=-res
}
                }
            }
        }
        return res
    }

    inline fun createValue(value: ValueDefinition): Value {
        var res = checkValue(value)
        if (res == null) {
            val l = mapLTS.size
            mapSTL[value] = l
            mapLTS.add(value)
            if (global) {
                res = l
            } else {
                res = -l
            }
        }
        return res!!
    }

    inline fun getValue(value: Value): ValueDefinition {
        if (value < 0) {
            return mapLTS[-value]
        } else {
            return nodeGlobalDictionary.mapLTS[value]
        }
    }
inline fun valueToGlobal(value:Value):Value{
if(value>=0){
return value
}else{
return nodeGlobalDictionary.createValue(getValue(value))
}
}
}
