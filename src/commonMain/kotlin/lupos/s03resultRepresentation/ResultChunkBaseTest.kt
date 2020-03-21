package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage


object ResultChunkBaseTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(28)
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
    val verbose = false

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        Coverage.funStart(29)
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
        Coverage.funStart(30)
        if (a < b)
            return b
        return a
    }

    fun min(a: Int, b: Int): Int {
        Coverage.funStart(31)
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

        constructor() {
            for (i in 0 until columns)
                resultSet.createVariable("name$i")
        }
    }

    fun log(s: String) {
        Coverage.funStart(32)
        if (verbose)
            println(s)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(33)
        var expectException = false
        log("-----------------------start")
        try {
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            require(ResultVektor.capacity > 0)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            while (true) {
                Coverage.whileLoopStart(34)
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
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
                        helper.chunk.skipPos(count)
                        helper.pos += count
                    }
                    1 -> {
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        if (count < 0 && helper.pos > helper.size + count)
                            count = helper.pos - helper.size
                        log("count $count")
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
                        helper.chunk.skipSize(count)
                        if (count > 0) {
                            Coverage.ifStart(35)
                            for (i in 0 until count)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
                        } else {
                            Coverage.ifStart(36)
                            if (!expectException)
                                for (i in 0 until -count)
                                    helper.kotlinList.removeAt(helper.size + count)
                        }
                        helper.size += count
                    }
                    2 -> {
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        if (count < 0 && helper.pos > helper.size + count)
                            count = helper.pos - helper.size
                        log("count $count")
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                        expectException = count <= 0 || !helper.chunk.canAppend()
                        helper.chunk.append(value, count)
                        for (i in 0 until count)
                            helper.kotlinList.add(helper.size, value)
                        helper.size += count
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
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    }
                    6 -> {
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
                    }
                    7 -> {
                        expectException = helper.pos >= helper.size
                        val v = helper.chunk.current()
                        val w = helper.kotlinList[helper.pos]
                        for (i in 0 until columns)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                    }
                    8 -> {
                        expectException = helper.pos >= helper.size
                        val v = helper.chunk.nextArr()
                        val w = helper.kotlinList[helper.pos]
                        for (i in 0 until columns)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                        helper.pos++
                    }
                    9 -> {
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    }
                    10 -> {
                        val colcount = nextRandom(buffer, columns, true)
                        expectException = colcount == 0 || helper.pos >= helper.size
                        val allcolumns = MutableList(columns) { it.toLong() }
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        val v = helper.chunk.current(columns)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
                        for (i in 0 until colcount)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                    }
                    11 -> {
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
                    }
                    12 -> {
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        val helper2 = helpers[helperIdx2]
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        expectException = count > helper.size - helper.pos || count <= 0
                        if (count == 0) {
                            Coverage.ifStart(37)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(38)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(helper.chunk, count)
                        }
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(39)
                            val c = min(helper2.chunk.availableWrite(), count)
                            for (i in 0 until c) {
                                Coverage.forLoopStart(40)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(helper.chunk, c)
                            count -= c
                        }
                        if (count > 0) {
                            Coverage.ifStart(41)
                            expectException = true
                            for (i in 0 until count) {
                                Coverage.forLoopStart(42)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(helper.chunk, count)
                        }
                    }
                    13 -> {
                        val colcount = nextRandom(buffer, columns, true)
                        val allcolumns = MutableList(columns) { it.toLong() }
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        val columns2 = allcolumns.toTypedArray()
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        val helper2 = helpers[helperIdx2]
                        log("helper2 ${helper2.chunk}")
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        log("count $count")
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
                        if (count == 0) {
                            Coverage.ifStart(43)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(44)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                for (col in columns2)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            helper.chunk.skipPos(columns2, count)
                            helper2.chunk.skipSize(columns2, count)
                        }
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(45)
                            val c = min(helper2.chunk.availableWrite(), count)
                            log("progress $c")
                            for (i in 0 until c) {
                                Coverage.forLoopStart(46)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                for (col in columns2)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
                            helper.chunk.skipPos(columns2, c)
                            helper2.chunk.skipSize(columns2, c)
                            count -= c
                        }
                        if (count > 0) {
                            Coverage.ifStart(47)
                            expectException = true
                            for (i in 0 until count) {
                                Coverage.forLoopStart(48)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                helper.pos++
                                for (col in columns2)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                helper2.kotlinList.add(helper2.size++, v)
                            }
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            helper.chunk.skipPos(columns2, count)
                            helper2.chunk.skipSize(columns2, count)
                        }
                    }
                    else -> {
                        Coverage.ifStart(49)
                        require(func < FUNCTION_COUNT)
                    }
                }
                if (expectException)
                    throw Exception("there should be an exception")
                log("" + expectException)
                log("\n")
                for (helper in helpers) {
                    Coverage.forLoopStart(50)
                    log("helper ${helper.chunk}")
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    log("skippos ${-helper.pos}")
                    if (helper.pos != 0)
                        helper.chunk.skipPos(-helper.pos)
                    for (j in 0 until helper.size) {
                        Coverage.forLoopStart(51)
                        val v = helper.chunk.nextArr()
                        val w = helper.kotlinList[j]
                        for (i in 0 until columns)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
                    }
                    if (helper.pos - helper.size != 0)
                        helper.chunk.skipPos(helper.pos - helper.size)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
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
Coverage.funStart(52)
    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(53)
    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(54)
    fun getColumn(variable: Variable) = data[variable.toInt()]
    override fun next(): ResultRow {
Coverage.funStart(55)
    fun setColumn(variable: Variable, col: ResultVektor) {
Coverage.funStart(56)
    fun sameElements(columns: Array<Variable>): Int {
Coverage.funStart(57)
    fun sameElements(): Int {
Coverage.funStart(58)
    override fun toString(): String {
Coverage.funStart(59)

*/
