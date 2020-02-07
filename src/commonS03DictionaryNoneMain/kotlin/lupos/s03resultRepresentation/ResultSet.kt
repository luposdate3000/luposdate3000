package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


class ResultSet(val dictionary: ResultSetDictionary) {
    val variables = mutableSetOf<String>()

    inline fun renameVariable(variableOld: String, variableNew: String): String {
        variables.remove(variableOld)
        variables.add(variableNew)
        return variableNew
    }

    inline fun createVariable(variable: String): Variable {
        variables.add(variable)
        return variable
    }

    inline fun getVariable(variable: Variable): String {
        return variable
    }

    inline fun getVariableNames(): Set<String> {
        return variables
    }

    inline fun createValue(value: String?): Value {
        return value
    }

    inline fun createResultRow(): ResultRow {
        return ResultRow()
    }

    inline fun getValue(value: Value): String? {
        return value
    }

    inline fun isUndefValue(r: ResultRow, v: Variable): Boolean {
        return r[v] == null
    }

    inline fun setUndefValue(r: ResultRow, v: Variable) {
        r[v] = null
    }
}
