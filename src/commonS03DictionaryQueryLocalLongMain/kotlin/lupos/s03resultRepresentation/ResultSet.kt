package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


class ResultSet(val dictionary: ResultSetDictionary) {
    val variablesSTL = mutableMapOf<String, Long>()
    val variablesLTS = mutableListOf<String>()

    inline fun renameVariable(variableOld: String, variableNew: String): Long {
        val l = variablesSTL[variableOld]!!
        variablesSTL.remove(variableOld)
        variablesSTL[variableNew] = l
        variablesLTS[l.toInt()] = variableNew
        return l
    }

    inline fun createVariable(variable: String): Variable {
        val o = variablesSTL[variable]
        if (o != null)
            return o
        val l = 0L + variablesLTS.size
        variablesSTL[variable] = l
        variablesLTS.add(variable)
        return l
    }

    inline fun getVariable(variable: Variable): String {
        return variablesLTS[variable.toInt()]
    }

    inline fun getVariableNames(): Set<String> {
        return variablesLTS.toSet()
    }

    inline fun createValue(value: String?): Value {
        if (value == null)
            return dictionary.undefValue
        return dictionary.createValue(value)
    }

    inline fun createResultRow(): ResultRow {
        return ResultRow()
    }

    inline fun getValue(value: Value): String? {
        return dictionary.getValue(value)
    }

    inline fun isUndefValue(r: ResultRow, v: Variable): Boolean {
        return r[v] == dictionary.undefValue
    }

    inline fun setUndefValue(r: ResultRow, v: Variable) {
        r[v] = dictionary.undefValue
    }
}
