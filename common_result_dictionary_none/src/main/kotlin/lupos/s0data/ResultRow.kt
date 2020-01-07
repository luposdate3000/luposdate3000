package lupos.s0data

actual typealias Variable = String

actual typealias Value = String

actual class Query {
    actual constructor()

    actual fun getVariable(variable: String): Variable {
        return variable
    }

    actual fun getValue(value: String): Value {
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