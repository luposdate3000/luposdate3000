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
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
Coverage.funStart(857)
        if (verbose) {
Coverage.ifStart(858)
            println(s)
Coverage.statementStart(859)
        }
Coverage.statementStart(860)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(861)
        var expectException = false
Coverage.statementStart(862)
        log("start")
Coverage.statementStart(863)
        try {
Coverage.statementStart(864)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(865)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(866)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(867)
            while (true) {
Coverage.whileLoopStart(868)
                expectException = false
Coverage.statementStart(869)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(870)
                val helper = helpers[helperIdx]
Coverage.statementStart(871)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(872)
                log(helper.kotlinList.toString())
Coverage.statementStart(873)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(874)
                log("func $func")
Coverage.statementStart(875)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(876)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(877)
                        log("count $count")
Coverage.statementStart(878)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(879)
                        helper.vektor.skipPos(count)
Coverage.statementStart(880)
                        helper.pos += count
Coverage.statementStart(881)
                    }
                    1 -> {
Coverage.whenCaseStart(882)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(883)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(884)
                            count = helper.pos - helper.size
Coverage.statementStart(885)
                        }
Coverage.statementStart(886)
                        log("count $count")
Coverage.statementStart(887)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(888)
                        helper.vektor.skipSize(count)
Coverage.statementStart(889)
                        helper.size += count
Coverage.statementStart(890)
                        if (count > 0) {
Coverage.ifStart(891)
                            for (i in 0 until count) {
Coverage.forLoopStart(892)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(893)
                            }
Coverage.statementStart(894)
                        } else {
Coverage.ifStart(895)
                            if (!expectException) {
Coverage.ifStart(896)
                                for (i in 0 until -count) {
Coverage.forLoopStart(897)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(898)
                                }
Coverage.statementStart(899)
                            }
Coverage.statementStart(900)
                        }
Coverage.statementStart(901)
                    }
                    2 -> {
Coverage.whenCaseStart(902)
                        helper.vektor.backupPosition()
Coverage.statementStart(903)
                        helper.backup = helper.pos
Coverage.statementStart(904)
                    }
                    3 -> {
Coverage.whenCaseStart(905)
                        expectException = helper.backup > helper.size
Coverage.statementStart(906)
                        helper.vektor.restorePosition()
Coverage.statementStart(907)
                        helper.pos = helper.backup
Coverage.statementStart(908)
                    }
                    4 -> {
Coverage.whenCaseStart(909)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(910)
                        val c = helper.vektor.current()
Coverage.statementStart(911)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(912)
                    }
                    5 -> {
Coverage.whenCaseStart(913)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(914)
                        val c = helper.vektor.next()
Coverage.statementStart(915)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(916)
                        helper.pos++
Coverage.statementStart(917)
                    }
                    6 -> {
Coverage.whenCaseStart(918)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(919)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(920)
                    }
                    7 -> {
Coverage.whenCaseStart(921)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(922)
                    }
                    8 -> {
Coverage.whenCaseStart(923)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(924)
                    }
                    9 -> {
Coverage.whenCaseStart(925)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(926)
                    }
                    10 -> {
Coverage.whenCaseStart(927)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(928)
                        log("count $count")
Coverage.statementStart(929)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(930)
                        log("value $value")
Coverage.statementStart(931)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(932)
                        helper.vektor.append(value, count)
Coverage.statementStart(933)
                        for (i in 0 until count) {
Coverage.forLoopStart(934)
                            helper.kotlinList.add(value)
Coverage.statementStart(935)
                        }
Coverage.statementStart(936)
                        helper.size += count
Coverage.statementStart(937)
                    }
                    11 -> {
Coverage.whenCaseStart(938)
                        var same = 0
Coverage.statementStart(939)
                        var lastsame = -1
Coverage.statementStart(940)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(941)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(942)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(943)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(944)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(945)
                            }
Coverage.statementStart(946)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(947)
                                same++
Coverage.statementStart(948)
                            }
Coverage.statementStart(949)
                            if (same == tmp) {
Coverage.ifStart(950)
                                break
                            }
Coverage.statementStart(951)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(952)
                                same++
Coverage.statementStart(953)
                            }
Coverage.statementStart(954)
                            log("same $same $tmp")
Coverage.statementStart(955)
                        }
Coverage.statementStart(956)
                        require(same == tmp)
