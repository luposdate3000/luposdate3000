package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.                                                                                                                                                Coverage
object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
                                                                                                                                                Coverage.funStart(549)
            if (a < b) {
                                                                                                                                                Coverage.ifStart(550)
                return -1
            }
                                                                                                                                                Coverage.statementStart(551)
            if (a == b) {
                                                                                                                                                Coverage.ifStart(552)
                throw Exception("dont compare equal values using comparator")
            }
                                                                                                                                                Coverage.statementStart(553)
            return 1
        }
    }
    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
                                                                                                                                                Coverage.funStart(554)
            for (i in variables.indices) {
val v=variables[i]
                                                                                                                                                Coverage.forLoopStart(555)
                if (a[v.toInt()] < b[v.toInt()]) {
                                                                                                                                                Coverage.ifStart(556)
                    return -1
                }
                                                                                                                                                Coverage.statementStart(557)
                if (a[v.toInt()] > b[v.toInt()]) {
                                                                                                                                                Coverage.ifStart(558)
                    return +1
                }
                                                                                                                                                Coverage.statementStart(559)
            }
                                                                                                                                                Coverage.statementStart(560)
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
    val verbose = true
    class NoMoreRandomException() : Exception("")
    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
//                                                                                                                                                Coverage.funStart(561)
        try {
//                                                                                                                                                Coverage.statementStart(562)
            val res = buffer.getNextInt() % max
//                                                                                                                                                Coverage.statementStart(563)
            if (positiveOnly && res < 0) {
//                                                                                                                                                Coverage.ifStart(564)
                return -res
            }
//                                                                                                                                                Coverage.statementStart(565)
            return res
        } catch (e: Throwable) {
//                                                                                                                                                Coverage.statementStart(566)
            throw NoMoreRandomException()
        }
//                                                                                                                                                Coverage.statementStart(567)
    }
    fun max(a: Int, b: Int): Int {
//                                                                                                                                                Coverage.funStart(568)
        if (a < b) {
//                                                                                                                                                Coverage.ifStart(569)
            return b
        }
//                                                                                                                                                Coverage.statementStart(570)
        return a
    }
    fun min(a: Int, b: Int): Int {
//                                                                                                                                                Coverage.funStart(571)
        if (a > b) {
//                                                                                                                                                Coverage.ifStart(572)
            return b
        }
//                                                                                                                                                Coverage.statementStart(573)
        return a
    }
    var columns = 4
    fun log(s: String) {
//                                                                                                                                                Coverage.funStart(574)
        if (verbose) {
//                                                                                                                                                Coverage.ifStart(575)
            println(s)
//                                                                                                                                                Coverage.statementStart(576)
        }
//                                                                                                                                                Coverage.statementStart(577)
    }
    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
//                                                                                                                                                Coverage.funStart(578)
        val res = StringBuilder()
//                                                                                                                                                Coverage.statementStart(579)
        res.append("[\n")
//                                                                                                                                                Coverage.statementStart(580)
        if (kotlinList.size > 0) {
//                                                                                                                                                Coverage.ifStart(581)
            var counter = 1
//                                                                                                                                                Coverage.statementStart(582)
            var idx = 1
//                                                                                                                                                Coverage.statementStart(583)
            var lastRow = kotlinList[0]
//                                                                                                                                                Coverage.statementStart(584)
            while (idx < kotlinList.size) {
//                                                                                                                                                Coverage.whileLoopStart(585)
                var row = kotlinList[idx]
//                                                                                                                                                Coverage.statementStart(586)
                var equal = true
//                                                                                                                                                Coverage.statementStart(587)
                for (i in lastRow.indices) {
//                                                                                                                                                Coverage.forLoopStart(588)
                    if (lastRow[i] != row[i]) {
//                                                                                                                                                Coverage.ifStart(589)
                        equal = false
//                                                                                                                                                Coverage.statementStart(590)
                    }
//                                                                                                                                                Coverage.statementStart(591)
                }
//                                                                                                                                                Coverage.statementStart(592)
                if (equal) {
//                                                                                                                                                Coverage.ifStart(593)
                    counter++
//                                                                                                                                                Coverage.statementStart(594)
                } else {
//                                                                                                                                                Coverage.ifStart(595)
                    res.append("${lastRow.map { it }}($counter)\n")
//                                                                                                                                                Coverage.statementStart(596)
                    lastRow = row
//                                                                                                                                                Coverage.statementStart(597)
                    counter = 1
//                                                                                                                                                Coverage.statementStart(598)
                }
//                                                                                                                                                Coverage.statementStart(599)
                idx++
//                                                                                                                                                Coverage.statementStart(600)
            }
//                                                                                                                                                Coverage.statementStart(601)
            res.append("${lastRow.map { it }}($counter)\n")
//                                                                                                                                                Coverage.statementStart(602)
        }
//                                                                                                                                                Coverage.statementStart(603)
        res.append("]\n")
