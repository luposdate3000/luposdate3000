package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(836)
            require(a != b)
Coverage.statementStart(837)
            if (a < b) {
Coverage.ifStart(838)
                return -1
            }
Coverage.statementStart(839)
            return 1
        }
    }
    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_DISTINCT_VALUES = 5
    val MAX_CAPACITY = 50
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 3
    val verbose = false
    class NoMoreRandomException() : Exception("")
    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
Coverage.funStart(840)
        try {
Coverage.statementStart(841)
            val res = buffer.getNextInt() % max
Coverage.statementStart(842)
            if (positiveOnly && res < 0) {
Coverage.ifStart(843)
                return -res
            }
Coverage.statementStart(844)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(845)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
Coverage.funStart(847)
        if (verbose) {
Coverage.ifStart(848)
            println(s)
Coverage.statementStart(849)
        }
Coverage.statementStart(850)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(851)
        var expectException = false
Coverage.statementStart(852)
        log("start")
Coverage.statementStart(853)
        try {
Coverage.statementStart(854)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(855)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(856)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(857)
            while (true) {
Coverage.whileLoopStart(858)
                expectException = false
Coverage.statementStart(859)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(860)
                val helper = helpers[helperIdx]
Coverage.statementStart(861)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(862)
                log(helper.kotlinList.toString())
Coverage.statementStart(863)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(864)
                log("func $func")
Coverage.statementStart(865)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(866)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(867)
                        log("count $count")
Coverage.statementStart(868)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(869)
                        helper.vektor.skipPos(count)
Coverage.statementStart(870)
                        helper.pos += count
Coverage.statementStart(871)
                    }
                    1 -> {
Coverage.whenCaseStart(872)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(873)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(874)
                            count = helper.pos - helper.size
Coverage.statementStart(875)
                        }
Coverage.statementStart(876)
                        log("count $count")
Coverage.statementStart(877)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(878)
                        helper.vektor.skipSize(count)
Coverage.statementStart(879)
                        helper.size += count
Coverage.statementStart(880)
                        if (count > 0) {
Coverage.ifStart(881)
                            for (i in 0 until count) {
Coverage.forLoopStart(882)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(883)
                            }
Coverage.statementStart(884)
                        } else {
Coverage.ifStart(885)
                            if (!expectException) {
Coverage.ifStart(886)
                                for (i in 0 until -count) {
Coverage.forLoopStart(887)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(888)
                                }
Coverage.statementStart(889)
                            }
Coverage.statementStart(890)
                        }
Coverage.statementStart(891)
                    }
                    2 -> {
Coverage.whenCaseStart(892)
                        helper.vektor.backupPosition()
Coverage.statementStart(893)
                        helper.backup = helper.pos
Coverage.statementStart(894)
                    }
                    3 -> {
Coverage.whenCaseStart(895)
                        expectException = helper.backup > helper.size
Coverage.statementStart(896)
                        helper.vektor.restorePosition()
Coverage.statementStart(897)
                        helper.pos = helper.backup
Coverage.statementStart(898)
                    }
                    4 -> {
Coverage.whenCaseStart(899)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(900)
                        val c = helper.vektor.current()
Coverage.statementStart(901)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(902)
                    }
                    5 -> {
Coverage.whenCaseStart(903)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(904)
                        val c = helper.vektor.next()
Coverage.statementStart(905)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(906)
                        helper.pos++
Coverage.statementStart(907)
                    }
                    6 -> {
Coverage.whenCaseStart(908)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(909)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(910)
                    }
                    7 -> {
Coverage.whenCaseStart(911)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(912)
                    }
                    8 -> {
Coverage.whenCaseStart(913)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(914)
                    }
                    9 -> {
Coverage.whenCaseStart(915)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(916)
                    }
                    10 -> {
Coverage.whenCaseStart(917)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(918)
                        log("count $count")
Coverage.statementStart(919)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(920)
                        log("value $value")
Coverage.statementStart(921)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(922)
                        helper.vektor.append(value, count)
Coverage.statementStart(923)
                        for (i in 0 until count) {
Coverage.forLoopStart(924)
                            helper.kotlinList.add(value)
Coverage.statementStart(925)
                        }
Coverage.statementStart(926)
                        helper.size += count
Coverage.statementStart(927)
                    }
                    11 -> {
Coverage.whenCaseStart(928)
                        var same = 0
Coverage.statementStart(929)
                        var lastsame = -1
Coverage.statementStart(930)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(931)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(932)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(933)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(934)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(935)
                            }
Coverage.statementStart(936)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(937)
                                same++
Coverage.statementStart(938)
                            }
