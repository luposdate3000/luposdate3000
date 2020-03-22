package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(520)
            require(a != b)
Coverage.statementStart(521)
            if (a < b) {
Coverage.ifStart(522)
                return -1
            }
Coverage.statementStart(523)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(524)
            for (i in variables.indices) {
Coverage.forLoopStart(525)
                val v = variables[i]
Coverage.statementStart(526)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(527)
                    return -1
                }
Coverage.statementStart(528)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(529)
                    return +1
                }
Coverage.statementStart(530)
            }
Coverage.statementStart(531)
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
Coverage.funStart(532)
        try {
Coverage.statementStart(533)
            val res = buffer.getNextInt() % max
Coverage.statementStart(534)
            if (positiveOnly && res < 0) {
Coverage.ifStart(535)
                return -res
            }
Coverage.statementStart(536)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(537)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(539)
        if (verbose) {
Coverage.ifStart(540)
            println(s)
Coverage.statementStart(541)
        }
Coverage.statementStart(542)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(543)
        val res = StringBuilder()
Coverage.statementStart(544)
        res.append("[\n")
Coverage.statementStart(545)
        if (kotlinList.size > 0) {
Coverage.ifStart(546)
            var counter = 1
Coverage.statementStart(547)
            var idx = 1
Coverage.statementStart(548)
            var lastRow = kotlinList[0]
Coverage.statementStart(549)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(550)
                var row = kotlinList[idx]
Coverage.statementStart(551)
                var equal = true
Coverage.statementStart(552)
                for (i in lastRow.indices) {
Coverage.forLoopStart(553)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(554)
                        equal = false
Coverage.statementStart(555)
                    }
Coverage.statementStart(556)
                }
Coverage.statementStart(557)
                if (equal) {
Coverage.ifStart(558)
                    counter++
Coverage.statementStart(559)
                } else {
Coverage.ifStart(560)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(561)
                    lastRow = row
Coverage.statementStart(562)
                    counter = 1
Coverage.statementStart(563)
                }
Coverage.statementStart(564)
                idx++
Coverage.statementStart(565)
            }
Coverage.statementStart(566)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(567)
        }
Coverage.statementStart(568)
        res.append("]\n")
Coverage.statementStart(569)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(570)
        var tmp = chunk
Coverage.statementStart(571)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(572)
        log("" + tmp)
Coverage.statementStart(573)
        tmp.backupPosition()
Coverage.statementStart(574)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(575)
            val v = tmp.nextArr()
Coverage.statementStart(576)
            val w = kotlinList[i]
Coverage.statementStart(577)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(578)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(579)
                tmp.restorePosition()
Coverage.statementStart(580)
                tmp = tmp.next
Coverage.statementStart(581)
                tmp.backupPosition()
Coverage.statementStart(582)
                log("" + tmp)
Coverage.statementStart(583)
            }
Coverage.statementStart(584)
        }
Coverage.statementStart(585)
        tmp.restorePosition()
Coverage.statementStart(586)
        require(tmp == chunk)
Coverage.statementStart(587)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(588)
        var expectException = false
Coverage.statementStart(589)
        log("-----------------------start")
Coverage.statementStart(590)
        try {
Coverage.statementStart(591)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(592)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(593)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(594)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(595)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(596)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(597)
            for (i in 0 until columns) {
Coverage.forLoopStart(598)
                resultSet.createVariable("name$i")
Coverage.statementStart(599)
            }
Coverage.statementStart(600)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(601)
            var chunkLast = chunk
Coverage.statementStart(602)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(603)
            while (true) {
Coverage.whileLoopStart(604)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(605)
                log("value ${value.map { it }}")
Coverage.statementStart(606)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(607)
                log("count $count")
Coverage.statementStart(608)
                expectException = count <= 0
Coverage.statementStart(609)
                for (i in 0 until count) {
Coverage.forLoopStart(610)
                    kotlinList.add(value)
Coverage.statementStart(611)
                }
Coverage.statementStart(612)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(613)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(614)
                }
Coverage.statementStart(615)
                chunkLast.append(value, count)
Coverage.statementStart(616)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(617)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(618)
                log("columns ${columns.map { it }}")
Coverage.statementStart(619)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(620)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(621)
                log("columns ${columns.map { it }}")
Coverage.statementStart(622)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(623)
                kotlinList.sortWith(comparator)
Coverage.statementStart(624)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(625)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(626)
                chunkLast = chunk.prev
Coverage.statementStart(627)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(628)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(630)
        } catch (e: Throwable) {
Coverage.statementStart(631)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(633)
        }
Coverage.statementStart(634)
    }
}
