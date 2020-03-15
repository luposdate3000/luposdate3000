package lupos.s03resultRepresentation

class ResultChunk(val resultSet: ResultSet) : Iterator<ResultRow> {
    val columns = resultSet.getVariableNames().size
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    var pos = 0
    var size = 0

    fun canAppend() = size < ResultVektor.capacity
    override fun hasNext() = pos < size
    fun append(row: ResultRow) {
        for (i in 0 until columns)
            data[i].data[size] = row.values[i]
        size++
    }

    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        for (i in 0 until columns)
            row.values[i] = data[i].data[pos]
        pos++
        return row
    }

    fun setColumn(variable: Variable, col: ResultVektor) {
        data[variable.toInt()] = col
    }

    fun getColumn(variable: Variable) = data[variable.toInt()]
}

class ResultVektor(undefValue: Value) {
    companion object {
        val capacity = 6
    }

    val data = Array<Value>(capacity) { undefValue }
}
