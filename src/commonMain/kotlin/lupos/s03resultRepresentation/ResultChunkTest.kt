package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(526)
            require(a != b)
            Coverage.statementStart(527)
            if (a < b) {
                Coverage.ifStart(528)
                return -1
            }
            Coverage.statementStart(529)
            return 1
        }
    }

    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
            Coverage.funStart(530)
            for (i in variables.indices) {
                Coverage.forLoopStart(531)
                val v = variables[i]
                Coverage.statementStart(532)
                if (a[v.toInt()] < b[v.toInt()]) {
                    Coverage.ifStart(533)
                    return -1
                }
                Coverage.statementStart(534)
                if (a[v.toInt()] > b[v.toInt()]) {
                    Coverage.ifStart(535)
                    return +1
                }
                Coverage.statementStart(536)
            }
            Coverage.statementStart(537)
            return 0
        }
    }

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 3
    val MAX_DISTINCT_VALUES = 5
    val MAX_CAPACITY = 50
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 3
    val verbose = false

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        Coverage.funStart(538)
        try {
            Coverage.statementStart(539)
            val res = buffer.getNextInt() % max
            Coverage.statementStart(540)
            if (positiveOnly && res < 0) {
                Coverage.ifStart(541)
                return -res
            }
            Coverage.statementStart(542)
            return res
        } catch (e: Throwable) {
            Coverage.statementStart(543)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
        Coverage.statementStart(545)
    }

    var columns = 4
    fun log(s: String) {
        Coverage.funStart(546)
        if (verbose) {
            Coverage.ifStart(547)
            println(s)
            Coverage.statementStart(548)
        }
        Coverage.statementStart(549)
    }

    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
        Coverage.funStart(550)
        val res = StringBuilder()
        Coverage.statementStart(551)
        res.append("[\n")
        Coverage.statementStart(552)
        if (kotlinList.size > 0) {
            Coverage.ifStart(553)
            var counter = 1
            Coverage.statementStart(554)
            var idx = 1
            Coverage.statementStart(555)
            var lastRow = kotlinList[0]
            Coverage.statementStart(556)
            while (idx < kotlinList.size) {
                Coverage.whileLoopStart(557)
                var row = kotlinList[idx]
                Coverage.statementStart(558)
                var equal = true
                Coverage.statementStart(559)
                for (i in lastRow.indices) {
                    Coverage.forLoopStart(560)
                    if (lastRow[i] != row[i]) {
                        Coverage.ifStart(561)
                        equal = false
                        Coverage.statementStart(562)
                    }
                    Coverage.statementStart(563)
                }
                Coverage.statementStart(564)
                if (equal) {
                    Coverage.ifStart(565)
                    counter++
                    Coverage.statementStart(566)
                } else {
                    Coverage.ifStart(567)
                    res.append("${lastRow.map { it }}($counter)\n")
                    Coverage.statementStart(568)
                    lastRow = row
                    Coverage.statementStart(569)
                    counter = 1
                    Coverage.statementStart(570)
                }
                Coverage.statementStart(571)
                idx++
                Coverage.statementStart(572)
            }
            Coverage.statementStart(573)
            res.append("${lastRow.map { it }}($counter)\n")
            Coverage.statementStart(574)
        }
        Coverage.statementStart(575)
        res.append("]\n")
        Coverage.statementStart(576)
        return res.toString()
    }

    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
        Coverage.funStart(577)
        var tmp = chunk
        Coverage.statementStart(578)
        log(kotlinListToString(kotlinList))
        Coverage.statementStart(579)
        log("" + tmp)
        Coverage.statementStart(580)
        tmp.backupPosition()
        Coverage.statementStart(581)
        for (i in 0 until kotlinList.size) {
            Coverage.forLoopStart(582)
            val v = tmp.nextArr()
            Coverage.statementStart(583)
            val w = kotlinList[i]
            Coverage.statementStart(584)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
            Coverage.statementStart(585)
            if (tmp.availableRead() == 0) {
                Coverage.ifStart(586)
                tmp.restorePosition()
                Coverage.statementStart(587)
                tmp = tmp.next
                Coverage.statementStart(588)
                tmp.backupPosition()
                Coverage.statementStart(589)
                log("" + tmp)
                Coverage.statementStart(590)
            }
            Coverage.statementStart(591)
        }
        Coverage.statementStart(592)
        tmp.restorePosition()
        Coverage.statementStart(593)
        require(tmp == chunk)
        Coverage.statementStart(594)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(595)
        var expectException = false
        Coverage.statementStart(596)
        log("-----------------------start")
        Coverage.statementStart(597)
        try {
            Coverage.statementStart(598)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            Coverage.statementStart(599)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
            Coverage.statementStart(600)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(601)
            var kotlinList = mutableListOf<Array<Value>>()
            Coverage.statementStart(602)
            var resultSetDictionary = ResultSetDictionary()
            Coverage.statementStart(603)
            var resultSet = ResultSet(resultSetDictionary)
            Coverage.statementStart(604)
            for (i in 0 until columns) {
                Coverage.forLoopStart(605)
                resultSet.createVariable("name$i")
                Coverage.statementStart(606)
            }
            Coverage.statementStart(607)
            var chunk = ResultChunk(resultSet, columns)
            Coverage.statementStart(608)
            var chunkLast = chunk
            Coverage.statementStart(609)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
            Coverage.statementStart(610)
            while (true) {
                Coverage.whileLoopStart(611)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                Coverage.statementStart(612)
                log("value ${value.map { it }}")
                Coverage.statementStart(613)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
                Coverage.statementStart(614)
                log("count $count")
                Coverage.statementStart(615)
                expectException = count <= 0
                Coverage.statementStart(616)
                for (i in 0 until count) {
                    Coverage.forLoopStart(617)
                    kotlinList.add(value)
                    Coverage.statementStart(618)
                }
                Coverage.statementStart(619)
                if (!chunkLast.canAppend()) {
                    Coverage.ifStart(620)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
                    Coverage.statementStart(621)
                }
                Coverage.statementStart(622)
                chunkLast.append(value, count)
                Coverage.statementStart(623)
                val allcolumns = MutableList(columns) { it.toLong() }
                Coverage.statementStart(624)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                Coverage.statementStart(625)
                log("columns ${columns.map { it }}")
                Coverage.statementStart(626)
                val comparator = MyComparatorRow(columns)
                Coverage.statementStart(627)
                checkEquals(kotlinList, chunk, comparator)
                Coverage.statementStart(628)
                log("columns ${columns.map { it }}")
                Coverage.statementStart(629)
                log("withoutsort" + kotlinListToString(kotlinList))
                Coverage.statementStart(630)
                kotlinList.sortWith(comparator)
                Coverage.statementStart(631)
                log("aftersort" + kotlinListToString(kotlinList))
                Coverage.statementStart(632)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
                Coverage.statementStart(633)
                chunkLast = chunk.prev
                Coverage.statementStart(634)
                checkEquals(kotlinList, chunk, comparator)
                Coverage.statementStart(635)
            }
/*Coverage Unreachable*/
            Coverage.statementStart(637)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(638)
        } catch (e: Throwable) {
            Coverage.statementStart(639)
            if (!expectException) {
/*Coverage Unreachable*/
                Coverage.statementStart(641)
                throw e
            }
            Coverage.statementStart(642)
        }
        Coverage.statementStart(643)
    }
}
