package lupos.s4resultRepresentation

actual class ResultSet {
    actual constructor()

    actual fun createVariable(variable: String): Variable {
        return variable
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
