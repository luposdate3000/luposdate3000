package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(825)
            require(a != b)
Coverage.statementStart(826)
            if (a < b) {
Coverage.ifStart(827)
                return -1
            }
Coverage.statementStart(828)
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
Coverage.funStart(829)
        try {
Coverage.statementStart(830)
            val res = buffer.getNextInt() % max
Coverage.statementStart(831)
            if (positiveOnly && res < 0) {
Coverage.ifStart(832)
                return -res
            }
Coverage.statementStart(833)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(834)
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
Coverage.funStart(835)
        if (verbose) {
Coverage.ifStart(836)
            println(s)
Coverage.statementStart(837)
        }
Coverage.statementStart(838)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(839)
        var expectException = false
Coverage.statementStart(840)
        log("start")
Coverage.statementStart(841)
        try {
Coverage.statementStart(842)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(843)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(844)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(845)
            while (true) {
Coverage.whileLoopStart(846)
                expectException = false
Coverage.statementStart(847)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(848)
                val helper = helpers[helperIdx]
Coverage.statementStart(849)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(850)
                log(helper.kotlinList.toString())
Coverage.statementStart(851)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(852)
                log("func $func")
Coverage.statementStart(853)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(854)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(855)
                        log("count $count")
Coverage.statementStart(856)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(857)
                        helper.vektor.skipPos(count)
Coverage.statementStart(858)
                        helper.pos += count
Coverage.statementStart(859)
                    }
                    1 -> {
Coverage.whenCaseStart(860)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(861)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(862)
                            count = helper.pos - helper.size
Coverage.statementStart(863)
                        }
Coverage.statementStart(864)
                        log("count $count")
Coverage.statementStart(865)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(866)
                        helper.vektor.skipSize(count)
Coverage.statementStart(867)
                        helper.size += count
Coverage.statementStart(868)
                        if (count > 0) {
Coverage.ifStart(869)
                            for (i in 0 until count) {
Coverage.forLoopStart(870)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(871)
                            }
Coverage.statementStart(872)
                        } else {
Coverage.ifStart(873)
                            if (!expectException) {
Coverage.ifStart(874)
                                for (i in 0 until -count) {
Coverage.forLoopStart(875)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(876)
                                }
Coverage.statementStart(877)
                            }
Coverage.statementStart(878)
                        }
Coverage.statementStart(879)
                    }
                    2 -> {
Coverage.whenCaseStart(880)
                        helper.vektor.backupPosition()
Coverage.statementStart(881)
                        helper.backup = helper.pos
Coverage.statementStart(882)
                    }
                    3 -> {
Coverage.whenCaseStart(883)
                        expectException = helper.backup > helper.size
Coverage.statementStart(884)
                        helper.vektor.restorePosition()
Coverage.statementStart(885)
                        helper.pos = helper.backup
Coverage.statementStart(886)
                    }
                    4 -> {
Coverage.whenCaseStart(887)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(888)
                        val c = helper.vektor.current()
Coverage.statementStart(889)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(890)
                    }
                    5 -> {
Coverage.whenCaseStart(891)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(892)
                        val c = helper.vektor.next()
Coverage.statementStart(893)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(894)
                        helper.pos++
Coverage.statementStart(895)
                    }
                    6 -> {
Coverage.whenCaseStart(896)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(897)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(898)
                    }
                    7 -> {
Coverage.whenCaseStart(899)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(900)
                    }
                    8 -> {
Coverage.whenCaseStart(901)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(902)
                    }
                    9 -> {
Coverage.whenCaseStart(903)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(904)
                    }
                    10 -> {
Coverage.whenCaseStart(905)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(906)
                        log("count $count")
Coverage.statementStart(907)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(908)
                        log("value $value")
Coverage.statementStart(909)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(910)
                        helper.vektor.append(value, count)
Coverage.statementStart(911)
                        for (i in 0 until count) {
Coverage.forLoopStart(912)
                            helper.kotlinList.add(value)
Coverage.statementStart(913)
                        }
Coverage.statementStart(914)
                        helper.size += count
Coverage.statementStart(915)
                    }
                    11 -> {
Coverage.whenCaseStart(916)
                        var same = 0
Coverage.statementStart(917)
                        var lastsame = -1
Coverage.statementStart(918)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(919)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(920)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(921)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(922)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(923)
                            }
Coverage.statementStart(924)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(925)
                                same++
Coverage.statementStart(926)
                            }
Coverage.statementStart(927)
                            if (same == tmp) {
Coverage.ifStart(928)
                                break
                            }
