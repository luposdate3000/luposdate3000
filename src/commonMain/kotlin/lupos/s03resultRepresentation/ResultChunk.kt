package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


class ResultChunkNoColumns(resultSet: ResultSet, columns: Int) : ResultChunk(resultSet, columns) {
    val emptyArray = arrayOf<Value>()
    var posField = 0
    var sizeField = 0

    override fun hasNext() = posField < sizeField
    override fun next(): ResultRow {
        posField++
        return resultSet.createResultRow()
    }

    override fun availableWrite() = ResultVektor.capacity - sizeField
    override fun availableRead() = sizeField - posField
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

    override fun skipPos(count: Int) {
        posField += count
    }

    override fun skipSize(count: Int) {
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

    open fun availableWrite(): Int {
        var res = data[0].availableWrite()
        for (i in 1 until columns) {
            val tmp = data[i].availableWrite()
            if (res > tmp)
                res = tmp
        }
        return res
    }

    open fun availableRead(): Int {
        return data[0].availableRead()
    }

    fun canAppend() = availableWrite() > 0

    fun append(row: ResultRow) {
        for (i in 0 until columns)
            data[i].append(row.values[i])
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
        res.append("" + availableRead() + "r" + availableWrite() + "w")
        for (c in 0 until columns)
            res.append("(${resultSet.getVariableNames()[c]},${data[c].posIndex},${data[c].sizeIndex},${data[c].posAbsolute},${data[c].sizeAbsolute},${data[c].posIndexLocal} ${data[c].availableRead()} ${data[c].availableWrite()}), ")
        res.append("\n")
        if (columns > 0)
            for (r in 0 until ResultVektor.capacity) {
                for (c in 0 until columns)
                    if (r >= data[c].posIndex && r <= data[c].sizeIndex && data[c].data[r].count > 0) {
                        res.append(data[c].data[r].value)
                        if (r == data[c].posIndex)
                            res.append("(${data[c].data[r].count - data[c].posIndexLocal})")
                        else
                            res.append("(${data[c].data[r].count})")
                        res.append(", ")
                    } else
                        res.append("-, ")
                res.append("\n")
            }
        return res.toString()
    }

    fun backupPosition() {
        for (i in 0 until columns)
            data[i].backupPosition()
    }

    fun restorePosition() {
        for (i in 0 until columns)
            data[i].restorePosition()
    }

}

class CompressedElement(var count: Int, var value: Value)

class ResultVektor(undefValue: Value) : Iterator<Value> {
    companion object {
        val capacity = 100
    }

    var posAbsolute = 0
    var posIndex = 0
    var posIndexLocal = 0
    var posBackup = Array(3) { 0 }
    var sizeAbsolute = 0
    var sizeIndex = 0

    val data = Array<CompressedElement>(capacity) { CompressedElement(0, undefValue) }

    fun skipPos(count: Int) {
        posAbsolute += count
        if (count >= 0) {
            var i = count
            while (true) {
                val c = data[posIndex].count - posIndexLocal
                if (c < i) {
                    nextElement()
                    i -= c
                } else {
                    posIndexLocal += i
                    break
                }
            }
        } else {
            require(false)
        }
    }

    fun skipSize(count: Int) {
        sizeAbsolute += count
        if (count >= 0)
            data[sizeIndex].count += count
        else {
            var i = count
            while (true) {
                val c = data[sizeIndex].count
                if (c < i) {
                    sizeIndex--
                    i -= c
                } else {
                    data[sizeIndex].count -= i
                    break
                }
            }
        }
    }

    fun backupPosition() {
        posBackup[0] = posAbsolute
        posBackup[1] = posIndex
        posBackup[2] = posIndexLocal
    }

    fun restorePosition() {
        posAbsolute = posBackup[0]
        posIndex = posBackup[1]
        posIndexLocal = posBackup[2]
    }

    fun current(): Value = data[posIndex].value
    override fun next(): Value {
        safeNextElement()
        posIndexLocal++
        posAbsolute++
        return data[posIndex].value
    }

    override fun hasNext() = sizeAbsolute > posAbsolute
    fun availableWrite() = capacity - sizeIndex - 1
    fun availableRead() = sizeAbsolute - posAbsolute
    fun canAppend() = availableWrite() > 0

    fun append(value: Value, count: Int = 1) {
        if (data[sizeIndex].value == value)
            data[sizeIndex].count += count
        else {
            if (sizeAbsolute > 0)
                sizeIndex++
            data[sizeIndex].count = count
            data[sizeIndex].value = value
        }
        sizeAbsolute += count
    }

    fun sameElements(): Int {
        safeNextElement()
        if (posIndex > sizeIndex)
            return 0
        return data[posIndex].count - posIndexLocal
    }

    fun nextElement() {
        posIndex++
        posIndexLocal = 0
    }

    fun safeNextElement() {
        if (posIndexLocal == data[posIndex].count)
            nextElement()
    }

    fun copy(from: ResultVektor, count: Int) {
        var i = count
        if (count > 0)
            from.safeNextElement()
        while (true) {
            val c = from.data[from.posIndex].count - from.posIndexLocal
            if (c < i) {
                append(from.data[from.posIndex].value, c)
                from.nextElement()
                i -= c
            } else {
                append(from.data[from.posIndex].value, i)
                from.posIndexLocal += i
                break
            }
        }
        from.posAbsolute += count
    }

    fun copy(from: Value, count: Int) {
        append(from, count)
    }
}
