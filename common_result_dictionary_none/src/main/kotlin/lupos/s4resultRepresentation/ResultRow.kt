package lupos.s4resultRepresentation

actual typealias Variable = String

actual typealias Value = String

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
}

actual inline class ResultRow(val values: Any) {
    actual operator fun set(name: Variable, value: Value) {
        (values as MutableMap<Variable, Value>)[name] = value
    }

    actual operator fun get(name: Variable): Value {
        return (values as MutableMap<Variable, Value>)[name]!!
    }
}