Coverage.statementStart(929)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(930)
                                same++
Coverage.statementStart(931)
                            }
Coverage.statementStart(932)
                            log("same $same $tmp")
Coverage.statementStart(933)
                        }
Coverage.statementStart(934)
                        require(same == tmp)
Coverage.statementStart(935)
                    }
                    12 -> {
Coverage.whenCaseStart(936)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(937)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(938)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(939)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(940)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(941)
                        log("count $count")
Coverage.statementStart(942)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(943)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(944)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(945)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(946)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(947)
                        }
Coverage.statementStart(948)
                        helper2.size += count
Coverage.statementStart(949)
                        helper.pos += count
Coverage.statementStart(950)
                    }
                    13 -> {
Coverage.whenCaseStart(951)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(952)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(953)
                        var last = first
Coverage.statementStart(954)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(955)
                        helper.pos = 0
Coverage.statementStart(956)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(957)
                            helper.vektor.skipPos(last)
Coverage.statementStart(958)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(959)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(960)
                        }
Coverage.statementStart(961)
                        while (last < lastTarget) {
Coverage.whileLoopStart(962)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(963)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(964)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(965)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(966)
                            }
Coverage.statementStart(967)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(968)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(969)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(970)
                                break
                            }
Coverage.statementStart(971)
                            last++
Coverage.statementStart(972)
                        }
Coverage.statementStart(973)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(974)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(975)
                        log("first $first")
Coverage.statementStart(976)
                        log("last $last")
Coverage.statementStart(977)
                        log("value $value")
Coverage.statementStart(978)
                        log("count $count")
Coverage.statementStart(979)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(980)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(981)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(982)
                        for (i in 0 until first) {
Coverage.forLoopStart(983)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(984)
                        }
Coverage.statementStart(985)
                        for (i in 0 until count) {
Coverage.forLoopStart(986)
                            listB.add(value)
Coverage.statementStart(987)
                        }
Coverage.statementStart(988)
                        for (i in first until last) {
Coverage.forLoopStart(989)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(990)
                        }
Coverage.statementStart(991)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(992)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(993)
                        }
Coverage.statementStart(994)
                        log("inA $listA")
Coverage.statementStart(995)
                        log("inB $listB")
Coverage.statementStart(996)
                        listB.sort()
Coverage.statementStart(997)
                        log("inB2 $listB")
Coverage.statementStart(998)
                        log("inC $listC")
Coverage.statementStart(999)
                        log("size " + listA.size)
Coverage.statementStart(1000)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1001)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1002)
                        log("${helper.vektor}")
Coverage.statementStart(1003)
                        log("asize ${listA.size}")
Coverage.statementStart(1004)
                        log("bsize ${listB.size}")
Coverage.statementStart(1005)
                        log("csize ${listC.size}")
Coverage.statementStart(1006)
                        log("ret $ret")
Coverage.statementStart(1007)
                        require(ret.second >= count)
Coverage.statementStart(1008)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1009)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1010)
                        listA.addAll(listB)
Coverage.statementStart(1011)
                        listA.addAll(listC)
Coverage.statementStart(1012)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1013)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1014)
                        }
Coverage.statementStart(1015)
                        helper.kotlinList = listA
Coverage.statementStart(1016)
                        helper.size += count
Coverage.statementStart(1017)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(1018)
                if (expectException) {
Coverage.ifStart(1019)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1020)
                log("" + expectException)
Coverage.statementStart(1021)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1022)
                log(helper.kotlinList.toString())
Coverage.statementStart(1023)
                log("\n")
Coverage.statementStart(1024)
                for (helper in helpers) {
Coverage.forLoopStart(1025)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1026)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1027)
                        val v = helper.vektor.next()
Coverage.statementStart(1028)
                        var l = i - 5
Coverage.statementStart(1029)
                        var r = i + 6
Coverage.statementStart(1030)
                        if (l < 0) {
Coverage.ifStart(1031)
                            l = 0
Coverage.statementStart(1032)
                        }
Coverage.statementStart(1033)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1034)
                            r = helper.kotlinList.size
Coverage.statementStart(1035)
                        }
Coverage.statementStart(1036)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1037)
                    }
Coverage.statementStart(1038)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1039)
                    log(helper.vektor.toString())
Coverage.statementStart(1040)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1041)
                }
Coverage.statementStart(1042)
                log("\n")
Coverage.statementStart(1043)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1044)
        } catch (e: Throwable) {
Coverage.statementStart(1045)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(1046)
        }
Coverage.statementStart(1047)
    }
}
