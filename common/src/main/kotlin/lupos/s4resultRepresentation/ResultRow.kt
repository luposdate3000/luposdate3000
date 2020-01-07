package lupos.s4resultRepresentation

expect class Variable

expect class Value

expect class ResultSet {
    constructor()

    fun createVariable(variable: String): Variable

    fun createValue(value: String): Value
    fun getValue(value: Value): String

    fun createResultRow(): ResultRow
    fun releaseResultRow(row: ResultRow)
}

expect inline class ResultRow(val values: Any) {
    operator fun set(name: Variable, value: Value)
    operator fun get(name: Variable): Value
}

interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}