//                                                                                                                                                Coverage.statementStart(604)
        return res.toString()
    }
    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
                                                                                                                                                Coverage.funStart(605)
        var tmp = chunk
                                                                                                                                                Coverage.statementStart(606)
	log(kotlinListToString(kotlinList))
                                                                                                                                                Coverage.statementStart(607)
        log("" + tmp)
                                                                                                                                                Coverage.statementStart(608)
        tmp.backupPosition()
                                                                                                                                                Coverage.statementStart(609)
        for (i in 0 until kotlinList.size) {
                                                                                                                                                Coverage.forLoopStart(610)
            while (tmp.availableRead() == 0) {
                                                                                                                                                Coverage.whileLoopStart(611)
                tmp.restorePosition()
                                                                                                                                                Coverage.statementStart(612)
                tmp = tmp.next
                                                                                                                                                Coverage.statementStart(613)
                tmp.backupPosition()
                                                                                                                                                Coverage.statementStart(614)
                log("" + tmp)
                                                                                                                                                Coverage.statementStart(615)
                if (tmp == chunk) {
                                                                                                                                                Coverage.ifStart(616)
                    break
                }
                                                                                                                                                Coverage.statementStart(617)
            }
                                                                                                                                                Coverage.statementStart(618)
            val v = tmp.nextArr()
                                                                                                                                                Coverage.statementStart(619)
            val w = kotlinList[i]
                                                                                                                                                Coverage.statementStart(620)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
                                                                                                                                                Coverage.statementStart(621)
            if (tmp.availableRead() == 0) {
                                                                                                                                                Coverage.ifStart(622)
                tmp.restorePosition()
                                                                                                                                                Coverage.statementStart(623)
                tmp = tmp.next
                                                                                                                                                Coverage.statementStart(624)
                tmp.backupPosition()
                                                                                                                                                Coverage.statementStart(625)
                log("" + tmp)
                                                                                                                                                Coverage.statementStart(626)
            }
                                                                                                                                                Coverage.statementStart(627)
        }
                                                                                                                                                Coverage.statementStart(628)
        tmp.restorePosition()
                                                                                                                                                Coverage.statementStart(629)
        require(tmp == chunk)
                                                                                                                                                Coverage.statementStart(630)
    }
    operator fun invoke(buffer: DynamicByteArray) {
                                                                                                                                                Coverage.funStart(631)
        var expectException = false
                                                                                                                                                Coverage.statementStart(632)
        log("-----------------------start")
                                                                                                                                                Coverage.statementStart(633)
        try {
                                                                                                                                                Coverage.statementStart(634)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
                                                                                                                                                Coverage.statementStart(635)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
                                                                                                                                                Coverage.statementStart(636)
            require(ResultVektor.capacity > 0)
                                                                                                                                                Coverage.statementStart(637)
            var kotlinList = mutableListOf<Array<Value>>()
                                                                                                                                                Coverage.statementStart(638)
            var resultSetDictionary = ResultSetDictionary()
                                                                                                                                                Coverage.statementStart(639)
            var resultSet = ResultSet(resultSetDictionary)
                                                                                                                                                Coverage.statementStart(640)
            for (i in 0 until columns) {
                                                                                                                                                Coverage.forLoopStart(641)
                resultSet.createVariable("name$i")
                                                                                                                                                Coverage.statementStart(642)
            }
                                                                                                                                                Coverage.statementStart(643)
            var chunk = ResultChunk(resultSet, columns)
                                                                                                                                                Coverage.statementStart(644)
            var chunkLast = chunk
                                                                                                                                                Coverage.statementStart(645)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
                                                                                                                                                Coverage.statementStart(646)
            while (true) {
                                                                                                                                                Coverage.whileLoopStart(647)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                                                                                                                                                Coverage.statementStart(648)
                log("value ${value.map { it }}")
                                                                                                                                                Coverage.statementStart(649)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
                                                                                                                                                Coverage.statementStart(650)
                log("count $count")
                                                                                                                                                Coverage.statementStart(651)
                expectException = count <= 0
                                                                                                                                                Coverage.statementStart(652)
                for (i in 0 until count) {
                                                                                                                                                Coverage.forLoopStart(653)
                    kotlinList.add(value)
                                                                                                                                                Coverage.statementStart(654)
                }
                                                                                                                                                Coverage.statementStart(655)
                if (!chunkLast.canAppend()) {
                                                                                                                                                Coverage.ifStart(656)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
                                                                                                                                                Coverage.statementStart(657)
                }
                                                                                                                                                Coverage.statementStart(658)
                chunkLast.append(value, count)
                                                                                                                                                Coverage.statementStart(659)
                val allcolumns = MutableList(columns) { it.toLong() }
                                                                                                                                                Coverage.statementStart(660)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                                                                                                                                                Coverage.statementStart(661)
                log("columns ${columns.map { it }}")
                                                                                                                                                Coverage.statementStart(662)
                val comparator = MyComparatorRow(columns)
                                                                                                                                                Coverage.statementStart(663)
                checkEquals(kotlinList, chunk, comparator)
                                                                                                                                                Coverage.statementStart(664)
		log("columns ${columns.map { it }}")
		log("beforesort"+kotlinListToString(kotlinList))
                kotlinList.sortWith(comparator)
		log("aftersort"+kotlinListToString(kotlinList))
                                                                                                                                                Coverage.statementStart(665)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
                                                                                                                                                Coverage.statementStart(666)
                chunkLast = chunk.prev
                                                                                                                                                Coverage.statementStart(667)
                checkEquals(kotlinList, chunk, comparator)
                                                                                                                                                Coverage.statementStart(668)
            }
                                                                                                                                                Coverage.statementStart(669)
        } catch (e: NoMoreRandomException) {
                                                                                                                                                Coverage.statementStart(670)
        } catch (e: Throwable) {
                                                                                                                                                Coverage.statementStart(671)
            if (!expectException) {
                                                                                                                                                Coverage.ifStart(672)
                throw e
            }
                                                                                                                                                Coverage.statementStart(673)
        }
                                                                                                                                                Coverage.statementStart(674)
    }
}
