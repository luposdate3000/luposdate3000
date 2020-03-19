package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw


open class ResultChunk(val resultSet: ResultSet, val columns: Int) : Iterator<ResultRow> {
    companion object {
        operator fun invoke(resultSet: ResultSet): ResultChunk {
            val columns = resultSet.getVariableNames().size
            if (columns == 0)
                return ResultChunkNoColumns(resultSet, columns)
            return ResultChunk(resultSet, columns)
        }

        fun removeFirst(root: ResultChunk): ResultChunk? {
            if (root.next == root)
                return null
            val res = root.next
            res.prev = root.prev
            root.prev.next = res
            return res
        }

        fun split(root: ResultChunk, count: Int): ResultChunk {
            var other = root
            for (i in 0 until count)
                other = other.next
            val rootLast = other.prev
            val otherLast = root.prev
            root.prev = rootLast
            rootLast.next = root
            otherLast.next = other
            other.prev = otherLast
            return other
        }

        fun append(rootLast: ResultChunk, other: ResultChunk): ResultChunk {
            val root = rootLast.next
            val otherLast = other.prev
            rootLast.next = other
            root.prev = otherLast
            otherLast.next = root
            other.prev = rootLast
            return otherLast
        }

        fun copy(from: ResultChunk, target: ResultChunk, count: Int): ResultChunk {
            val resultSet = from.resultSet
            val columns = from.columns
            var targetLast = target
            var available = targetLast.availableWrite()
            if (available == 0) {
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            var cnt = count
            while (available < cnt) {
                targetLast.copy(from, available)
                cnt -= available
                targetLast = append(targetLast, ResultChunk(resultSet, columns))
                available = ResultVektor.capacity
            }
            targetLast.copy(from, cnt)
            return targetLast
        }

        fun sortHelper(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, a: ResultChunk, b: ResultChunk, target: ResultChunk): ResultChunk {
            var targetLast = target
            loop@ while (a.hasNext() && b.hasNext()) {
                var cmp = 0
                for (i in columnOrder) {
                    val vala = a.data[i.toInt()].current()
                    val valb = b.data[i.toInt()].current()
                    if (vala != valb) {
                        cmp = comparator[i.toInt()].compare(vala, valb)
                        require(cmp != 0)
                        if (cmp < 0) {
                            var count = a.data[i.toInt()].sameElements()
                            targetLast = copy(a, targetLast, count)
                        } else {
                            var count = b.data[i.toInt()].sameElements()
                            targetLast = copy(b, targetLast, count)
                        }
                        continue@loop
                    }
                }
                if (cmp == 0) {
                    val i = columnOrder[columnOrder.size - 1]
                    var countA = a.data[i.toInt()].sameElements()
                    targetLast = copy(a, targetLast, countA)
                    var countB = b.data[i.toInt()].sameElements()
                    targetLast = copy(b, targetLast, countB)
                }
            }
            return targetLast
        }

        fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, chunks: ResultChunk): ResultChunk {
            val chunkCount = chunks.chunkCount()
            when (chunkCount) {
                0 -> return chunks
                1 -> {
                    chunks.sort(comparator, columnOrder)
                    return chunks
                }
                2 -> {
                    val a = split(chunks, 1)
                    val b = chunks
                    val resultSet = a.resultSet
                    val columns = a.columns
                    a.sort(comparator, columnOrder)
                    b.sort(comparator, columnOrder)
                    var res = ResultChunk(resultSet, columns)
                    var resLast = res
                    resLast = sortHelper(comparator, columnOrder, a, b, resLast)
                    val idx = if (a.hasNext()) {
                        var count = a.availableRead()
                        copy(a, resLast, count)
                    } else {
                        var count = b.availableRead()
                        copy(b, resLast, count)
                    }
                    return res
                }
                else -> {
                    val half = chunkCount / 2
                    var a: ResultChunk? = sort(comparator, columnOrder, split(chunks, half))
                    var b: ResultChunk? = sort(comparator, columnOrder, chunks)
                    val resultSet = a!!.resultSet
                    val columns = a!!.columns
                    val res = ResultChunk(resultSet, columns)
                    var resLast = res
                    while (true) {
                        resLast = sortHelper(comparator, columnOrder, a!!, b!!, resLast)
                        if (a.hasNext()) {
                            b = removeFirst(b)
                            if (b == null)
                                break
                        } else {
                            a = removeFirst(a)
                            if (a == null)
                                break
                        }
                    }
                    if (a != null) {
                        var count = a!!.availableRead()
                        resLast = copy(a, resLast, count)
                        append(resLast, a)
                    } else {
                        var count = b!!.availableRead()
                        resLast = copy(b!!, resLast, count)
                        append(resLast, b!!)
                    }
                    return res
                }
            }
        }
    }

    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    var prev = this
    var next = this

    fun chunkCount(): Int {
        var res = 1
        var tmp = this.next
        while (tmp != this) {
            res++
            tmp = tmp.next
        }
        return res
    }

    fun insertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        var column = data[columnOrder[0].toInt()]
        var idx = column.insertSorted(values[0], comparator = comparator[columnOrder[0].toInt()], count = count)
        var first = idx
        var last = first + column.data[first].count - count
        for (i in 1 until columns) {
            column = data[columnOrder[i].toInt()]
            idx = column.insertSorted(values[i], first, last, comparator[columnOrder[i].toInt()], count)
            first = idx
            last = first + column.data[first].count - count
        }
    }

    fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>) {
        val tmp = ResultChunk(resultSet, columns)
        while (hasNext()) {
            val same = sameElements()
            tmp.insertSorted(comparator, columnOrder, Array(columns) { data[it].current() }, same)
            skipPos(same)
        }
        for (i in 0 until columns)
            data[i] = tmp.data[i]
    }

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
    open fun copy(columnsTo: Array<Variable>, chunkFrom: ResultChunk, columnsFrom: Array<Variable>, count: Int) {
        for (c in 0 until columnsTo.size) {
            val colTo = data[columnsTo[c].toInt()]
            val colFrom = chunkFrom.data[columnsFrom[c].toInt()]
            colTo.copy(colFrom, count)
        }
    }

    open fun copy(chunkFrom: ResultChunk, count: Int) {
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


}

class ValueComparatorFast : Comparator<Value> {
    override fun compare(a: Value, b: Value) = a.compareTo(b)
}

