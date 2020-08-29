package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s01io.BufferManager
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.MyMapIntInt
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueUndef

val nodeGlobalDictionary = ResultSetDictionary(true)

class ResultSetDictionary(val global: Boolean = false) {
    companion object {
        const val booleanTrueValue = 0//required by truth-tables

        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        const val booleanFalseValue = 1//required by truth-tables

        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        const val errorValue = 2//required by truth-tables

        @JvmField
        val errorValue2 = ValueError()
        const val undefValue = 3

        @JvmField
        val undefValue2 = ValueUndef()
        fun debug() {
        }

        fun isGlobalBNode(value: Value): Boolean {
            if (value >= 0) {
                return false
            }
            return nodeGlobalDictionary.getValue(value) is ValueBnode
        }
    }

    fun isLocalBNode(value: Value): Boolean {
        if (value < 0) {
            return false
        }
        return getValue(value) is ValueBnode
    }

    val bnodeMapToGlobal = MyMapIntInt()
    var mapSTL = mutableMapOf<ValueDefinition, Value>(errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue, undefValue2 to undefValue)
    var mapLTS = mutableMapOf<Value, ValueDefinition>(errorValue to errorValue2, booleanTrueValue to booleanTrueValue2, booleanFalseValue to booleanFalseValue2, undefValue to undefValue2)
    var bNodeCounter = 0
    fun clear() {
        mapSTL = mutableMapOf<ValueDefinition, Value>(errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue, undefValue2 to undefValue)
        mapLTS = mutableMapOf<Value, ValueDefinition>(errorValue to errorValue2, booleanTrueValue to booleanTrueValue2, booleanFalseValue to booleanFalseValue2, undefValue to undefValue2)
        bNodeCounter = 0
        bnodeMapToGlobal.clear()
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
                SanityCheck.println({ "TODO exception 40" })
                e.printStackTrace()
            }
        }
        return res
    }

    inline fun createNewBNode(): Value {
        return createValue(ValueBnode("" + bNodeCounter++))
    }

    inline fun createIri(iri: String): Value {
        return createValue("<" + iri + ">")
    }

    inline fun createValue(value: String?): Value {
        return createValue(ValueDefinition(value))
    }

    inline fun createTyped(content: String, type: String): Value {
        return createValue(ValueDefinition("\"$content\"^^<$type>"))
    }

    inline fun createDouble(value: Double): Value {
        return createValue(ValueDouble(value))
    }

    inline fun createFloat(value: Double): Value {
        return createValue(ValueFloat(value))
    }

    inline fun createDecimal(value: Double): Value {
        return createValue(ValueDecimal(value))
    }

    inline fun createInteger(value: Int): Value {
        return createValue(ValueInteger(value))
    }

    inline fun checkValue(value: ValueDefinition): Value {
        var res: Value
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
                    if (res != null) {
                        res = -res
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
            mapLTS[l] = value
            if (global) {
                res = l
            } else {
                res = -l
            }
        }
        return res
    }

    inline fun getValue(value: Value): ValueDefinition {
        if (value < 0) {
            return mapLTS[-value]!!
        } else {
            return nodeGlobalDictionary.mapLTS[value]!!
        }
/*Coverage Unreachable*/
    }

    inline fun valueToGlobal(value: Value): Value {
        if (value >= 0) {
            return value
        } else {
            if (isLocalBNode(value)) {
                return bnodeMapToGlobal.getOrCreate(value, { nodeGlobalDictionary.createNewBNode() })
            }
            return nodeGlobalDictionary.createValue(getValue(value))
        }
/*Coverage Unreachable*/
    }

    fun safeToFolder() {
        File(BufferManager.bufferPrefix + "dictionary.txt").printWriter { out ->
            var idx = 0
            for ((k, line) in mapLTS) {
                if (idx > 3) {
                    out.println(line.valueToString())
                }
                idx++
            }
        }
    }

    fun loadFromFolder() {
        File(BufferManager.bufferPrefix + "dictionary.txt").forEachLine {
            createValue(ValueDefinition(it))
        }
    }
}
