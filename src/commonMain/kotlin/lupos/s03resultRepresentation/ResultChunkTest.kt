package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(579)
            if (a < b) {
Coverage.ifStart(580)
                return -1
            }
Coverage.statementStart(581)
            if (a == b) {
Coverage.ifStart(582)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(583)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(584)
            for (i in variables.indices) {
Coverage.forLoopStart(585)
                val v = variables[i]
Coverage.statementStart(586)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(587)
                    return -1
                }
Coverage.statementStart(588)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(589)
                    return +1
                }
Coverage.statementStart(590)
            }
Coverage.statementStart(591)
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
Coverage.funStart(592)
        try {
Coverage.statementStart(593)
            val res = buffer.getNextInt() % max
Coverage.statementStart(594)
            if (positiveOnly && res < 0) {
Coverage.ifStart(595)
                return -res
            }
Coverage.statementStart(596)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(597)
            throw NoMoreRandomException()
        }
Coverage.statementStart(598)
    }
    fun max(a: Int, b: Int): Int {
Coverage.funStart(599)
        if (a < b) {
Coverage.ifStart(600)
            return b
        }
Coverage.statementStart(601)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(602)
        if (a > b) {
Coverage.ifStart(603)
            return b
        }
Coverage.statementStart(604)
        return a
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(605)
        if (verbose) {
Coverage.ifStart(606)
            println(s)
Coverage.statementStart(607)
        }
Coverage.statementStart(608)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(609)
        val res = StringBuilder()
Coverage.statementStart(610)
        res.append("[\n")
Coverage.statementStart(611)
        if (kotlinList.size > 0) {
Coverage.ifStart(612)
            var counter = 1
Coverage.statementStart(613)
            var idx = 1
Coverage.statementStart(614)
            var lastRow = kotlinList[0]
Coverage.statementStart(615)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(616)
                var row = kotlinList[idx]
Coverage.statementStart(617)
                var equal = true
Coverage.statementStart(618)
                for (i in lastRow.indices) {
Coverage.forLoopStart(619)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(620)
                        equal = false
Coverage.statementStart(621)
                    }
Coverage.statementStart(622)
                }
Coverage.statementStart(623)
                if (equal) {
Coverage.ifStart(624)
                    counter++
Coverage.statementStart(625)
                } else {
Coverage.ifStart(626)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(627)
                    lastRow = row
Coverage.statementStart(628)
                    counter = 1
Coverage.statementStart(629)
                }
Coverage.statementStart(630)
                idx++
Coverage.statementStart(631)
            }
Coverage.statementStart(632)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(633)
        }
Coverage.statementStart(634)
        res.append("]\n")
Coverage.statementStart(635)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(636)
        var tmp = chunk
Coverage.statementStart(637)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(638)
        log("" + tmp)
Coverage.statementStart(639)
        tmp.backupPosition()
Coverage.statementStart(640)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(641)
            while (tmp.availableRead() == 0) {
Coverage.whileLoopStart(642)
                tmp.restorePosition()
Coverage.statementStart(643)
                tmp = tmp.next
Coverage.statementStart(644)
                tmp.backupPosition()
Coverage.statementStart(645)
                log("" + tmp)
Coverage.statementStart(646)
                if (tmp == chunk) {
Coverage.ifStart(647)
                    break
                }
Coverage.statementStart(648)
            }
Coverage.statementStart(649)
            val v = tmp.nextArr()
Coverage.statementStart(650)
            val w = kotlinList[i]
Coverage.statementStart(651)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(652)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(653)
                tmp.restorePosition()
Coverage.statementStart(654)
                tmp = tmp.next
Coverage.statementStart(655)
                tmp.backupPosition()
Coverage.statementStart(656)
                log("" + tmp)
Coverage.statementStart(657)
            }
Coverage.statementStart(658)
        }
Coverage.statementStart(659)
        tmp.restorePosition()
Coverage.statementStart(660)
        require(tmp == chunk)
Coverage.statementStart(661)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(662)
        var expectException = false
Coverage.statementStart(663)
        log("-----------------------start")
Coverage.statementStart(664)
        try {
Coverage.statementStart(665)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(666)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(667)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(668)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(669)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(670)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(671)
            for (i in 0 until columns) {
Coverage.forLoopStart(672)
                resultSet.createVariable("name$i")
Coverage.statementStart(673)
            }
Coverage.statementStart(674)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(675)
            var chunkLast = chunk
Coverage.statementStart(676)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(677)
            while (true) {
Coverage.whileLoopStart(678)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(679)
                log("value ${value.map { it }}")
Coverage.statementStart(680)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(681)
                log("count $count")
Coverage.statementStart(682)
                expectException = count <= 0
Coverage.statementStart(683)
                for (i in 0 until count) {
Coverage.forLoopStart(684)
                    kotlinList.add(value)
Coverage.statementStart(685)
                }
Coverage.statementStart(686)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(687)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(688)
                }
Coverage.statementStart(689)
                chunkLast.append(value, count)
Coverage.statementStart(690)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(691)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(692)
                log("columns ${columns.map { it }}")
Coverage.statementStart(693)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(694)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(695)
                log("columns ${columns.map { it }}")
Coverage.statementStart(696)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(697)
                kotlinList.sortWith(comparator)
Coverage.statementStart(698)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(699)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(700)
                chunkLast = chunk.prev
Coverage.statementStart(701)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(702)
            }
Coverage.statementStart(703)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(704)
        } catch (e: Throwable) {
Coverage.statementStart(705)
            if (!expectException) {
Coverage.ifStart(706)
                throw e
            }
Coverage.statementStart(707)
        }
Coverage.statementStart(708)
    }
}
