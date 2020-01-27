package lupos.s06resultRepresentation
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.Value
import lupos.s4resultRepresentation.Variable

class ResultSet {
    val variables = mutableSetOf<String>()

    constructor()

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

    fun createValue(value: String): Value {
        return value
    }

    fun createResultRow(): ResultRow {
        return ResultRow()
    }

    fun releaseResultRow(row: ResultRow) {
    }

    fun getValue(value: Value): String {
        return value
    }

    fun getUndefValue(): String = ""
}
