package lupos.s03resultRepresentation

import lupos.s00misc.*

object ResultChunkBaseTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            if (a < b)
                return -1
            if (a == b)
                throw Exception("dont compare equal values using comparator")
            return 1
        }
    }

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 10
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = true

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        try {
            val res = buffer.getNextInt() % max
            if (positiveOnly && res < 0)
                return -res
            return res
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
    }

    fun max(a: Int, b: Int): Int {
        if (a < b)
            return b
        return a
    }

    fun min(a: Int, b: Int): Int {
        if (a > b)
            return b
        return a
    }

    var columns = 4

    class ResultVektorTestHelper {
        var kotlinList = mutableListOf<Array<Value>>()
        var resultSetDictionary = ResultSetDictionary()
        var resultSet = ResultSet(resultSetDictionary)
        var chunk = ResultChunkBase(resultSet, columns)
        var pos = 0
        var size = 0
        var backup = 0
constructor(){
for(i in 0 until columns)
resultSet.createVariable("name$i")
}
    }

    fun log(s: String) {
        if (verbose)
            println(s)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        var expectException = false
        log("-----------------------start")
        try {
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            require(ResultVektor.capacity > 0)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            while (true) {
                expectException = false
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                val helper = helpers[helperIdx]
log("helper ${helper.chunk}")
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                log("func $func")
                when (func) {
                    0 -> {
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        log("count $count")
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
                        helper.chunk.skipPos(count)
                        helper.pos += count
                    }
                    1 -> {
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        if (count < 0 && helper.pos > helper.size + count)
                            count = helper.pos - helper.size
                        log("count $count")
                        expectException = helper.size + count < 0 || !helper.chunk.canAppend()
                        helper.chunk.skipSize(count)
                        helper.size += count
                        if (count > 0) {
                            for (i in 0 until count)
                                helper.kotlinList.add(Array(columns) { DONT_CARE_VALUE })
                        } else {
                            if (!expectException)
                                for (i in 0 until -count)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
                        }
                    }
                    2 -> {
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        if (count < 0 && helper.pos > helper.size + count)
                            count = helper.pos - helper.size
                        log("count $count")
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                        expectException = helper.size + count < 0 || !helper.chunk.canAppend()
                        helper.chunk.append(value, count)
                        for (i in 0 until count)
                            helper.kotlinList.add(value)
                    }
                    3 -> {
                        helper.backup = helper.pos
                        helper.chunk.backupPosition()
                    }
                    4 -> {
                        helper.pos = helper.backup
                        helper.chunk.restorePosition()
                    }
                    5 -> {
                        require(helper.size - helper.pos == helper.chunk.availableRead())
                    }
                    6 -> {
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
                    }
                    7 -> {
                        expectException = helper.pos >= helper.size
                        val v = helper.chunk.current()
                        val w = helper.kotlinList[helper.pos]
                        for (i in 0 until columns)
                            require(v[i] == w[i])
                    }
                    8 -> {
                        expectException = helper.pos >= helper.size
                        val v = helper.chunk.nextArr()
                        val w = helper.kotlinList[helper.pos]
                        for (i in 0 until columns)
                            require(v[i] == w[i])
                        helper.pos++
                    }
                    9 -> {
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size -1,{"${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}"})
                    }
                    10 -> {
                        val colcount = nextRandom(buffer, columns, true)
expectException=colcount==0||helper.pos>=helper.size
                        val allcolumns = MutableList(colcount) { it.toLong() }
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        val v = helper.chunk.current(columns)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
                        for (i in 0 until colcount)
                            require(v[i] == w[i])
                    }
                    11 -> {
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
                    }
                    12 -> {
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        val helper2 = helpers[helperIdx2]
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        expectException = count > helper.chunk.availableRead() || count <= 0
                        if (count == 0)
                            helper2.chunk.copy(helper.chunk, count)
                        while (helper2.chunk.canAppend() && count > 0) {
                            val c = helper2.chunk.availableWrite()
                            helper2.chunk.copy(helper.chunk, c)
                            count -= c
                        }
                        if (count > 0) {
                            expectException = true
                            helper2.chunk.copy(helper.chunk, count)
                        }
                    }
                    13 -> {
                        val colcount = nextRandom(buffer, columns, true)
                        val allcolumns = MutableList(colcount) { it.toLong() }
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        val columns2 = allcolumns.toTypedArray()
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        val helper2 = helpers[helperIdx2]
log("helper2 ${helper2.chunk}")
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
log("count $count")
                        expectException = count > helper.chunk.availableRead() || count <= 0||colcount==0
                        if (count == 0) {
                            helper2.chunk.copy(columns, helper.chunk, columns, count)
                            helper.chunk.skipPos(columns2, count)
                            helper2.chunk.skipSize(columns2, count)
                        }
                        while (helper2.chunk.canAppend() && count > 0) {
                            val c = min(helper2.chunk.availableWrite(),count)
log("progress $c")
                            helper2.chunk.copy(columns, helper.chunk, columns, c)
                            helper.chunk.skipPos(columns2, c)
                            helper2.chunk.skipSize(columns2, c)
                            count -= c
                        }
                        if (count > 0) {
                            expectException = true
                            helper2.chunk.copy(columns, helper.chunk, columns, count)
                            helper.chunk.skipPos(columns2, count)
                            helper2.chunk.skipSize(columns2, count)
                        }
                    }
                    else -> {
                        require(func < FUNCTION_COUNT)
                    }
                }
                if (expectException)
                    throw Exception("there should be an exception")
                log("" + expectException)
                log("\n")
                for (helper in helpers) {
helper.chunk.skipPos(-helper.pos)
for(j in 0 until helper.size){
                        val v = helper.chunk.nextArr()
                        val w = helper.kotlinList[j]
                        for (i in 0 until columns)
                            require(v[i] == w[i]|| w[i]==DONT_CARE_VALUE)
}
helper.chunk.skipPos(helper.pos-helper.size)
                }
                log("\n")
            }
        } catch (e: NoMoreRandomException) {
        } catch (e: Throwable) {
            if (!expectException)
                throw e
        }
    }
}
/*

    fun append(row: ResultRow, count: Int = 1) {
    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
    fun getColumn(variable: Variable) = data[variable.toInt()]
    override fun next(): ResultRow {
    fun setColumn(variable: Variable, col: ResultVektor) {
    fun sameElements(columns: Array<Variable>): Int {
    fun sameElements(): Int {
    override fun toString(): String {

*/
