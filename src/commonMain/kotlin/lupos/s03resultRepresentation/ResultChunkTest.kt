package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(513)
            require(a != b)
Coverage.statementStart(514)
            if (a < b) {
Coverage.ifStart(515)
                return -1
            }
Coverage.statementStart(516)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(517)
            for (i in variables.indices) {
Coverage.forLoopStart(518)
                val v = variables[i]
Coverage.statementStart(519)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(520)
                    return -1
                }
Coverage.statementStart(521)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(522)
                    return +1
                }
Coverage.statementStart(523)
            }
Coverage.statementStart(524)
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
Coverage.funStart(525)
        try {
Coverage.statementStart(526)
            val res = buffer.getNextInt() % max
Coverage.statementStart(527)
            if (positiveOnly && res < 0) {
Coverage.ifStart(528)
                return -res
            }
Coverage.statementStart(529)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(530)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(531)
        if (verbose) {
Coverage.ifStart(532)
            println(s)
Coverage.statementStart(533)
        }
Coverage.statementStart(534)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(535)
        val res = StringBuilder()
Coverage.statementStart(536)
        res.append("[\n")
Coverage.statementStart(537)
        if (kotlinList.size > 0) {
Coverage.ifStart(538)
            var counter = 1
Coverage.statementStart(539)
            var idx = 1
Coverage.statementStart(540)
            var lastRow = kotlinList[0]
Coverage.statementStart(541)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(542)
                var row = kotlinList[idx]
Coverage.statementStart(543)
                var equal = true
Coverage.statementStart(544)
                for (i in lastRow.indices) {
Coverage.forLoopStart(545)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(546)
                        equal = false
Coverage.statementStart(547)
                    }
Coverage.statementStart(548)
                }
Coverage.statementStart(549)
                if (equal) {
Coverage.ifStart(550)
                    counter++
Coverage.statementStart(551)
                } else {
Coverage.ifStart(552)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(553)
                    lastRow = row
Coverage.statementStart(554)
                    counter = 1
Coverage.statementStart(555)
                }
Coverage.statementStart(556)
                idx++
Coverage.statementStart(557)
            }
Coverage.statementStart(558)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(559)
        }
Coverage.statementStart(560)
        res.append("]\n")
Coverage.statementStart(561)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(562)
        var tmp = chunk
Coverage.statementStart(563)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(564)
        log("" + tmp)
Coverage.statementStart(565)
        tmp.backupPosition()
Coverage.statementStart(566)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(567)
            val v = tmp.nextArr()
Coverage.statementStart(568)
            val w = kotlinList[i]
Coverage.statementStart(569)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(570)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(571)
                tmp.restorePosition()
Coverage.statementStart(572)
                tmp = tmp.next
Coverage.statementStart(573)
                tmp.backupPosition()
Coverage.statementStart(574)
                log("" + tmp)
Coverage.statementStart(575)
            }
Coverage.statementStart(576)
        }
Coverage.statementStart(577)
        tmp.restorePosition()
Coverage.statementStart(578)
        require(tmp == chunk)
Coverage.statementStart(579)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(580)
        var expectException = false
Coverage.statementStart(581)
        log("-----------------------start")
Coverage.statementStart(582)
        try {
Coverage.statementStart(583)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(584)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(585)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(586)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(587)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(588)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(589)
            for (i in 0 until columns) {
Coverage.forLoopStart(590)
                resultSet.createVariable("name$i")
Coverage.statementStart(591)
            }
Coverage.statementStart(592)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(593)
            var chunkLast = chunk
Coverage.statementStart(594)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(595)
            while (true) {
Coverage.whileLoopStart(596)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(597)
                log("value ${value.map { it }}")
Coverage.statementStart(598)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(599)
                log("count $count")
Coverage.statementStart(600)
                expectException = count <= 0
Coverage.statementStart(601)
                for (i in 0 until count) {
Coverage.forLoopStart(602)
                    kotlinList.add(value)
Coverage.statementStart(603)
                }
Coverage.statementStart(604)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(605)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(606)
                }
Coverage.statementStart(607)
                chunkLast.append(value, count)
Coverage.statementStart(608)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(609)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(610)
                log("columns ${columns.map { it }}")
Coverage.statementStart(611)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(612)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(613)
                log("columns ${columns.map { it }}")
Coverage.statementStart(614)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(615)
                kotlinList.sortWith(comparator)
Coverage.statementStart(616)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(617)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(618)
                chunkLast = chunk.prev
Coverage.statementStart(619)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(620)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(621)
        } catch (e: Throwable) {
Coverage.statementStart(622)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(623)
        }
Coverage.statementStart(624)
    }
}
