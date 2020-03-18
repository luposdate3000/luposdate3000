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
	fun sort(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>,chunks:Array<ResultChunk>):Array<ResultChunk>{
when(chunks.size){
0->return chunks
1->{
chunks[0].sort()
return chunks
}
2->{
chunks[0].sort()
chunks[1].sort()
val res=mutableListOf(ResultChunk(resultSet,columns))
var resLast=res[0]
while(a.hasNext()&&b.hasNext()){
var cmp=0
for(i in columnOrder){
cmp=comparator.compare(chunks[0].data[i.toInt()].current(),chunks[1].data[i.toInt()].current())
if(cmp<0){
val same = chunks[0].sameElements()
if(!resLast.canAppend()){
resLast=ResultChunk(resultSet,columns)
res.add(resLast)
}
resLast.append(chunks[0].current())
}
}
}

return res.toTypedArray()
}
else->{
return xxx
}
}
	}

    }

    val data = Array(columns) { ResultVektor(resultSet.dictionary.undefValue) }
    fun insertSorted(comparator: Array<Comparator<Value>>, columnOrder: Array<Variable>, values: Array<Value>, count: Int = 1) {
        var column = data[columnOrder[0].toInt()]
        var idx = column.insertSorted(values[0], comparator[columnOrder[0].toInt()], count)
        var first = idx
        var last = first + column.data[first].count
        for (i in 1 until columns) {
            column = data[columnOrder[i].toInt()]
            idx = column.insertSorted(values[i], first, last, comparator[columnOrder[i].toInt()], count)
            first = idx
            last = first + column.data[first].count
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
            data[i] = tmp[i]
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

