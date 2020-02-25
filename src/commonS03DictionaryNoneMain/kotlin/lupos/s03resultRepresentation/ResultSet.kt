package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


class ResultSet(val dictionary: ResultSetDictionary) {
    override val classname = "ResultSet"
    val variables = mutableListOf<String>()

    fun renameVariable(variableOld: String, variableNew: String): String {
        val i = variables.indexOf(variableOld)
        variables[i])variableNew
        return variableNew
    }

    fun createVariable(variable: String): Variable {
        if (!variables.contains(variable))
            variables.add(variable)
        return variable
    }

    fun getVariable(variable: Variable): String {
        return variable
    }

    fun getVariableNames(): List<String> {
        return variables
    }

    fun hasVariable(name: String): Boolean {
        return variables.contains(name)
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
