package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(512)
            require(a != b)
Coverage.statementStart(513)
            if (a < b) {
Coverage.ifStart(514)
                return -1
            }
Coverage.statementStart(515)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(516)
            for (i in variables.indices) {
Coverage.forLoopStart(517)
                val v = variables[i]
Coverage.statementStart(518)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(519)
                    return -1
                }
Coverage.statementStart(520)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(521)
                    return +1
                }
Coverage.statementStart(522)
            }
Coverage.statementStart(523)
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
Coverage.funStart(524)
        try {
Coverage.statementStart(525)
            val res = buffer.getNextInt() % max
Coverage.statementStart(526)
            if (positiveOnly && res < 0) {
Coverage.ifStart(527)
                return -res
            }
Coverage.statementStart(528)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(529)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(530)
        if (verbose) {
Coverage.ifStart(531)
            println(s)
Coverage.statementStart(532)
        }
Coverage.statementStart(533)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(534)
        val res = StringBuilder()
Coverage.statementStart(535)
        res.append("[\n")
Coverage.statementStart(536)
        if (kotlinList.size > 0) {
Coverage.ifStart(537)
            var counter = 1
Coverage.statementStart(538)
            var idx = 1
Coverage.statementStart(539)
            var lastRow = kotlinList[0]
Coverage.statementStart(540)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(541)
                var row = kotlinList[idx]
Coverage.statementStart(542)
                var equal = true
Coverage.statementStart(543)
                for (i in lastRow.indices) {
Coverage.forLoopStart(544)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(545)
                        equal = false
Coverage.statementStart(546)
                    }
Coverage.statementStart(547)
                }
Coverage.statementStart(548)
                if (equal) {
Coverage.ifStart(549)
                    counter++
Coverage.statementStart(550)
                } else {
Coverage.ifStart(551)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(552)
                    lastRow = row
Coverage.statementStart(553)
                    counter = 1
Coverage.statementStart(554)
                }
Coverage.statementStart(555)
                idx++
Coverage.statementStart(556)
            }
Coverage.statementStart(557)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(558)
        }
Coverage.statementStart(559)
        res.append("]\n")
Coverage.statementStart(560)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(561)
        var tmp = chunk
Coverage.statementStart(562)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(563)
        log("" + tmp)
Coverage.statementStart(564)
        tmp.backupPosition()
Coverage.statementStart(565)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(566)
            val v = tmp.nextArr()
Coverage.statementStart(567)
            val w = kotlinList[i]
Coverage.statementStart(568)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(569)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(570)
                tmp.restorePosition()
Coverage.statementStart(571)
                tmp = tmp.next
Coverage.statementStart(572)
                tmp.backupPosition()
Coverage.statementStart(573)
                log("" + tmp)
Coverage.statementStart(574)
            }
Coverage.statementStart(575)
        }
Coverage.statementStart(576)
        tmp.restorePosition()
Coverage.statementStart(577)
        require(tmp == chunk)
Coverage.statementStart(578)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(579)
        var expectException = false
Coverage.statementStart(580)
        log("-----------------------start")
Coverage.statementStart(581)
        try {
Coverage.statementStart(582)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(583)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(584)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(585)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(586)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(587)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(588)
            for (i in 0 until columns) {
Coverage.forLoopStart(589)
                resultSet.createVariable("name$i")
Coverage.statementStart(590)
            }
Coverage.statementStart(591)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(592)
            var chunkLast = chunk
Coverage.statementStart(593)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(594)
            while (true) {
Coverage.whileLoopStart(595)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(596)
                log("value ${value.map { it }}")
Coverage.statementStart(597)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(598)
                log("count $count")
Coverage.statementStart(599)
                expectException = count <= 0
Coverage.statementStart(600)
                for (i in 0 until count) {
Coverage.forLoopStart(601)
                    kotlinList.add(value)
Coverage.statementStart(602)
                }
Coverage.statementStart(603)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(604)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(605)
                }
Coverage.statementStart(606)
                chunkLast.append(value, count)
Coverage.statementStart(607)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(608)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(609)
                log("columns ${columns.map { it }}")
Coverage.statementStart(610)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(611)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(612)
                log("columns ${columns.map { it }}")
Coverage.statementStart(613)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(614)
                kotlinList.sortWith(comparator)
Coverage.statementStart(615)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(616)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(617)
                chunkLast = chunk.prev
Coverage.statementStart(618)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(619)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(620)
        } catch (e: Throwable) {
Coverage.statementStart(621)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(622)
        }
Coverage.statementStart(623)
    }
}
