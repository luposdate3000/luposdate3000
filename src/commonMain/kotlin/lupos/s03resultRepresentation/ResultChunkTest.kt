package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.Coverage

object ResultChunkTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(619)
            if (a < b) {
                Coverage.ifStart(620)
                return -1
            }
            Coverage.statementStart(621)
            if (a == b) {
                Coverage.ifStart(622)
                throw Exception("dont compare equal values using comparator")
            }
            Coverage.statementStart(623)
            return 1
        }
    }

    class MyComparatorRow(val variables: Array<Variable>) : Comparator<Array<Value>> {
        override fun compare(a: Array<Value>, b: Array<Value>): Int {
            Coverage.funStart(624)
            for (i in variables.indices) {
                Coverage.forLoopStart(625)
                val v = variables[i]
                Coverage.statementStart(626)
                if (a[v.toInt()] < b[v.toInt()]) {
                    Coverage.ifStart(627)
                    return -1
                }
                Coverage.statementStart(628)
                if (a[v.toInt()] > b[v.toInt()]) {
                    Coverage.ifStart(629)
                    return +1
                }
                Coverage.statementStart(630)
            }
            Coverage.statementStart(631)
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
        Coverage.funStart(632)
//                                                                                                                                                
        Coverage.statementStart(633)
        try {
            Coverage.statementStart(634)
//                                                                                                                                                
            Coverage.statementStart(635)
            val res = buffer.getNextInt() % max
            Coverage.statementStart(636)
//                                                                                                                                                
            Coverage.statementStart(637)
            if (positiveOnly && res < 0) {
                Coverage.ifStart(638)
//                                                                                                                                                
                Coverage.statementStart(639)
                return -res
            }
            Coverage.statementStart(640)
//                                                                                                                                                
            Coverage.statementStart(641)
            return res
        } catch (e: Throwable) {
            Coverage.statementStart(642)
//                                                                                                                                                
            Coverage.statementStart(643)
            throw NoMoreRandomException()
        }
        Coverage.statementStart(644)
//                                                                                                                                                
        Coverage.statementStart(645)
    }

    fun max(a: Int, b: Int): Int {
        Coverage.funStart(646)
//                                                                                                                                                
        Coverage.statementStart(647)
        if (a < b) {
            Coverage.ifStart(648)
//                                                                                                                                                
            Coverage.statementStart(649)
            return b
        }
        Coverage.statementStart(650)
//                                                                                                                                                
        Coverage.statementStart(651)
        return a
    }

    fun min(a: Int, b: Int): Int {
        Coverage.funStart(652)
//                                                                                                                                                
        Coverage.statementStart(653)
        if (a > b) {
            Coverage.ifStart(654)
//                                                                                                                                                
            Coverage.statementStart(655)
            return b
        }
        Coverage.statementStart(656)
//                                                                                                                                                
        Coverage.statementStart(657)
        return a
    }

    var columns = 4
    fun log(s: String) {
        Coverage.funStart(658)
//                                                                                                                                                
        Coverage.statementStart(659)
        if (verbose) {
            Coverage.ifStart(660)
//                                                                                                                                                
            Coverage.statementStart(661)
            println(s)
            Coverage.statementStart(662)
//                                                                                                                                                
            Coverage.statementStart(663)
        }
        Coverage.statementStart(664)
//                                                                                                                                                
        Coverage.statementStart(665)
    }

    fun kotlinListToString(kotlinList: MutableList<Array<Value>>): String {
        Coverage.funStart(666)
//                                                                                                                                                
        Coverage.statementStart(667)
        val res = StringBuilder()
        Coverage.statementStart(668)
//                                                                                                                                                
        Coverage.statementStart(669)
        res.append("[\n")
        Coverage.statementStart(670)
//                                                                                                                                                
        Coverage.statementStart(671)
        if (kotlinList.size > 0) {
            Coverage.ifStart(672)
//                                                                                                                                                
            Coverage.statementStart(673)
            var counter = 1
            Coverage.statementStart(674)
//                                                                                                                                                
            Coverage.statementStart(675)
            var idx = 1
            Coverage.statementStart(676)
//                                                                                                                                                
            Coverage.statementStart(677)
            var lastRow = kotlinList[0]
            Coverage.statementStart(678)
//                                                                                                                                                
            Coverage.statementStart(679)
            while (idx < kotlinList.size) {
                Coverage.whileLoopStart(680)
//                                                                                                                                                
                Coverage.statementStart(681)
                var row = kotlinList[idx]
                Coverage.statementStart(682)
//                                                                                                                                                
                Coverage.statementStart(683)
                var equal = true
                Coverage.statementStart(684)
//                                                                                                                                                
                Coverage.statementStart(685)
                for (i in lastRow.indices) {
                    Coverage.forLoopStart(686)
//                                                                                                                                                
                    Coverage.statementStart(687)
                    if (lastRow[i] != row[i]) {
                        Coverage.ifStart(688)
//                                                                                                                                                
                        Coverage.statementStart(689)
                        equal = false
                        Coverage.statementStart(690)
//                                                                                                                                                
                        Coverage.statementStart(691)
                    }
                    Coverage.statementStart(692)
//                                                                                                                                                
                    Coverage.statementStart(693)
                }
                Coverage.statementStart(694)
//                                                                                                                                                
                Coverage.statementStart(695)
                if (equal) {
                    Coverage.ifStart(696)
//                                                                                                                                                
                    Coverage.statementStart(697)
                    counter++
                    Coverage.statementStart(698)
//                                                                                                                                                
                    Coverage.statementStart(699)
                } else {
                    Coverage.ifStart(700)
//                                                                                                                                                
                    Coverage.statementStart(701)
                    res.append("${lastRow.map { it }}($counter)\n")
                    Coverage.statementStart(702)
//                                                                                                                                                
                    Coverage.statementStart(703)
                    lastRow = row
                    Coverage.statementStart(704)
//                                                                                                                                                
                    Coverage.statementStart(705)
                    counter = 1
                    Coverage.statementStart(706)
//                                                                                                                                                
                    Coverage.statementStart(707)
                }
                Coverage.statementStart(708)
//                                                                                                                                                
                Coverage.statementStart(709)
                idx++
                Coverage.statementStart(710)
//                                                                                                                                                
                Coverage.statementStart(711)
            }
            Coverage.statementStart(712)
//                                                                                                                                                
            Coverage.statementStart(713)
            res.append("${lastRow.map { it }}($counter)\n")
            Coverage.statementStart(714)
//                                                                                                                                                
            Coverage.statementStart(715)
        }
        Coverage.statementStart(716)
//                                                                                                                                                
        Coverage.statementStart(717)
        res.append("]\n")
        Coverage.statementStart(718)
//                                                                                                                                                
        Coverage.statementStart(719)
        return res.toString()
    }

    fun checkEquals(kotlinList: MutableList<Array<Value>>, chunk: ResultChunk, comparator: Comparator<Array<Value>>) {
        Coverage.funStart(720)
        var tmp = chunk
        Coverage.statementStart(721)
        log(kotlinListToString(kotlinList))
        Coverage.statementStart(722)
        log("" + tmp)
        Coverage.statementStart(723)
        tmp.backupPosition()
        Coverage.statementStart(724)
        for (i in 0 until kotlinList.size) {
            Coverage.forLoopStart(725)
            while (tmp.availableRead() == 0) {
                Coverage.whileLoopStart(726)
                tmp.restorePosition()
                Coverage.statementStart(727)
                tmp = tmp.next
                Coverage.statementStart(728)
                tmp.backupPosition()
                Coverage.statementStart(729)
                log("" + tmp)
                Coverage.statementStart(730)
                if (tmp == chunk) {
                    Coverage.ifStart(731)
                    break
                }
                Coverage.statementStart(732)
            }
            Coverage.statementStart(733)
            val v = tmp.nextArr()
            Coverage.statementStart(734)
            val w = kotlinList[i]
            Coverage.statementStart(735)
            require(comparator.compare(v, w) == 0, { "$i ${v.map { it }} ${w.map { it }} \n${kotlinListToString(kotlinList)} ${tmp.prev} $tmp ${tmp.next}" })
            Coverage.statementStart(736)
            if (tmp.availableRead() == 0) {
                Coverage.ifStart(737)
                tmp.restorePosition()
                Coverage.statementStart(738)
                tmp = tmp.next
                Coverage.statementStart(739)
                tmp.backupPosition()
                Coverage.statementStart(740)
                log("" + tmp)
                Coverage.statementStart(741)
            }
            Coverage.statementStart(742)
        }
        Coverage.statementStart(743)
        tmp.restorePosition()
        Coverage.statementStart(744)
        require(tmp == chunk)
        Coverage.statementStart(745)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(746)
        var expectException = false
        Coverage.statementStart(747)
        log("-----------------------start")
        Coverage.statementStart(748)
        try {
            Coverage.statementStart(749)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            Coverage.statementStart(750)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 3, true) + 3
            Coverage.statementStart(751)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(752)
            var kotlinList = mutableListOf<Array<Value>>()
            Coverage.statementStart(753)
            var resultSetDictionary = ResultSetDictionary()
            Coverage.statementStart(754)
            var resultSet = ResultSet(resultSetDictionary)
            Coverage.statementStart(755)
            for (i in 0 until columns) {
                Coverage.forLoopStart(756)
                resultSet.createVariable("name$i")
                Coverage.statementStart(757)
            }
            Coverage.statementStart(758)
            var chunk = ResultChunk(resultSet, columns)
            Coverage.statementStart(759)
            var chunkLast = chunk
            Coverage.statementStart(760)
            var comparatorArray: Array<Comparator<Value>> = Array(columns) { MyComparatorValue() }
            Coverage.statementStart(761)
            while (true) {
                Coverage.whileLoopStart(762)
                val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                Coverage.statementStart(763)
                log("value ${value.map { it }}")
                Coverage.statementStart(764)
                var count = nextRandom(buffer, ResultVektor.capacity, false)
                Coverage.statementStart(765)
                log("count $count")
                Coverage.statementStart(766)
                expectException = count <= 0
                Coverage.statementStart(767)
                for (i in 0 until count) {
                    Coverage.forLoopStart(768)
                    kotlinList.add(value)
                    Coverage.statementStart(769)
                }
                Coverage.statementStart(770)
                if (!chunkLast.canAppend()) {
                    Coverage.ifStart(771)
                    chunkLast = ResultChunk.append(chunkLast, ResultChunk(resultSet, columns))
                    Coverage.statementStart(772)
                }
                Coverage.statementStart(773)
                chunkLast.append(value, count)
                Coverage.statementStart(774)
                val allcolumns = MutableList(columns) { it.toLong() }
                Coverage.statementStart(775)
                val columns = Array(columns) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                Coverage.statementStart(776)
                log("columns ${columns.map { it }}")
                Coverage.statementStart(777)
                val comparator = MyComparatorRow(columns)
                Coverage.statementStart(778)
                checkEquals(kotlinList, chunk, comparator)
                Coverage.statementStart(779)
                log("columns ${columns.map { it }}")
                Coverage.statementStart(780)
                log("beforesort" + kotlinListToString(kotlinList)) {
                    Coverage.forLoopStart(781)
                    kotlinList.sortWith(comparator)
                }
                Coverage.statementStart(782)
                log("aftersort" + kotlinListToString(kotlinList))
                Coverage.statementStart(783)
                chunk = ResultChunk.sort(comparatorArray, columns, chunk)
                Coverage.statementStart(784)
                chunkLast = chunk.prev
                Coverage.statementStart(785)
                checkEquals(kotlinList, chunk, comparator)
                Coverage.statementStart(786)
            }
            Coverage.statementStart(787)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(788)
        } catch (e: Throwable) {
            Coverage.statementStart(789)
            if (!expectException) {
                Coverage.ifStart(790)
                throw e
            }
            Coverage.statementStart(791)
        }
        Coverage.statementStart(792)
    }
}
