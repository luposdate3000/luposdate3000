package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(530)
            require(a != b)
Coverage.statementStart(531)
            if (a < b) {
Coverage.ifStart(532)
                return -1
            }
Coverage.statementStart(533)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(534)
            for (i in variables.indices) {
Coverage.forLoopStart(535)
                val v = variables[i]
Coverage.statementStart(536)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(537)
                    return -1
                }
Coverage.statementStart(538)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(539)
                    return +1
                }
Coverage.statementStart(540)
            }
Coverage.statementStart(541)
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
Coverage.funStart(542)
        try {
Coverage.statementStart(543)
            val res = buffer.getNextInt() % max
Coverage.statementStart(544)
            if (positiveOnly && res < 0) {
Coverage.ifStart(545)
                return -res
            }
Coverage.statementStart(546)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(547)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(549)
        if (verbose) {
Coverage.ifStart(550)
            println(s)
Coverage.statementStart(551)
        }
Coverage.statementStart(552)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(553)
        val res = StringBuilder()
Coverage.statementStart(554)
        res.append("[\n")
Coverage.statementStart(555)
        if (kotlinList.size > 0) {
Coverage.ifStart(556)
            var counter = 1
Coverage.statementStart(557)
            var idx = 1
Coverage.statementStart(558)
            var lastRow = kotlinList[0]
Coverage.statementStart(559)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(560)
                var row = kotlinList[idx]
Coverage.statementStart(561)
                var equal = true
Coverage.statementStart(562)
                for (i in lastRow.indices) {
Coverage.forLoopStart(563)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(564)
                        equal = false
Coverage.statementStart(565)
                    }
Coverage.statementStart(566)
                }
Coverage.statementStart(567)
                if (equal) {
Coverage.ifStart(568)
                    counter++
Coverage.statementStart(569)
                } else {
Coverage.ifStart(570)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(571)
                    lastRow = row
Coverage.statementStart(572)
                    counter = 1
Coverage.statementStart(573)
                }
Coverage.statementStart(574)
                idx++
Coverage.statementStart(575)
            }
Coverage.statementStart(576)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(577)
        }
Coverage.statementStart(578)
        res.append("]\n")
Coverage.statementStart(579)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(580)
        var tmp = chunk
Coverage.statementStart(581)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(582)
        log("" + tmp)
Coverage.statementStart(583)
        tmp.backupPosition()
Coverage.statementStart(584)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(585)
            val v = tmp.nextArr()
Coverage.statementStart(586)
            val w = kotlinList[i]
Coverage.statementStart(587)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(588)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(589)
                tmp.restorePosition()
Coverage.statementStart(590)
                tmp = tmp.next
Coverage.statementStart(591)
                tmp.backupPosition()
Coverage.statementStart(592)
                log("" + tmp)
Coverage.statementStart(593)
            }
Coverage.statementStart(594)
        }
Coverage.statementStart(595)
        tmp.restorePosition()
Coverage.statementStart(596)
        require(tmp == chunk)
Coverage.statementStart(597)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(598)
        var expectException = false
Coverage.statementStart(599)
        log("-----------------------start")
Coverage.statementStart(600)
        try {
Coverage.statementStart(601)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(602)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(603)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(604)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(605)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(606)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(607)
            for (i in 0 until columns) {
Coverage.forLoopStart(608)
                resultSet.createVariable("name$i")
Coverage.statementStart(609)
            }
Coverage.statementStart(610)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(611)
            var chunkLast = chunk
Coverage.statementStart(612)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(613)
            while (true) {
Coverage.whileLoopStart(614)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(615)
                log("value ${value.map { it }}")
Coverage.statementStart(616)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(617)
                log("count $count")
Coverage.statementStart(618)
                expectException = count <= 0
Coverage.statementStart(619)
                for (i in 0 until count) {
Coverage.forLoopStart(620)
                    kotlinList.add(value)
Coverage.statementStart(621)
                }
Coverage.statementStart(622)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(623)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(624)
                }
Coverage.statementStart(625)
                chunkLast.append(value, count)
Coverage.statementStart(626)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(627)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(628)
                log("columns ${columns.map { it }}")
Coverage.statementStart(629)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(630)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(631)
                log("columns ${columns.map { it }}")
Coverage.statementStart(632)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(633)
                kotlinList.sortWith(comparator)
Coverage.statementStart(634)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(635)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(636)
                chunkLast = chunk.prev
Coverage.statementStart(637)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(638)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(640)
        } catch (e: Throwable) {
Coverage.statementStart(641)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(643)
        }
Coverage.statementStart(644)
    }
}
