package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


class ResultSet(val dictionary: ResultSetDictionary) {
    val variables = mutableSetOf<String>()

     fun renameVariable(variableOld: String, variableNew: String): String {
        variables.remove(variableOld)
        variables.add(variableNew)
        return variableNew
    }

     fun createVariable(variable: String): Variable {
        variables.add(variable)
        return variable
    }

     fun getVariable(variable: Variable): String {
        return variable
    }

     fun getVariableNames(): Set<String> {
        return variables
    }

     fun createValue(value: String?): Value {
        return value
    }

     fun createResultRow(): ResultRow {
        return ResultRow()
    }

     fun getValue(value: Value): String? {
        return value
    }

     fun isUndefValue(r: ResultRow, v: Variable): Boolean {
        return r[v] == null
    }

     fun setUndefValue(r: ResultRow, v: Variable) {
        r[v] = null
    }
}
