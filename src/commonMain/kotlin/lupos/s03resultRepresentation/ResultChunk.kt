package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw

class ResultChunk(val resultSet: ResultSet) : Iterator<ResultRow> {
    val columns = resultSet.getVariableNames().size
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }

    var pos: Int
        get() = data[0].pos
        set(value) {
            for (i in 0 until data.size) 
                data[i].pos = value
        }
    var size: Int
        get() = data[0].size
        set(value) {
            for (i in 0 until data.size) 
                data[i].size = value
        }

    fun availableSpace() = data[0].availableSpace()
    fun canAppend() = availableSpace() > 0
    fun append(row: ResultRow) {
        for (i in 0 until columns)
            data[i].data[size] = row.values[i]
        size++
    }

    override fun hasNext() = pos < size
    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        for (i in 0 until columns)
            row.values[i] = data[i].next()
        return row
    }

fun skip(count:Int){
pos+=count
}
    fun current(columns: Array<Variable>) = Array(columns.size) { data[it].current() }
    fun current() = Array(columns) { data[it].current() }

    fun setColumn(variable: Variable, col: ResultVektor) {
        data[variable.toInt()] = col
    }

    fun getColumn(variable: Variable) = data[variable.toInt()]
    fun sameElements(columns: Array<Variable>): Int {
        var res = size - pos
        for (i in columns) {
            val t = data[i.toInt()].sameElements()
            if (t < res)
                res = t
        }
        return res
    }

    fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }
    fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.copy(valFrom, count)
        }
    }

}

class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        val capacity = 6
    }

    var pos = 0
    var size = 0

    val data = Array<Value>(capacity) { undefValue }

     fun current(): Value = data[pos]
    override fun next(): Value = data[pos++]
    override fun hasNext() = pos < size
    fun availableSpace() = capacity - size
    fun canAppend() = availableSpace() > 0
    fun append(value: Value) {
        data[size++] = value
}
        fun sameElements(): Int {
            var res = 1
            for (i in pos + 1 until size)
                if (data[i] == data[pos])
                    res++
            return res
        }

    fun copy(from: ResultVektor, count: Int) {
        for (i in 0 until count)
            append(from.next())
    }
    fun copy(from: Value, count: Int) {
        for (i in 0 until count)
            append(from)
    }
}
