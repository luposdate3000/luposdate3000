package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(824)
            require(a != b)
Coverage.statementStart(825)
            if (a < b) {
Coverage.ifStart(826)
                return -1
            }
Coverage.statementStart(827)
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
Coverage.funStart(828)
        try {
Coverage.statementStart(829)
            val res = buffer.getNextInt() % max
Coverage.statementStart(830)
            if (positiveOnly && res < 0) {
Coverage.ifStart(831)
                return -res
            }
Coverage.statementStart(832)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(833)
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
Coverage.funStart(834)
        if (verbose) {
Coverage.ifStart(835)
            println(s)
Coverage.statementStart(836)
        }
Coverage.statementStart(837)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(838)
        var expectException = false
Coverage.statementStart(839)
        log("start")
Coverage.statementStart(840)
        try {
Coverage.statementStart(841)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(842)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(843)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(844)
            while (true) {
Coverage.whileLoopStart(845)
                expectException = false
Coverage.statementStart(846)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(847)
                val helper = helpers[helperIdx]
Coverage.statementStart(848)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(849)
                log(helper.kotlinList.toString())
Coverage.statementStart(850)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(851)
                log("func $func")
Coverage.statementStart(852)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(853)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(854)
                        log("count $count")
Coverage.statementStart(855)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(856)
                        helper.vektor.skipPos(count)
Coverage.statementStart(857)
                        helper.pos += count
Coverage.statementStart(858)
                    }
                    1 -> {
Coverage.whenCaseStart(859)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(860)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(861)
                            count = helper.pos - helper.size
Coverage.statementStart(862)
                        }
Coverage.statementStart(863)
                        log("count $count")
Coverage.statementStart(864)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(865)
                        helper.vektor.skipSize(count)
Coverage.statementStart(866)
                        helper.size += count
Coverage.statementStart(867)
                        if (count > 0) {
Coverage.ifStart(868)
                            for (i in 0 until count) {
Coverage.forLoopStart(869)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(870)
                            }
Coverage.statementStart(871)
                        } else {
Coverage.ifStart(872)
                            if (!expectException) {
Coverage.ifStart(873)
                                for (i in 0 until -count) {
Coverage.forLoopStart(874)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(875)
                                }
Coverage.statementStart(876)
                            }
Coverage.statementStart(877)
                        }
Coverage.statementStart(878)
                    }
                    2 -> {
Coverage.whenCaseStart(879)
                        helper.vektor.backupPosition()
Coverage.statementStart(880)
                        helper.backup = helper.pos
Coverage.statementStart(881)
                    }
                    3 -> {
Coverage.whenCaseStart(882)
                        expectException = helper.backup > helper.size
Coverage.statementStart(883)
                        helper.vektor.restorePosition()
Coverage.statementStart(884)
                        helper.pos = helper.backup
Coverage.statementStart(885)
                    }
                    4 -> {
Coverage.whenCaseStart(886)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(887)
                        val c = helper.vektor.current()
Coverage.statementStart(888)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(889)
                    }
                    5 -> {
Coverage.whenCaseStart(890)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(891)
                        val c = helper.vektor.next()
Coverage.statementStart(892)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(893)
                        helper.pos++
Coverage.statementStart(894)
                    }
                    6 -> {
Coverage.whenCaseStart(895)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(896)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(897)
                    }
                    7 -> {
Coverage.whenCaseStart(898)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(899)
                    }
                    8 -> {
Coverage.whenCaseStart(900)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(901)
                    }
                    9 -> {
Coverage.whenCaseStart(902)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(903)
                    }
                    10 -> {
Coverage.whenCaseStart(904)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(905)
                        log("count $count")
Coverage.statementStart(906)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(907)
                        log("value $value")
Coverage.statementStart(908)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(909)
                        helper.vektor.append(value, count)
Coverage.statementStart(910)
                        for (i in 0 until count) {
Coverage.forLoopStart(911)
                            helper.kotlinList.add(value)
Coverage.statementStart(912)
                        }
Coverage.statementStart(913)
                        helper.size += count
Coverage.statementStart(914)
                    }
                    11 -> {
Coverage.whenCaseStart(915)
                        var same = 0
Coverage.statementStart(916)
                        var lastsame = -1
Coverage.statementStart(917)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(918)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(919)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(920)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(921)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(922)
                            }
Coverage.statementStart(923)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(924)
                                same++
Coverage.statementStart(925)
                            }
Coverage.statementStart(926)
                            if (same == tmp) {
Coverage.ifStart(927)
                                break
                            }
