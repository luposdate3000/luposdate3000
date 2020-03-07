package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s04logicalOperators.Query


class ResultSet(@JvmField val dictionary: ResultSetDictionary) {
    @JvmField
    val variablesSTL = mutableMapOf<String, Variable>()
    @JvmField
    val variablesLTS = mutableListOf<String>()
    @JvmField
    val mutex = CoroutinesHelper.createLock()

    fun renameVariable(variableOld: String, variableNew: String): Variable {
        var res: Variable? = null
        CoroutinesHelper.runBlockWithLock(mutex, {
            val l = variablesSTL[variableOld]!!
            variablesSTL.remove(variableOld)
            variablesSTL[variableNew] = l
            variablesLTS[l.toInt()] = variableNew
            res = l
        })
        return res!!
    }

    fun createVariable(variable: String): Variable {
        var res: Variable? = null
        CoroutinesHelper.runBlockWithLock(mutex, {
            val o = variablesSTL[variable]
            if (o != null)
                res = o
            else {
                val l = 0L + variablesLTS.size
                variablesSTL[variable] = l
                variablesLTS.add(variable)
                res = l
            }
        })
        return res!!
    }

    fun getVariable(variable: Variable) = variablesLTS[variable.toInt()]

    fun hasVariable(name: String) = variablesLTS.contains(name)

    fun getVariableNames() = variablesLTS

    fun createValue(value: String?): Value {
        if (value == null)
            return dictionary.undefValue
        return dictionary.createValue(value)
    }

    fun createResultRow() = ResultRow(variablesLTS.size, dictionary.undefValue)

    fun getValue(value: Value) = dictionary.getValue(value)

    fun isUndefValue(r: ResultRow, v: Variable) = r[v] == dictionary.undefValue

    fun setUndefValue(r: ResultRow, v: Variable) {
        r[v] = dictionary.undefValue
    }
}
