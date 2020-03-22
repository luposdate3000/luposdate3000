package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(846)
            require(a != b)
            Coverage.statementStart(847)
            if (a < b) {
                Coverage.ifStart(848)
                return -1
            }
            Coverage.statementStart(849)
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
        Coverage.funStart(850)
        try {
            Coverage.statementStart(851)
            val res = buffer.getNextInt() % max
            Coverage.statementStart(852)
            if (positiveOnly && res < 0) {
                Coverage.ifStart(853)
                return -res
            }
            Coverage.statementStart(854)
            return res
        } catch (e: Throwable) {
            Coverage.statementStart(855)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
        Coverage.statementStart(857)
    }

    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }

    fun log(s: String) {
        Coverage.funStart(858)
        if (verbose) {
            Coverage.ifStart(859)
            println(s)
            Coverage.statementStart(860)
        }
        Coverage.statementStart(861)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(862)
        var expectException = false
        Coverage.statementStart(863)
        log("start")
        Coverage.statementStart(864)
        try {
            Coverage.statementStart(865)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            Coverage.statementStart(866)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(867)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            Coverage.statementStart(868)
            while (true) {
                Coverage.whileLoopStart(869)
                expectException = false
                Coverage.statementStart(870)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                Coverage.statementStart(871)
                val helper = helpers[helperIdx]
                Coverage.statementStart(872)
                log("helperIdx $helperIdx ${helper.vektor}")
                Coverage.statementStart(873)
                log(helper.kotlinList.toString())
                Coverage.statementStart(874)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                Coverage.statementStart(875)
                log("func $func")
                Coverage.statementStart(876)
                when (func) {
                    0 -> {
                        Coverage.whenCaseStart(877)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(878)
                        log("count $count")
                        Coverage.statementStart(879)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
                        Coverage.statementStart(880)
                        helper.vektor.skipPos(count)
                        Coverage.statementStart(881)
                        helper.pos += count
                        Coverage.statementStart(882)
                    }
                    1 -> {
                        Coverage.whenCaseStart(883)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(884)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(885)
                            count = helper.pos - helper.size
                            Coverage.statementStart(886)
                        }
                        Coverage.statementStart(887)
                        log("count $count")
                        Coverage.statementStart(888)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
                        Coverage.statementStart(889)
                        helper.vektor.skipSize(count)
                        Coverage.statementStart(890)
                        helper.size += count
                        Coverage.statementStart(891)
                        if (count > 0) {
                            Coverage.ifStart(892)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(893)
                                helper.kotlinList.add(DONT_CARE_VALUE)
                                Coverage.statementStart(894)
                            }
                            Coverage.statementStart(895)
                        } else {
                            Coverage.ifStart(896)
                            if (!expectException) {
                                Coverage.ifStart(897)
                                for (i in 0 until -count) {
                                    Coverage.forLoopStart(898)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
                                    Coverage.statementStart(899)
                                }
                                Coverage.statementStart(900)
                            }
                            Coverage.statementStart(901)
                        }
                        Coverage.statementStart(902)
                    }
                    2 -> {
                        Coverage.whenCaseStart(903)
                        helper.vektor.backupPosition()
                        Coverage.statementStart(904)
                        helper.backup = helper.pos
                        Coverage.statementStart(905)
                    }
                    3 -> {
                        Coverage.whenCaseStart(906)
                        expectException = helper.backup > helper.size
                        Coverage.statementStart(907)
                        helper.vektor.restorePosition()
                        Coverage.statementStart(908)
                        helper.pos = helper.backup
                        Coverage.statementStart(909)
                    }
                    4 -> {
                        Coverage.whenCaseStart(910)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(911)
                        val c = helper.vektor.current()
                        Coverage.statementStart(912)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                        Coverage.statementStart(913)
                    }
                    5 -> {
                        Coverage.whenCaseStart(914)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(915)
                        val c = helper.vektor.next()
                        Coverage.statementStart(916)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                        Coverage.statementStart(917)
                        helper.pos++
                        Coverage.statementStart(918)
                    }
                    6 -> {
                        Coverage.whenCaseStart(919)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
                        Coverage.statementStart(920)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
                        Coverage.statementStart(921)
                    }
                    7 -> {
                        Coverage.whenCaseStart(922)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
                        Coverage.statementStart(923)
                    }
                    8 -> {
                        Coverage.whenCaseStart(924)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
                        Coverage.statementStart(925)
                    }
                    9 -> {
                        Coverage.whenCaseStart(926)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
                        Coverage.statementStart(927)
                    }
                    10 -> {
                        Coverage.whenCaseStart(928)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(929)
                        log("count $count")
                        Coverage.statementStart(930)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        Coverage.statementStart(931)
                        log("value $value")
                        Coverage.statementStart(932)
                        expectException = count <= 0 || !helper.vektor.canAppend()
                        Coverage.statementStart(933)
                        helper.vektor.append(value, count)
                        Coverage.statementStart(934)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(935)
                            helper.kotlinList.add(value)
                            Coverage.statementStart(936)
                        }
                        Coverage.statementStart(937)
                        helper.size += count
                        Coverage.statementStart(938)
                    }
                    11 -> {
                        Coverage.whenCaseStart(939)
                        var same = 0
                        Coverage.statementStart(940)
                        var lastsame = -1
                        Coverage.statementStart(941)
                        var helperValue = DONT_CARE_VALUE
                        Coverage.statementStart(942)
                        val tmp = helper.vektor.sameElements()
                        Coverage.statementStart(943)
                        while (same != lastsame && same != tmp) {
                            Coverage.whileLoopStart(944)
                            if (helperValue == DONT_CARE_VALUE) {
                                Coverage.ifStart(945)
                                helperValue = helper.kotlinList[helper.pos]
                                Coverage.statementStart(946)
                            }
                            Coverage.statementStart(947)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
                                Coverage.whileLoopStart(948)
                                same++
                                Coverage.statementStart(949)
                            }
                            Coverage.statementStart(950)
                            if (same == tmp) {
                                Coverage.ifStart(951)
                                break
                            }
                            Coverage.statementStart(952)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
                                Coverage.whileLoopStart(953)
                                same++
                                Coverage.statementStart(954)
                            }
                            Coverage.statementStart(955)
                            log("same $same $tmp")
                            Coverage.statementStart(956)
                        }
                        Coverage.statementStart(957)
                        require(same == tmp)
                        Coverage.statementStart(958)
                    }
                    12 -> {
                        Coverage.whenCaseStart(959)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(960)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(961)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
                        Coverage.statementStart(962)
                        log(helper2.kotlinList.toString())
                        Coverage.statementStart(963)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(964)
                        log("count $count")
                        Coverage.statementStart(965)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
                        Coverage.statementStart(966)
                        helper2.vektor.copy(helper.vektor, count)
                        Coverage.statementStart(967)
                        expectException = helper.vektor.availableRead() < count || count <= 0
                        Coverage.statementStart(968)
                        for (i in helper.pos until helper.pos + count) {
                            Coverage.forLoopStart(969)
                            helper2.kotlinList.add(helper.kotlinList[i])
                            Coverage.statementStart(970)
                        }
                        Coverage.statementStart(971)
                        helper2.size += count
                        Coverage.statementStart(972)
                        helper.pos += count
                        Coverage.statementStart(973)
                    }
                    13 -> {
                        Coverage.whenCaseStart(974)
                        val first = nextRandom(buffer, helper.size, true)
                        Coverage.statementStart(975)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
                        Coverage.statementStart(976)
                        var last = first
                        Coverage.statementStart(977)
                        helper.vektor.skipPos(-helper.pos)
                        Coverage.statementStart(978)
                        helper.pos = 0
                        Coverage.statementStart(979)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
                            Coverage.ifStart(980)
                            helper.vektor.skipPos(last)
                            Coverage.statementStart(981)
                            helper.kotlinList[last] = helper.vektor.current()
                            Coverage.statementStart(982)
                            helper.vektor.skipPos(-last)
                            Coverage.statementStart(983)
                        }
                        Coverage.statementStart(984)
                        while (last < lastTarget) {
                            Coverage.whileLoopStart(985)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
                                Coverage.ifStart(986)
                                helper.vektor.skipPos(last + 1)
                                Coverage.statementStart(987)
                                helper.kotlinList[last + 1] = helper.vektor.current()
                                Coverage.statementStart(988)
                                helper.vektor.skipPos(-last - 1)
                                Coverage.statementStart(989)
                            }
                            Coverage.statementStart(990)
                            val lastValue = helper.kotlinList[last]
                            Coverage.statementStart(991)
                            val thisValue = helper.kotlinList[last + 1]
                            Coverage.statementStart(992)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
                                Coverage.ifStart(993)
                                break
                            }
                            Coverage.statementStart(994)
                            last++
                            Coverage.statementStart(995)
                        }
                        Coverage.statementStart(996)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
                        Coverage.statementStart(997)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        Coverage.statementStart(998)
                        log("first $first")
                        Coverage.statementStart(999)
                        log("last $last")
                        Coverage.statementStart(1000)
                        log("value $value")
                        Coverage.statementStart(1001)
                        log("count $count")
                        Coverage.statementStart(1002)
                        val listA = mutableListOf<Value>()
                        Coverage.statementStart(1003)
                        val listB = mutableListOf<Value>()
                        Coverage.statementStart(1004)
                        val listC = mutableListOf<Value>()
                        Coverage.statementStart(1005)
                        for (i in 0 until first) {
                            Coverage.forLoopStart(1006)
                            listA.add(helper.kotlinList[i])
                            Coverage.statementStart(1007)
                        }
                        Coverage.statementStart(1008)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(1009)
                            listB.add(value)
                            Coverage.statementStart(1010)
                        }
                        Coverage.statementStart(1011)
                        for (i in first until last) {
                            Coverage.forLoopStart(1012)
                            listB.add(helper.kotlinList[i])
                            Coverage.statementStart(1013)
                        }
                        Coverage.statementStart(1014)
                        for (i in last until helper.kotlinList.size) {
                            Coverage.forLoopStart(1015)
                            listC.add(helper.kotlinList[i])
                            Coverage.statementStart(1016)
                        }
                        Coverage.statementStart(1017)
                        log("inA $listA")
                        Coverage.statementStart(1018)
                        log("inB $listB")
                        Coverage.statementStart(1019)
                        listB.sort()
                        Coverage.statementStart(1020)
                        log("inB2 $listB")
                        Coverage.statementStart(1021)
                        log("inC $listC")
                        Coverage.statementStart(1022)
                        log("size " + listA.size)
                        Coverage.statementStart(1023)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
                        Coverage.statementStart(1024)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
                        Coverage.statementStart(1025)
                        log("${helper.vektor}")
                        Coverage.statementStart(1026)
                        log("asize ${listA.size}")
                        Coverage.statementStart(1027)
                        log("bsize ${listB.size}")
                        Coverage.statementStart(1028)
                        log("csize ${listC.size}")
                        Coverage.statementStart(1029)
                        log("ret $ret")
                        Coverage.statementStart(1030)
                        require(ret.second >= count)
                        Coverage.statementStart(1031)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
                        Coverage.statementStart(1032)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
                        Coverage.statementStart(1033)
                        listA.addAll(listB)
                        Coverage.statementStart(1034)
                        listA.addAll(listC)
                        Coverage.statementStart(1035)
                        for (i in ret.first until ret.first + ret.second) {
                            Coverage.forLoopStart(1036)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
                            Coverage.statementStart(1037)
                        }
                        Coverage.statementStart(1038)
                        helper.kotlinList = listA
                        Coverage.statementStart(1039)
                        helper.size += count
                        Coverage.statementStart(1040)
                    }
                    else -> {
/*Coverage Unreachable*/
                        Coverage.statementStart(1042)
                    }
                }
                Coverage.statementStart(1043)
                if (expectException) {
                    Coverage.ifStart(1044)
                    throw Exception("there should be an exception")
                }
                Coverage.statementStart(1045)
                log("" + expectException)
                Coverage.statementStart(1046)
                log("helperIdx $helperIdx ${helper.vektor}")
                Coverage.statementStart(1047)
                log(helper.kotlinList.toString())
                Coverage.statementStart(1048)
                log("\n")
                Coverage.statementStart(1049)
                for (helper in helpers) {
                    Coverage.forLoopStart(1050)
                    helper.vektor.skipPos(-helper.pos)
                    Coverage.statementStart(1051)
                    for (i in 0 until helper.size) {
                        Coverage.forLoopStart(1052)
                        val v = helper.vektor.next()
                        Coverage.statementStart(1053)
                        var l = i - 5
                        Coverage.statementStart(1054)
                        var r = i + 6
                        Coverage.statementStart(1055)
                        if (l < 0) {
                            Coverage.ifStart(1056)
                            l = 0
                            Coverage.statementStart(1057)
                        }
                        Coverage.statementStart(1058)
                        if (r > helper.kotlinList.size) {
                            Coverage.ifStart(1059)
                            r = helper.kotlinList.size
                            Coverage.statementStart(1060)
                        }
                        Coverage.statementStart(1061)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
                        Coverage.statementStart(1062)
                    }
                    Coverage.statementStart(1063)
                    helper.vektor.skipPos(helper.pos - helper.size)
                    Coverage.statementStart(1064)
                    log(helper.vektor.toString())
                    Coverage.statementStart(1065)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
                    Coverage.statementStart(1066)
                }
                Coverage.statementStart(1067)
                log("\n")
                Coverage.statementStart(1068)
            }
/*Coverage Unreachable*/
            Coverage.statementStart(1070)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(1071)
        } catch (e: Throwable) {
            Coverage.statementStart(1072)
            if (!expectException) {
/*Coverage Unreachable*/
                Coverage.statementStart(1074)
                throw e
            }
            Coverage.statementStart(1075)
        }
        Coverage.statementStart(1076)
    }
}
