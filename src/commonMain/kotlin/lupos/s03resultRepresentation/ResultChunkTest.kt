package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(169)
            if (a < b) {
                Coverage.ifStart(170)
                return -1
            }
            if (a == b) {
                Coverage.ifStart(171)
                throw Exception("dont compare equal values using comparator")
            }
            return 1
        }
    }

    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
            Coverage.funStart(172)
            for (v in variables) {
                Coverage.forLoopStart(173)
                if (a[v.toInt()] < b[v.toInt()]) {
                    Coverage.ifStart(174)
                    return -1
                }
                if (a[v.toInt()] > b[v.toInt()]) {
                    Coverage.ifStart(175)
                    return +1
                }
            }
            return 0
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
        Coverage.funStart(176)
        try {
            val res = buffer.getNextInt() % max
            if (positiveOnly && res < 0) {
                Coverage.ifStart(177)
                return -res
            }
            return res
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
    }

    fun max(a: Int, b: Int): Int {
        Coverage.funStart(178)
        if (a < b) {
            Coverage.ifStart(179)
            return b
        }
        return a
    }

    fun min(a: Int, b: Int): Int {
        Coverage.funStart(180)
        if (a > b) {
            Coverage.ifStart(181)
            return b
        }
        return a
    }

    var columns = 4
    fun log(s: String) {
        Coverage.funStart(182)
        if (verbose) {
            Coverage.ifStart(183)
            println(s)
        }
    }

    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
        Coverage.funStart(184)
        val res = StringBuilder()
        res.append("[\n")
        if (kotlinList.size > 0) {
            Coverage.ifStart(185)
            var counter = 1
            var idx = 1
            var lastRow = kotlinList[0]
            while (idx < kotlinList.size) {
                Coverage.whileLoopStart(186)
                var row = kotlinList[idx]
                var equal = true
                for (i in lastRow.indices) {
                    Coverage.forLoopStart(187)
                    if (lastRow[i] != row[i]) {
                        Coverage.ifStart(188)
                        equal = false
                    }
                }
                if (equal) {
                    Coverage.ifStart(189)
                    counter++
                } else {
                    Coverage.ifStart(190)
                    res.append("${lastRow.map { it }}($counter)\n")
                    lastRow = row
                    counter = 1
                }
                idx++
            }
            res.append("${lastRow.map { it }}($counter)\n")
        }
        res.append("]\n")
        return res.toString()
    }

    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
        Coverage.funStart(191)
        var tmp = chunk
        log("" + kotlinList.map { it.map { it }.toString() + "\n" })
        log("" + tmp)
        tmp.backupPosition()
        for (i in 0 until kotlinList.size) {
            Coverage.forLoopStart(192)
            while (tmp.availableRead() == 0) {
                Coverage.whileLoopStart(193)
                tmp.restorePosition()
                tmp = tmp.next
                tmp.backupPosition()
                log("" + tmp)
                if (tmp == chunk) {
                    Coverage.ifStart(194)
                    break
                }
            }
            val v = tmp.nextArr()
            val w = kotlinList[i]
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
            if (tmp.availableRead() == 0) {
                Coverage.ifStart(195)
                tmp.restorePosition()
                tmp = tmp.next
                tmp.backupPosition()
                log("" + tmp)
            }
        }
        tmp.restorePosition()
        require(tmp == chunk)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(196)
        var expectException = false
        log("-----------------------start")
        try {
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
            require(ResultVektor.capacity > 0)
            var kotlinList = mutableListOf<Array<Value>>()
            var resultSetDictionary = ResultSetDictionary()
            var resultSet = ResultSet(resultSetDictionary)
            for (i in 0 until columns) {
                Coverage.forLoopStart(197)
                resultSet.createVariable("name$i")
            }
            var chunk = ResultChunk(resultSet, columns)
            var chunkLast = chunk
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
            while (true) {
                Coverage.whileLoopStart(198)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                log("value ${value.map { it }}")
                var count = nextRandom(buffer, ResultVektor.capacity, false)
                log("count $count")
                expectException = count <= 0
                for (i in 0 until count) {
                    Coverage.forLoopStart(199)
                    kotlinList.add(value)
                }
                if (!chunkLast.canAppend()) {
                    Coverage.ifStart(200)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
                }
                chunkLast.append(value, count)
                val allcolumns = MutableList(columns) { it.toLong() }
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                log("columns ${columns.map { it }}")
                val comparator = MyComparatorRow(columns)
                checkEquals(kotlinList, chunk, comparator)
                kotlinList.sortWith(comparator)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
                chunkLast = chunk.prev
                checkEquals(kotlinList, chunk, comparator)
            }
        } catch (e: NoMoreRandomException) {
        } catch (e: Throwable) {
            if (!expectException) {
                Coverage.ifStart(201)
                throw e
            }
        }
    }
}
