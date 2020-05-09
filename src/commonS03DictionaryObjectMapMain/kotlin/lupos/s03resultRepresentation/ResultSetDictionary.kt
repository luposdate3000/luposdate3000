package lupos.s03resultRepresentation
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeMutableList
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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

    var bNodeCounter = 0
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

    inline fun createDecimal(value: Double): Value {
        return createValue(ValueDecimal(value))
    }

    inline fun createInteger(value: Int): Value {
        return createValue(ValueInteger(value))
    }

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

    inline fun valueToGlobal(value: Value): Value {
        if (value >= 0) {
            return value
        } else {
            return nodeGlobalDictionary.createValue(getValue(value))
        }
    }

    fun safeToFolder(folderName: String) {
        File(folderName + "/dictionary.txt").printWriter { out ->
            var idx = 0
            for (line in mapLTS) {
                if (idx > 3) {
                    out.println(line.valueToString())
                }
                idx++
            }
        }
    }

    fun loadFromFolder(folderName: String) {
        File(folderName + "/dictionary.txt").forEachLine {
            createValue(ValueDefinition(it))
        }
    }
}
