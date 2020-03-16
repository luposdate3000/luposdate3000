package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


//ein Block von mehreren Zeilen
class ResultChunk(val resultSet: ResultSet) : Iterator<ResultRow> {

    //anzahl der Spalten
    val columns = resultSet.getVariableNames().size

    //die eigentlichen Daten als Array von Spalten
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    //reference for retrieving the current pos
    //dies ist die aktuelle Zeile innerhalb von diesem Spaltenvektor (nur beim lesen verändert)
    var pos: Int
        get() = data[0].pos
        set(value) {
            for (i in 0 until data.size)
                data[i].pos = value
        }
    //dies ist die aktuelle Zeile innerhalb von diesem Spaltenvektor (nur beim schreiben verändert)
    var size: Int
        get() = data[0].size
        set(value) {
            for (i in 0 until data.size)
                data[i].size = value
        }

    fun availableSpace() = data[0].availableSpace()
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

    //das hier spart das lesen von vielen unnötigen zeilen
    fun skip(count: Int) {
        pos += count
    }

    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    fun current() = Array(columns) { data[it].current() }
    fun nextArr() = Array(columns) { data[it].next() }

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

    fun skipPos(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].pos += count
    }

    fun skipSize(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].size += count
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
//            println("cpycol ${columnsTo[c].toInt()} ${columnsFrom[c].toInt()}")
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
//            println("cpyarr ${columnsTo[c].toInt()} ${columnsFrom[c].toInt()}")
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.copy(valFrom, count)
        }
    }

    override fun toString(): String {
        val res = StringBuilder()
        for (c in 0 until columns)
            res.append("(${resultSet.getVariableNames()[c]},${data[c].pos},${data[c].size}), ")
        res.append("\n")
        for (r in pos until size) {
            for (c in 0 until columns)
                res.append("${data[c].data[r]}, ")
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
        for (i in pos + 1 until size)
            if (data[i] == data[pos])
                res++
        return res
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(from: ResultVektor, count: Int) {
//        println("cp $count $pos $size ${from.pos} ${from.size}")
        for (i in 0 until count)
            append(from.next())
    }

    //dies hier wird durch kompression später deutlich verbessert
    fun copy(from: Value, count: Int) {
//        println("cp $count $pos $size")
        for (i in 0 until count)
            append(from)
    }
}
