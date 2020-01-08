package lupos.s4resultRepresentation

actual class ResultSet {
    val variables=mutableSetOf<String>()
    actual constructor()

    actual fun createVariable(variable: String): Variable {
	variables.add(variable)
        return variable
    }

    actual fun getVariableNames():Set<String>{
	return variables
    }

    actual fun createValue(value: String): Value {
        return value
    }

    actual fun createResultRow(): ResultRow {
        return ResultRow(mutableMapOf<Variable, Value>())
    }

    actual fun releaseResultRow(row: ResultRow) {
    }

    actual fun getValue(value: Value): String {
        return value
    }
}
