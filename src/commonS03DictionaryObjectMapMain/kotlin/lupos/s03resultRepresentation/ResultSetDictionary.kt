package lupos.s03resultRepresentation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueUndef
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
fun debug(){
Coverage.funStart(14185)
}
    }
    var mapSTL = mutableMapOf<ValueDefinition, Value>(errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue, undefValue2 to undefValue)
    var mapLTS = mutableMapOf<Value, ValueDefinition>(errorValue to errorValue2,booleanTrueValue to booleanTrueValue2,booleanFalseValue to booleanFalseValue2,undefValue to undefValue2)
    inline fun toBooleanOrError(value: Value): Value {
Coverage.funStart(14186)
        var res: Value = errorValue
Coverage.statementStart(14187)
        if (value < undefValue && value >= 0) {
Coverage.ifStart(14188)
            res = value
Coverage.statementStart(14189)
        } else {
Coverage.ifStart(14190)
            try {
Coverage.statementStart(14191)
                if (getValue(value).toBoolean()) {
Coverage.ifStart(14192)
                    res = booleanTrueValue
Coverage.statementStart(14193)
                } else {
Coverage.ifStart(14194)
                    res = booleanFalseValue
Coverage.statementStart(14195)
                }
Coverage.statementStart(14196)
            } catch (e: Throwable) {
Coverage.statementStart(14197)
            }
Coverage.statementStart(14198)
        }
Coverage.statementStart(14199)
        return res
    }
    var bNodeCounter = 0
    inline fun createNewBNode(): Value {
Coverage.funStart(14200)
        return createValue(ValueBnode("" + bNodeCounter++))
    }
    inline fun createIri(iri: String): Value {
Coverage.funStart(14201)
        return createValue("<" + iri + ">")
    }
    inline fun createValue(value: String?): Value {
Coverage.funStart(14202)
        return createValue(ValueDefinition(value))
    }
    inline fun createTyped(content: String, type: String): Value {
Coverage.funStart(14203)
        return createValue(ValueDefinition("\"$content\"^^<$type>"))
    }
    inline fun createDouble(value: Double): Value {
Coverage.funStart(14204)
        return createValue(ValueDouble(value))
    }
    inline fun createDecimal(value: Double): Value {
Coverage.funStart(14205)
        return createValue(ValueDecimal(value))
    }
    inline fun createInteger(value: Int): Value {
Coverage.funStart(14206)
        return createValue(ValueInteger(value))
    }
    inline fun checkValue(value: ValueDefinition): Value? {
Coverage.funStart(14207)
        var res: Value?
Coverage.statementStart(14208)
        if (value is ValueUndef) {
Coverage.ifStart(14209)
            res = undefValue
Coverage.statementStart(14210)
        } else if (value is ValueError) {
Coverage.ifStart(14211)
            res = errorValue
Coverage.statementStart(14212)
        } else {
Coverage.ifStart(14213)
            if (global) {
Coverage.ifStart(14214)
                res = mapSTL[value]
Coverage.statementStart(14215)
            } else {
Coverage.ifStart(14216)
                res = nodeGlobalDictionary.mapSTL[value]
Coverage.statementStart(14217)
                if (res == null) {
Coverage.ifStart(14218)
                    res = mapSTL[value]
Coverage.statementStart(14219)
                    if (res != null) {
Coverage.ifStart(14220)
                        res = -res
Coverage.statementStart(14221)
                    }
Coverage.statementStart(14222)
                }
Coverage.statementStart(14223)
            }
Coverage.statementStart(14224)
        }
Coverage.statementStart(14225)
        return res
    }
    inline fun createValue(value: ValueDefinition): Value {
Coverage.funStart(14226)
        var res = checkValue(value)
Coverage.statementStart(14227)
        if (res == null) {
Coverage.ifStart(14228)
            val l = mapLTS.size
Coverage.statementStart(14229)
            mapSTL[value] = l
Coverage.statementStart(14230)
            mapLTS[l]=value
Coverage.statementStart(14231)
            if (global) {
Coverage.ifStart(14232)
                res = l
Coverage.statementStart(14233)
            } else {
Coverage.ifStart(14234)
                res = -l
Coverage.statementStart(14235)
            }
Coverage.statementStart(14236)
        }
Coverage.statementStart(14237)
        return res!!
    }
    inline fun getValue(value: Value): ValueDefinition {
Coverage.funStart(14238)
        if (value < 0) {
Coverage.ifStart(14239)
            return mapLTS[-value]!!
        } else {
Coverage.statementStart(14240)
            return nodeGlobalDictionary.mapLTS[value]!!
        }
Coverage.statementStart(14241)
    }
    inline fun valueToGlobal(value: Value): Value {
Coverage.funStart(14242)
        if (value >= 0) {
Coverage.ifStart(14243)
            return value
        } else {
Coverage.statementStart(14244)
            return nodeGlobalDictionary.createValue(getValue(value))
        }
Coverage.statementStart(14245)
    }
    fun safeToFolder(folderName: String) {
Coverage.funStart(14246)
        File(folderName + "/dictionary.txt").printWriter { out ->
Coverage.statementStart(14247)
            var idx = 0
Coverage.statementStart(14248)
            for ((k,line) in mapLTS) {
Coverage.forLoopStart(14249)
                if (idx > 3) {
Coverage.ifStart(14250)
                    out.println(line.valueToString())
Coverage.statementStart(14251)
                }
Coverage.statementStart(14252)
                idx++
Coverage.statementStart(14253)
            }
Coverage.statementStart(14254)
        }
Coverage.statementStart(14255)
    }
    fun loadFromFolder(folderName: String) {
Coverage.funStart(14256)
        File(folderName + "/dictionary.txt").forEachLine {
Coverage.statementStart(14257)
            createValue(ValueDefinition(it))
Coverage.statementStart(14258)
        }
Coverage.statementStart(14259)
    }
}
