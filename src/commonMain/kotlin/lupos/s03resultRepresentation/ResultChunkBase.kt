package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


open class ResultChunkBase(val resultSet: ResultSet, val columns: Int) : Iterator<ResultRow> {
    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    open fun availableWrite(): Int {
        var res = data[0].availableWrite()
        for (i in 1 until columns) {
            val tmp = data[i].availableWrite()
            if (res > tmp)
                res = tmp
        }
        return res
    }

    open fun availableRead(): Int = data[0].availableRead()
    fun append(row: ResultRow, count: Int = 1) {
        for (i in 0 until columns)
            data[i].append(row.values[i], count)
    }

    fun append(values: Array<Value>, count: Int = 1) {
        for (i in 0 until columns)
            data[i].append(values[i], count)
    }

    fun backupPosition() {
        for (i in 0 until columns)
            data[i].backupPosition()
    }

    fun canAppend() = availableWrite() > 0
    fun current(columns: Array<Variable>) = Array(columns.size) { data[columns[it].toInt()].current() }
    fun current() = Array(columns) { data[it].current() }
    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunkBase, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(chunkFrom: ResultChunkBase, count: Int) {
        for (c in 0 until columns) {
            val colTo = data[c]
            val colFrom = chunkFrom.data[c]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            colTo.append(valFrom, count)
        }
    }

    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val valFrom = arrFrom[columnsFrom[c].toInt()]
            if (valFrom != resultSet.dictionary.undefValue)
                colTo.append(valFrom, count)
            else
                colTo.append(arrFromAlternative[c], count)
        }
    }


    fun getColumn(variable: Variable) = data[variable.toInt()]

    override fun hasNext() = data[0].hasNext()
    override fun next(): ResultRow {
        val row = resultSet.createResultRow()
        for (i in 0 until columns)
            row.values[i] = data[i].next()
        return row
    }

    open fun nextArr() = Array(columns) { data[it].next() }

    fun setColumn(variable: Variable, col: ResultVektor) {
        data[variable.toInt()] = col
    }

    fun restorePosition() {
        for (i in 0 until columns)
            data[i].restorePosition()
    }

    fun sameElements(columns: Array<Variable>): Int {
        var res = availableRead()
        for (i in columns) {
            val t = data[i.toInt()].sameElements()
            if (t < res)
                res = t
        }
        return res
    }

    fun sameElements(): Int {
        var res = availableRead()
        for (i in 0 until columns) {
            val t = data[i].sameElements()
            if (t < res)
                res = t
        }
        return res
    }

    open fun skipPos(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].skipPos(count)
    }

    open fun skipSize(columns: Array<Variable>, count: Int) {
        for (c in 0 until columns.size)
            data[columns[c].toInt()].skipSize(count)
    }

    open fun skipPos(count: Int) {
        for (c in 0 until columns)
            data[c].skipPos(count)
    }

    open fun skipSize(count: Int) {
        for (c in 0 until columns)
            data[c].skipSize(count)
    }

    override fun toString(): String {
        val res = StringBuilder()
        res.append("" + availableRead() + "r" + availableWrite() + "w")
        for (c in 0 until columns)
            res.append("(${resultSet.getVariableNames()[c]},${data[c].posIndex},${data[c].sizeIndex},${data[c].posAbsolute},${data[c].sizeAbsolute},${data[c].posIndexLocal} ${data[c].availableRead()} ${data[c].availableWrite()}), ")
        res.append("\n")
        if (columns > 0)
            for (r in 0 until ResultVektor.capacity) {
                var flag = false
                for (c in 0 until columns)
                    if (r >= data[c].posIndex && r <= data[c].sizeIndex && data[c].data[r].count > 0) {
                        res.append(data[c].data[r].value)
                        if (r == data[c].posIndex && data[c].posIndexLocal != 0)
                            res.append("(${data[c].data[r].count - data[c].posIndexLocal})<${data[c].data[r].count}>")
                        else
                            res.append("(${data[c].data[r].count})")
                        res.append(", ")
                    } else {
                        res.append("<${data[c].data[r].count}>, ")
                        flag = true
                    }
                res.append("\n")
                if (flag && r > 4)
                    break
            }
        return res.toString()
    }
}
