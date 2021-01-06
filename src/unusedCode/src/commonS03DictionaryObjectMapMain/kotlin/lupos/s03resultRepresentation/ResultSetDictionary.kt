package lupos.s03resultRepresentation
import lupos.s00misc.File
import lupos.s00misc.MyMapIntInt
import lupos.s01io.BufferManager
import kotlin.jvm.JvmField
val nodeGlobalDictionary = ResultSetDictionary(true)
public class ResultSetDictionary(val global: Boolean = false) {
    companion object {
        const val booleanTrueValue = 0 // required by truth-tables
        @JvmField
        val booleanTrueValue2 = ValueBoolean(true)
        const val booleanFalseValue = 1 // required by truth-tables
        @JvmField
        val booleanFalseValue2 = ValueBoolean(false)
        const val errorValue = 2 // required by truth-tables
        @JvmField
        val errorValue2 = ValueError()
        const val undefValue = 3
        @JvmField
        val undefValue2 = ValueUndef()
        public fun debug() {
        }
        public fun isGlobalBNode(value: Int): Boolean {
            if (value >= 0) {
                return false
            }
            return nodeGlobalDictionary.getValue(value) is ValueBnode
        }
    }
    public fun isLocalBNode(value: Int): Boolean {
        if (value < 0) {
            return false
        }
        return getValue(value) is ValueBnode
    }
    val bnodeMapToGlobal = MyMapIntInt()
    var mapSTL = mutableMapOf<ValueDefinition, Int>(errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue, undefValue2 to undefValue)
    var mapLTS = mutableMapOf<Int, ValueDefinition>(errorValue to errorValue2, booleanTrueValue to booleanTrueValue2, booleanFalseValue to booleanFalseValue2, undefValue to undefValue2)
    var bNodeCounter = 0
    public fun clear() {
        mapSTL = mutableMapOf<ValueDefinition, Int>(errorValue2 to errorValue, booleanTrueValue2 to booleanTrueValue, booleanFalseValue2 to booleanFalseValue, undefValue2 to undefValue)
        mapLTS = mutableMapOf<Int, ValueDefinition>(errorValue to errorValue2, booleanTrueValue to booleanTrueValue2, booleanFalseValue to booleanFalseValue2, undefValue to undefValue2)
        bNodeCounter = 0
        bnodeMapToGlobal.clear()
    }
    internal inline fun toBooleanOrError(value: Int): Int {
        var res: Int = errorValue
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
    internal inline fun createNewBNode(): Int {
        return createValue(ValueBnode("" + bNodeCounter++))
    }
    internal inline fun createIri(iri: String): Int {
        return createValue("<" + iri + ">")
    }
    internal inline fun createValue(value: String?): Int {
        return createValue(ValueDefinition(value))
    }
    internal inline fun createTyped(content: String, type: String): Int {
        return createValue(ValueDefinition("\"$content\"^^<$type>"))
    }
    internal inline fun createDouble(value: Double): Int {
        return createValue(ValueDouble(value))
    }
    internal inline fun createFloat(value: Double): Int {
        return createValue(ValueFloat(value))
    }
    internal inline fun createDecimal(value: Double): Int {
        return createValue(ValueDecimal(value))
    }
    internal inline fun createInteger(value: Int): Int {
        return createValue(ValueInteger(value))
    }
    internal inline fun checkValue(value: ValueDefinition): Int {
        var res: Int
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
    internal inline fun createValue(value: ValueDefinition): Int {
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
    internal inline fun getValue(value: Int): ValueDefinition {
        if (value < 0) {
            return mapLTS[-value]!!
        } else {
            return nodeGlobalDictionary.mapLTS[value]!!
        }
/*Coverage Unreachable*/
    }
    internal inline fun valueToGlobal(value: Int): Int {
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
    public fun safeToFolder() {
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
    public fun loadFromFolder() {
        File(BufferManager.bufferPrefix + "dictionary.txt").forEachLine {
            createValue(ValueDefinition(it))
        }
    }
}
