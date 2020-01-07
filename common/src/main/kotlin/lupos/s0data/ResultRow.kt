package lupos.s0data

expect class Variable

expect class Value

expect class Query {
    constructor()

    fun getVariable(variable: String): Variable
    fun getValue(value: String): Value
    fun createResultRow(): ResultRow
    fun releaseResultRow(row: ResultRow)
}

expect inline class ResultRow(val values: Any) {
    operator fun set(name: Variable, value: Value)
    operator fun get(name: Variable): Value
}