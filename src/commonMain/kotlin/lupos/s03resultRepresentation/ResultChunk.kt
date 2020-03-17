package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class ResultChunkNoColumns(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    val emptyArray = arrayOf<Value>()
    var posField = 0
    var sizeField = 0
    override var posx: Int
        get() = posField
        set(value) {
            posField = value
        }
    override var sizex: Int
        get() = sizeField
        set(value) {
            sizeField = value
        }

override fun hasNext()=posField<sizeField
    override fun next(): ResultRow {
        posField++
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField-posField
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
        sizeField += count
    }
    override fun skipPos( count: Int) {
        posField += count
    }

    override fun skipSize( count: Int) {
        sizeField += count
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

    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    open var posx: Int
        get() = data[0].pos
        set(value) {
            for (i in 0 until data.size)
                data[i].pos = value
        }
    open var sizex: Int
        get() = data[0].size
        set(value) {
            for (i in 0 until data.size)
                data[i].size = value
        }

    open fun availableWrite() = data[0].availableWrite()
    open fun availableRead() = data[0].availableRead()
    fun canAppend() = availableWrite() > 0

    fun append(row: ResultRow) {
        for (i in 0 until columns)
            data[i].append( row.values[i])
    }

    override fun hasNext() = data[0].hasNext()
    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        for (i in 0 until columns)
            row.values[i] = data[i].next()
        return row
    }

    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    open fun nextArr() = Array(columns) { data[it].next() }

    fun setColumn(variable: Variable, col: ResultVektor) {
        data[variable.toInt()] = col
    }

    fun getColumn(variable: Variable) = data[variable.toInt()]

    fun sameElements(columns: Array<Variable>): Int {
        var res = availableRead()
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
    open fun skipPos( count: Int) {
        for (c in 0 until columns)
            data[c].pos += count
    }

    open fun skipSize( count: Int) {
        for (c in 0 until columns)
            data[c].size += count
    }

    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.copy(valFrom, count)
        }
    }

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

fun backupPosition(){
for(i in 0 until columns)
data[i].backupPosition()
}
fun restorePosition(){ 
for(i in 0 until columns)
data[i].restorePosition()
}

}

class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        val capacity = 6
    }

    var pos = 0
    var posBackup = 0
    var size = 0

    val data = Array<Value>(capacity) { undefValue }


fun backupPosition(){
posBackup=pos
}
fun restorePosition(){
pos=posBackup
}
    fun current(): Value = data[pos]
    override fun next(): Value = data[pos++]
    override fun hasNext() = pos < size
    fun availableWrite() = capacity - size
    fun availableRead() = size - pos
    fun canAppend() = availableWrite() > 0

    fun append(value: Value) {
        data[size++] = value
    }

    fun sameElements(): Int {
        var res = 1
        while (pos + res < size && data[pos + res] == data[pos])
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