Coverage.statementStart(928)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(929)
                                same++
Coverage.statementStart(930)
                            }
Coverage.statementStart(931)
                            log("same $same $tmp")
Coverage.statementStart(932)
                        }
Coverage.statementStart(933)
                        require(same == tmp)
Coverage.statementStart(934)
                    }
                    12 -> {
Coverage.whenCaseStart(935)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(936)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(937)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(938)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(939)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(940)
                        log("count $count")
Coverage.statementStart(941)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(942)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(943)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(944)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(945)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(946)
                        }
Coverage.statementStart(947)
                        helper2.size += count
Coverage.statementStart(948)
                        helper.pos += count
Coverage.statementStart(949)
                    }
                    13 -> {
Coverage.whenCaseStart(950)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(951)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(952)
                        var last = first
Coverage.statementStart(953)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(954)
                        helper.pos = 0
Coverage.statementStart(955)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(956)
                            helper.vektor.skipPos(last)
Coverage.statementStart(957)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(958)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(959)
                        }
Coverage.statementStart(960)
                        while (last < lastTarget) {
Coverage.whileLoopStart(961)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(962)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(963)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(964)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(965)
                            }
Coverage.statementStart(966)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(967)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(968)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(969)
                                break
                            }
Coverage.statementStart(970)
                            last++
Coverage.statementStart(971)
                        }
Coverage.statementStart(972)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(973)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(974)
                        log("first $first")
Coverage.statementStart(975)
                        log("last $last")
Coverage.statementStart(976)
                        log("value $value")
Coverage.statementStart(977)
                        log("count $count")
Coverage.statementStart(978)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(979)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(980)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(981)
                        for (i in 0 until first) {
Coverage.forLoopStart(982)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(983)
                        }
Coverage.statementStart(984)
                        for (i in 0 until count) {
Coverage.forLoopStart(985)
                            listB.add(value)
Coverage.statementStart(986)
                        }
Coverage.statementStart(987)
                        for (i in first until last) {
Coverage.forLoopStart(988)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(989)
                        }
Coverage.statementStart(990)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(991)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(992)
                        }
Coverage.statementStart(993)
                        log("inA $listA")
Coverage.statementStart(994)
                        log("inB $listB")
Coverage.statementStart(995)
                        listB.sort()
Coverage.statementStart(996)
                        log("inB2 $listB")
Coverage.statementStart(997)
                        log("inC $listC")
Coverage.statementStart(998)
                        log("size " + listA.size)
Coverage.statementStart(999)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1000)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1001)
                        log("${helper.vektor}")
Coverage.statementStart(1002)
                        log("asize ${listA.size}")
Coverage.statementStart(1003)
                        log("bsize ${listB.size}")
Coverage.statementStart(1004)
                        log("csize ${listC.size}")
Coverage.statementStart(1005)
                        log("ret $ret")
Coverage.statementStart(1006)
                        require(ret.second >= count)
Coverage.statementStart(1007)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1008)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1009)
                        listA.addAll(listB)
Coverage.statementStart(1010)
                        listA.addAll(listC)
Coverage.statementStart(1011)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1012)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1013)
                        }
Coverage.statementStart(1014)
                        helper.kotlinList = listA
Coverage.statementStart(1015)
                        helper.size += count
Coverage.statementStart(1016)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(1017)
                if (expectException) {
Coverage.ifStart(1018)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1019)
                log("" + expectException)
Coverage.statementStart(1020)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1021)
                log(helper.kotlinList.toString())
Coverage.statementStart(1022)
                log("\n")
Coverage.statementStart(1023)
                for (helper in helpers) {
Coverage.forLoopStart(1024)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1025)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1026)
                        val v = helper.vektor.next()
Coverage.statementStart(1027)
                        var l = i - 5
Coverage.statementStart(1028)
                        var r = i + 6
Coverage.statementStart(1029)
                        if (l < 0) {
Coverage.ifStart(1030)
                            l = 0
Coverage.statementStart(1031)
                        }
Coverage.statementStart(1032)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1033)
                            r = helper.kotlinList.size
Coverage.statementStart(1034)
                        }
Coverage.statementStart(1035)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1036)
                    }
Coverage.statementStart(1037)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1038)
                    log(helper.vektor.toString())
Coverage.statementStart(1039)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1040)
                }
Coverage.statementStart(1041)
                log("\n")
Coverage.statementStart(1042)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1043)
        } catch (e: Throwable) {
Coverage.statementStart(1044)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(1045)
        }
Coverage.statementStart(1046)
    }
}
