package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
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
        @JvmField
        val errorValue = 1
        @JvmField
        val errorValue2 = ValueError()
        @JvmField
        val booleanTrueValue = 2
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        @JvmField
        val booleanFalseValue = 3
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)

    }

    @JvmField
    val mapSTL = mutableMapOf<ValueDefinition, Value>(undefValue2 to undefValue,errorValue2 to errorValue,booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue)
    @JvmField
    val mapLTS = mutableListOf<ValueDefinition>(undefValue2)

    inline fun createValue(value: String?) = createValue(ValueDefinition(value))
    inline fun createValue(value: ValueDefinition): Value {
        try {
            //BenchmarkUtils.start(EBenchmark.IMPORT_DICTIONARY_INSERT)
            var res: Value 
            if (value is ValueUndef ) {
                res= undefValue
            }
else if(value is ValueError){
res= errorValue
}else{
            val o = mapSTL[value]
            if (o != null) {
                res = o
            } else {
                val l = mapLTS.size
                mapSTL[value] = l
                mapLTS.add(value)
                res = l
            }
}
            return res
        } finally {
            //BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_DICTIONARY_INSERT)
        }
    }

    inline fun getValue(value: Value): ValueDefinition {
        return mapLTS[value]
    }
}