Coverage.statementStart(939)
                            if (same == tmp) {
Coverage.ifStart(940)
                                break
                            }
Coverage.statementStart(941)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(942)
                                same++
Coverage.statementStart(943)
                            }
Coverage.statementStart(944)
                            log("same $same $tmp")
Coverage.statementStart(945)
                        }
Coverage.statementStart(946)
                        require(same == tmp)
Coverage.statementStart(947)
                    }
                    12 -> {
Coverage.whenCaseStart(948)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(949)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(950)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(951)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(952)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(953)
                        log("count $count")
Coverage.statementStart(954)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(955)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(956)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(957)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(958)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(959)
                        }
Coverage.statementStart(960)
                        helper2.size += count
Coverage.statementStart(961)
                        helper.pos += count
Coverage.statementStart(962)
                    }
                    13 -> {
Coverage.whenCaseStart(963)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(964)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(965)
                        var last = first
Coverage.statementStart(966)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(967)
                        helper.pos = 0
Coverage.statementStart(968)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(969)
                            helper.vektor.skipPos(last)
Coverage.statementStart(970)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(971)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(972)
                        }
Coverage.statementStart(973)
                        while (last < lastTarget) {
Coverage.whileLoopStart(974)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(975)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(976)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(977)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(978)
                            }
Coverage.statementStart(979)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(980)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(981)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(982)
                                break
                            }
Coverage.statementStart(983)
                            last++
Coverage.statementStart(984)
                        }
Coverage.statementStart(985)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(986)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(987)
                        log("first $first")
Coverage.statementStart(988)
                        log("last $last")
Coverage.statementStart(989)
                        log("value $value")
Coverage.statementStart(990)
                        log("count $count")
Coverage.statementStart(991)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(992)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(993)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(994)
                        for (i in 0 until first) {
Coverage.forLoopStart(995)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(996)
                        }
Coverage.statementStart(997)
                        for (i in 0 until count) {
Coverage.forLoopStart(998)
                            listB.add(value)
Coverage.statementStart(999)
                        }
Coverage.statementStart(1000)
                        for (i in first until last) {
Coverage.forLoopStart(1001)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1002)
                        }
Coverage.statementStart(1003)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(1004)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1005)
                        }
Coverage.statementStart(1006)
                        log("inA $listA")
Coverage.statementStart(1007)
                        log("inB $listB")
Coverage.statementStart(1008)
                        listB.sort()
Coverage.statementStart(1009)
                        log("inB2 $listB")
Coverage.statementStart(1010)
                        log("inC $listC")
Coverage.statementStart(1011)
                        log("size " + listA.size)
Coverage.statementStart(1012)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1013)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1014)
                        log("${helper.vektor}")
Coverage.statementStart(1015)
                        log("asize ${listA.size}")
Coverage.statementStart(1016)
                        log("bsize ${listB.size}")
Coverage.statementStart(1017)
                        log("csize ${listC.size}")
Coverage.statementStart(1018)
                        log("ret $ret")
Coverage.statementStart(1019)
                        require(ret.second >= count)
Coverage.statementStart(1020)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1021)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1022)
                        listA.addAll(listB)
Coverage.statementStart(1023)
                        listA.addAll(listC)
Coverage.statementStart(1024)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1025)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1026)
                        }
Coverage.statementStart(1027)
                        helper.kotlinList = listA
Coverage.statementStart(1028)
                        helper.size += count
Coverage.statementStart(1029)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(1031)
                if (expectException) {
Coverage.ifStart(1032)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1033)
                log("" + expectException)
Coverage.statementStart(1034)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1035)
                log(helper.kotlinList.toString())
Coverage.statementStart(1036)
                log("\n")
Coverage.statementStart(1037)
                for (helper in helpers) {
Coverage.forLoopStart(1038)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1039)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1040)
                        val v = helper.vektor.next()
Coverage.statementStart(1041)
                        var l = i - 5
Coverage.statementStart(1042)
                        var r = i + 6
Coverage.statementStart(1043)
                        if (l < 0) {
Coverage.ifStart(1044)
                            l = 0
Coverage.statementStart(1045)
                        }
Coverage.statementStart(1046)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1047)
                            r = helper.kotlinList.size
Coverage.statementStart(1048)
                        }
Coverage.statementStart(1049)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1050)
                    }
Coverage.statementStart(1051)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1052)
                    log(helper.vektor.toString())
Coverage.statementStart(1053)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1054)
                }
Coverage.statementStart(1055)
                log("\n")
Coverage.statementStart(1056)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1058)
        } catch (e: Throwable) {
Coverage.statementStart(1059)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(1061)
        }
Coverage.statementStart(1062)
    }
}