Coverage.statementStart(957)
                    }
                    12 -> {
Coverage.whenCaseStart(958)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(959)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(960)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(961)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(962)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(963)
                        log("count $count")
Coverage.statementStart(964)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(965)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(966)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(967)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(968)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(969)
                        }
Coverage.statementStart(970)
                        helper2.size += count
Coverage.statementStart(971)
                        helper.pos += count
Coverage.statementStart(972)
                    }
                    13 -> {
Coverage.whenCaseStart(973)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(974)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(975)
                        var last = first
Coverage.statementStart(976)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(977)
                        helper.pos = 0
Coverage.statementStart(978)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(979)
                            helper.vektor.skipPos(last)
Coverage.statementStart(980)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(981)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(982)
                        }
Coverage.statementStart(983)
                        while (last < lastTarget) {
Coverage.whileLoopStart(984)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(985)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(986)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(987)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(988)
                            }
Coverage.statementStart(989)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(990)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(991)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(992)
                                break
                            }
Coverage.statementStart(993)
                            last++
Coverage.statementStart(994)
                        }
Coverage.statementStart(995)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(996)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(997)
                        log("first $first")
Coverage.statementStart(998)
                        log("last $last")
Coverage.statementStart(999)
                        log("value $value")
Coverage.statementStart(1000)
                        log("count $count")
Coverage.statementStart(1001)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(1002)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(1003)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(1004)
                        for (i in 0 until first) {
Coverage.forLoopStart(1005)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(1006)
                        }
Coverage.statementStart(1007)
                        for (i in 0 until count) {
Coverage.forLoopStart(1008)
                            listB.add(value)
Coverage.statementStart(1009)
                        }
Coverage.statementStart(1010)
                        for (i in first until last) {
Coverage.forLoopStart(1011)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1012)
                        }
Coverage.statementStart(1013)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(1014)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1015)
                        }
Coverage.statementStart(1016)
                        log("inA $listA")
Coverage.statementStart(1017)
                        log("inB $listB")
Coverage.statementStart(1018)
                        listB.sort()
Coverage.statementStart(1019)
                        log("inB2 $listB")
Coverage.statementStart(1020)
                        log("inC $listC")
Coverage.statementStart(1021)
                        log("size " + listA.size)
Coverage.statementStart(1022)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1023)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1024)
                        log("${helper.vektor}")
Coverage.statementStart(1025)
                        log("asize ${listA.size}")
Coverage.statementStart(1026)
                        log("bsize ${listB.size}")
Coverage.statementStart(1027)
                        log("csize ${listC.size}")
Coverage.statementStart(1028)
                        log("ret $ret")
Coverage.statementStart(1029)
                        require(ret.second >= count)
Coverage.statementStart(1030)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1031)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1032)
                        listA.addAll(listB)
Coverage.statementStart(1033)
                        listA.addAll(listC)
Coverage.statementStart(1034)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1035)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1036)
                        }
Coverage.statementStart(1037)
                        helper.kotlinList = listA
Coverage.statementStart(1038)
                        helper.size += count
Coverage.statementStart(1039)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(1041)
                if (expectException) {
Coverage.ifStart(1042)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1043)
                log("" + expectException)
Coverage.statementStart(1044)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1045)
                log(helper.kotlinList.toString())
Coverage.statementStart(1046)
                log("\n")
Coverage.statementStart(1047)
                for (helper in helpers) {
Coverage.forLoopStart(1048)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1049)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1050)
                        val v = helper.vektor.next()
Coverage.statementStart(1051)
                        var l = i - 5
Coverage.statementStart(1052)
                        var r = i + 6
Coverage.statementStart(1053)
                        if (l < 0) {
Coverage.ifStart(1054)
                            l = 0
Coverage.statementStart(1055)
                        }
Coverage.statementStart(1056)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1057)
                            r = helper.kotlinList.size
Coverage.statementStart(1058)
                        }
Coverage.statementStart(1059)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1060)
                    }
Coverage.statementStart(1061)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1062)
                    log(helper.vektor.toString())
Coverage.statementStart(1063)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1064)
                }
Coverage.statementStart(1065)
                log("\n")
Coverage.statementStart(1066)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1068)
        } catch (e: Throwable) {
Coverage.statementStart(1069)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(1071)
        }
Coverage.statementStart(1072)
    }
}
