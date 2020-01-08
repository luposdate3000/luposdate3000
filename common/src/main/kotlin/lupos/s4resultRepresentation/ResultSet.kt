package lupos.s4resultRepresentation

expect class ResultSet {
    constructor()

    fun createVariable(variable: String): Variable
    fun getVariableNames(): Set<String>

    fun createValue(value: String): Value
    fun getValue(value: Value): String

    fun createResultRow(): ResultRow
    fun releaseResultRow(row: ResultRow)
}
