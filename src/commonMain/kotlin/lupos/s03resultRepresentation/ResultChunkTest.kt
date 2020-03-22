package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(578)
            if (a < b) {
Coverage.ifStart(579)
                return -1
            }
Coverage.statementStart(580)
            if (a == b) {
Coverage.ifStart(581)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(582)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(583)
            for (i in variables.indices) {
Coverage.forLoopStart(584)
                val v = variables[i]
Coverage.statementStart(585)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(586)
                    return -1
                }
Coverage.statementStart(587)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(588)
                    return +1
                }
Coverage.statementStart(589)
            }
Coverage.statementStart(590)
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
Coverage.funStart(591)
        try {
Coverage.statementStart(592)
            val res = buffer.getNextInt() % max
Coverage.statementStart(593)
            if (positiveOnly && res < 0) {
Coverage.ifStart(594)
                return -res
            }
Coverage.statementStart(595)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(596)
            throw NoMoreRandomException()
        }
Coverage.statementStart(597)
    }
    fun max(a: Int, b: Int): Int {
Coverage.funStart(598)
        if (a < b) {
Coverage.ifStart(599)
            return b
        }
Coverage.statementStart(600)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(601)
        if (a > b) {
Coverage.ifStart(602)
            return b
        }
Coverage.statementStart(603)
        return a
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(604)
        if (verbose) {
Coverage.ifStart(605)
            println(s)
Coverage.statementStart(606)
        }
Coverage.statementStart(607)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(608)
        val res = StringBuilder()
Coverage.statementStart(609)
        res.append("[\n")
Coverage.statementStart(610)
        if (kotlinList.size > 0) {
Coverage.ifStart(611)
            var counter = 1
Coverage.statementStart(612)
            var idx = 1
Coverage.statementStart(613)
            var lastRow = kotlinList[0]
Coverage.statementStart(614)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(615)
                var row = kotlinList[idx]
Coverage.statementStart(616)
                var equal = true
Coverage.statementStart(617)
                for (i in lastRow.indices) {
Coverage.forLoopStart(618)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(619)
                        equal = false
Coverage.statementStart(620)
                    }
Coverage.statementStart(621)
                }
Coverage.statementStart(622)
                if (equal) {
Coverage.ifStart(623)
                    counter++
Coverage.statementStart(624)
                } else {
Coverage.ifStart(625)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(626)
                    lastRow = row
Coverage.statementStart(627)
                    counter = 1
Coverage.statementStart(628)
                }
Coverage.statementStart(629)
                idx++
Coverage.statementStart(630)
            }
Coverage.statementStart(631)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(632)
        }
Coverage.statementStart(633)
        res.append("]\n")
Coverage.statementStart(634)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(635)
        var tmp = chunk
Coverage.statementStart(636)
        log(kotlinListToString(kotlinList))
Coverage.statementStart(637)
        log("" + tmp)
Coverage.statementStart(638)
        tmp.backupPosition()
Coverage.statementStart(639)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(640)
            while (tmp.availableRead() == 0) {
Coverage.whileLoopStart(641)
                tmp.restorePosition()
Coverage.statementStart(642)
                tmp = tmp.next
Coverage.statementStart(643)
                tmp.backupPosition()
Coverage.statementStart(644)
                log("" + tmp)
Coverage.statementStart(645)
                if (tmp == chunk) {
Coverage.ifStart(646)
                    break
                }
Coverage.statementStart(647)
            }
Coverage.statementStart(648)
            val v = tmp.nextArr()
Coverage.statementStart(649)
            val w = kotlinList[i]
Coverage.statementStart(650)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(651)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(652)
                tmp.restorePosition()
Coverage.statementStart(653)
                tmp = tmp.next
Coverage.statementStart(654)
                tmp.backupPosition()
Coverage.statementStart(655)
                log("" + tmp)
Coverage.statementStart(656)
            }
Coverage.statementStart(657)
        }
Coverage.statementStart(658)
        tmp.restorePosition()
Coverage.statementStart(659)
        require(tmp == chunk)
Coverage.statementStart(660)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(661)
        var expectException = false
Coverage.statementStart(662)
        log("-----------------------start")
Coverage.statementStart(663)
        try {
Coverage.statementStart(664)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(665)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(666)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(667)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(668)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(669)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(670)
            for (i in 0 until columns) {
Coverage.forLoopStart(671)
                resultSet.createVariable("name$i")
Coverage.statementStart(672)
            }
Coverage.statementStart(673)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(674)
            var chunkLast = chunk
Coverage.statementStart(675)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(676)
            while (true) {
Coverage.whileLoopStart(677)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(678)
                log("value ${value.map { it }}")
Coverage.statementStart(679)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(680)
                log("count $count")
Coverage.statementStart(681)
                expectException = count <= 0
Coverage.statementStart(682)
                for (i in 0 until count) {
Coverage.forLoopStart(683)
                    kotlinList.add(value)
Coverage.statementStart(684)
                }
Coverage.statementStart(685)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(686)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(687)
                }
Coverage.statementStart(688)
                chunkLast.append(value, count)
Coverage.statementStart(689)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(690)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(691)
                log("columns ${columns.map { it }}")
Coverage.statementStart(692)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(693)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(694)
                log("columns ${columns.map { it }}")
Coverage.statementStart(695)
                log("withoutsort" + kotlinListToString(kotlinList))
Coverage.statementStart(696)
                kotlinList.sortWith(comparator)
Coverage.statementStart(697)
                log("aftersort" + kotlinListToString(kotlinList))
Coverage.statementStart(698)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(699)
                chunkLast = chunk.prev
Coverage.statementStart(700)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(701)
            }
Coverage.statementStart(702)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(703)
        } catch (e: Throwable) {
Coverage.statementStart(704)
            if (!expectException) {
Coverage.ifStart(705)
                throw e
            }
Coverage.statementStart(706)
        }
Coverage.statementStart(707)
    }
}
