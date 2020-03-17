package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class ResultChunkNoColumns(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    val emptyArray = arrayOf<Value>()
    var posField = 0
    var sizeField = 0
    override var pos: Int
        get() = posField
        set(value) {
            posField = value
        }
    override var size: Int
        get() = sizeField
        set(value) {
            sizeField = value
        }

    override fun next(): ResultRow {
        posField++
        return resultSet.createResultRow()
    }

    override fun availableSpace() = ResultVektor.capacity - sizeField
    override fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        posField += count
    }

    override fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        posField += count
    }

    override fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        posField += count
    }

    override fun skipPos(columns: Array<Variable>, count: Int) {
        posField += count
    }

    override fun skipSize(columns: Array<Variable>, count: Int) {
        posField += count
    }

    override fun nextArr(): Array<Value> {
        posField++
        return emptyArray
    }
}

open class ResultChunk(val resultSet: ResultSet, val columns: Int) : Iterator<ResultRow> {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            val columns = resultSet.getVariableNames().size
            if (columns == 0)
                return ResultChunkNoColumns(resultSet, columns)
            return ResultChunk(resultSet, columns)
        }
    }

    //die eigentlichen Daten als Array von Spalten
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    //reference for retrieving the current pos
    //dies ist die aktuelle Zeile innerhalb von diesem Spaltenvektor (nur beim lesen verändert)
    open var pos: Int
        get() = data[0].pos
        set(value) {
            for (i in 0 until data.size)
                data[i].pos = value
        }
    //dies ist die aktuelle Zeile innerhalb von diesem Spaltenvektor (nur beim schreiben verändert)
    open var size: Int
        get() = data[0].size
        set(value) {
            for (i in 0 until data.size)
                data[i].size = value
        }

    open fun availableSpace() = data[0].availableSpace()
    fun canAppend() = availableSpace() > 0

    //backwards compatibility
    fun append(row: ResultRow) {
        for (i in 0 until columns)
            data[i].data[size] = row.values[i]
        size++
    }

    override fun hasNext() = pos < size
    //backwards compatibility
    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        for (i in 0 until columns)
            row.values[i] = data[i].next()
        return row
    }

    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    fun current() = Array(columns) { data[it].current() }
    open fun nextArr() = Array(columns) { data[it].next() }

    fun setColumn(variable: Variable, col: ResultVektor) {
        data[variable.toInt()] = col
    }

    fun getColumn(variable: Variable) = data[variable.toInt()]

    //dies hier wird durch kompression später deutlich verbessert
    fun sameElements(columns: Array<Variable>): Int {
        var res = size - pos
        for (i in columns) {
            val t = data[i.toInt()].sameElements()
            if (t < res)
                res = t
        }
        return res
    }

    open fun skipPos(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].pos += count
    }

    open fun skipSize(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].size += count
    }

    //dies hier wird durch kompression später deutlich verbessert
    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    //dies hier wird durch kompression später deutlich verbessert
    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.copy(valFrom, count)
        }
    }

    //dies hier wird durch kompression später deutlich verbessert
    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            if (valFrom != resultSet.dictionary.undefValue)
                colTo.copy(valFrom, count)
            else
                colTo.copy(arrFromAlternative[c], count)
        }
    }

    override fun toString(): String {
        val res = StringBuilder()
        for (c in 0 until columns)
            res.append("(${resultSet.getVariableNames()[c]},${data[c].pos},${data[c].size}), ")
        res.append("\n")
        if (columns > 0)
            for (r in 0 until ResultVektor.capacity) {
                for (c in 0 until columns)
                    if (r >= data[c].pos && r < data[c].size)
                        res.append("${data[c].data[r]}, ")
                    else
                        res.append("-, ")
                res.append("\n")
            }
        return res.toString()
    }
}

//eine einzelne Spalte von Werten
class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        val capacity = 6
    }

    var pos = 0
    var size = 0

    //dieses array wird durch die seiten-basierte implementierung ausgetauscht
    val data = Array<Value>(capacity) { undefValue }

    fun current(): Value = data[pos]
    override fun next(): Value = data[pos++]
    override fun hasNext() = pos < size
    fun availableSpace() = capacity - size
    fun canAppend() = availableSpace() > 0

    //dies hier wird durch kompression später deutlich verbessert
    fun append(value: Value) {
        data[size++] = value
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun sameElements(): Int {
        var res = 1
        while (pos + res < size && data[pos + res] == data[pos])
            res++
        return res
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(from: ResultVektor, count: Int) {
        for (i in 0 until count)
            append(from.next())
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(from: Value, count: Int) {
        for (i in 0 until count)
            append(from)
    }
}
