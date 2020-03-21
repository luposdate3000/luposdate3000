package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(556)
            if (a < b) {
Coverage.ifStart(557)
                return -1
            }
Coverage.statementStart(558)
            if (a == b) {
Coverage.ifStart(559)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(560)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
Coverage.funStart(561)
            for (v in variables) {
Coverage.forLoopStart(562)
                if (a[v.toInt()] < b[v.toInt()]) {
Coverage.ifStart(563)
                    return -1
                }
Coverage.statementStart(564)
                if (a[v.toInt()] > b[v.toInt()]) {
Coverage.ifStart(565)
                    return +1
                }
Coverage.statementStart(566)
            }
Coverage.statementStart(567)
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
Coverage.funStart(568)
        try {
Coverage.statementStart(569)
            val res = buffer.getNextInt() % max
Coverage.statementStart(570)
            if (positiveOnly && res < 0) {
Coverage.ifStart(571)
                return -res
            }
Coverage.statementStart(572)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(573)
            throw NoMoreRandomException()
        }
Coverage.statementStart(574)
    }
    fun max(a: Int, b: Int): Int {
Coverage.funStart(575)
        if (a < b) {
Coverage.ifStart(576)
            return b
        }
Coverage.statementStart(577)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(578)
        if (a > b) {
Coverage.ifStart(579)
            return b
        }
Coverage.statementStart(580)
        return a
    }
    var columns = 4
    fun log(s: String) {
Coverage.funStart(581)
        if (verbose) {
Coverage.ifStart(582)
            println(s)
Coverage.statementStart(583)
        }
Coverage.statementStart(584)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
Coverage.funStart(585)
        val res = StringBuilder()
Coverage.statementStart(586)
        res.append("[\n")
Coverage.statementStart(587)
        if (kotlinList.size > 0) {
Coverage.ifStart(588)
            var counter = 1
Coverage.statementStart(589)
            var idx = 1
Coverage.statementStart(590)
            var lastRow = kotlinList[0]
Coverage.statementStart(591)
            while (idx < kotlinList.size) {
Coverage.whileLoopStart(592)
                var row = kotlinList[idx]
Coverage.statementStart(593)
                var equal = true
Coverage.statementStart(594)
                for (i in lastRow.indices) {
Coverage.forLoopStart(595)
                    if (lastRow[i] != row[i]) {
Coverage.ifStart(596)
                        equal = false
Coverage.statementStart(597)
                    }
Coverage.statementStart(598)
                }
Coverage.statementStart(599)
                if (equal) {
Coverage.ifStart(600)
                    counter++
Coverage.statementStart(601)
                } else {
Coverage.ifStart(602)
                    res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(603)
                    lastRow = row
Coverage.statementStart(604)
                    counter = 1
Coverage.statementStart(605)
                }
Coverage.statementStart(606)
                idx++
Coverage.statementStart(607)
            }
Coverage.statementStart(608)
            res.append("${lastRow.map { it }}($counter)\n")
Coverage.statementStart(609)
        }
Coverage.statementStart(610)
        res.append("]\n")
Coverage.statementStart(611)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
Coverage.funStart(612)
        var tmp = chunk
Coverage.statementStart(613)
        log("" + kotlinList.map { it.map { it }.toString() + "\n" })
Coverage.statementStart(614)
        log("" + tmp)
Coverage.statementStart(615)
        tmp.backupPosition()
Coverage.statementStart(616)
        for (i in 0 until kotlinList.size) {
Coverage.forLoopStart(617)
            while (tmp.availableRead() == 0) {
Coverage.whileLoopStart(618)
                tmp.restorePosition()
Coverage.statementStart(619)
                tmp = tmp.next
Coverage.statementStart(620)
                tmp.backupPosition()
Coverage.statementStart(621)
                log("" + tmp)
Coverage.statementStart(622)
                if (tmp == chunk) {
Coverage.ifStart(623)
                    break
                }
Coverage.statementStart(624)
            }
Coverage.statementStart(625)
            val v = tmp.nextArr()
Coverage.statementStart(626)
            val w = kotlinList[i]
Coverage.statementStart(627)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
Coverage.statementStart(628)
            if (tmp.availableRead() == 0) {
Coverage.ifStart(629)
                tmp.restorePosition()
Coverage.statementStart(630)
                tmp = tmp.next
Coverage.statementStart(631)
                tmp.backupPosition()
Coverage.statementStart(632)
                log("" + tmp)
Coverage.statementStart(633)
            }
Coverage.statementStart(634)
        }
Coverage.statementStart(635)
        tmp.restorePosition()
Coverage.statementStart(636)
        require(tmp == chunk)
Coverage.statementStart(637)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(638)
        var expectException = false
Coverage.statementStart(639)
        log("-----------------------start")
Coverage.statementStart(640)
        try {
Coverage.statementStart(641)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(642)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
Coverage.statementStart(643)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(644)
            var kotlinList = mutableListOf<Array<Value>>()
Coverage.statementStart(645)
            var resultSetDictionary = ResultSetDictionary()
Coverage.statementStart(646)
            var resultSet = ResultSet(resultSetDictionary)
Coverage.statementStart(647)
            for (i in 0 until columns) {
Coverage.forLoopStart(648)
                resultSet.createVariable("name$i")
Coverage.statementStart(649)
            }
Coverage.statementStart(650)
            var chunk = ResultChunk(resultSet, columns)
Coverage.statementStart(651)
            var chunkLast = chunk
Coverage.statementStart(652)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
Coverage.statementStart(653)
            while (true) {
Coverage.whileLoopStart(654)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(655)
                log("value ${value.map { it }}")
Coverage.statementStart(656)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
Coverage.statementStart(657)
                log("count $count")
Coverage.statementStart(658)
                expectException = count <= 0
Coverage.statementStart(659)
                for (i in 0 until count) {
Coverage.forLoopStart(660)
                    kotlinList.add(value)
Coverage.statementStart(661)
                }
Coverage.statementStart(662)
                if (!chunkLast.canAppend()) {
Coverage.ifStart(663)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
Coverage.statementStart(664)
                }
Coverage.statementStart(665)
                chunkLast.append(value, count)
Coverage.statementStart(666)
                val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(667)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(668)
                log("columns ${columns.map { it }}")
Coverage.statementStart(669)
                val comparator = MyComparatorRow(columns)
Coverage.statementStart(670)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(671)
                kotlinList.sortWith(comparator)
Coverage.statementStart(672)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
Coverage.statementStart(673)
                chunkLast = chunk.prev
Coverage.statementStart(674)
                checkEquals(kotlinList, chunk, comparator)
Coverage.statementStart(675)
            }
Coverage.statementStart(676)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(677)
        } catch (e: Throwable) {
Coverage.statementStart(678)
            if (!expectException) {
Coverage.ifStart(679)
                throw e
            }
Coverage.statementStart(680)
        }
Coverage.statementStart(681)
    }
}
