package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(883)
            if (a < b) {
Coverage.ifStart(884)
                return -1
            }
Coverage.statementStart(885)
            if (a == b) {
Coverage.ifStart(886)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(887)
            return 1
        }
    }
    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = true
    class NoMoreRandomException() : Exception("")
    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
//Coverage.funStart(888)
        try {
//Coverage.statementStart(889)
            val res = buffer.getNextInt() % max
//Coverage.statementStart(890)
            if (positiveOnly && res < 0) {
//Coverage.ifStart(891)
                return -res
            }
//Coverage.statementStart(892)
            return res
        } catch (e: Throwable) {
//Coverage.statementStart(893)
            throw NoMoreRandomException()
        }
//Coverage.statementStart(894)
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
//Coverage.funStart(895)
        if (verbose) {
//Coverage.ifStart(896)
            println(s)
//Coverage.statementStart(897)
        }
//Coverage.statementStart(898)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(899)
        var expectException = false
Coverage.statementStart(900)
        log("start")
Coverage.statementStart(901)
        try {
Coverage.statementStart(902)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(903)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(904)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(905)
            while (true) {
Coverage.whileLoopStart(906)
                expectException = false
Coverage.statementStart(907)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(908)
                val helper = helpers[helperIdx]
Coverage.statementStart(909)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(910)
                log(helper.kotlinList.toString())
Coverage.statementStart(911)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(912)
                log("func $func")
Coverage.statementStart(913)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(914)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(915)
                        log("count $count")
Coverage.statementStart(916)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(917)
                        helper.vektor.skipPos(count)
Coverage.statementStart(918)
                        helper.pos += count
Coverage.statementStart(919)
                    }
                    1 -> {
Coverage.whenCaseStart(920)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(921)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(922)
                            count = helper.pos - helper.size
Coverage.statementStart(923)
                        }
Coverage.statementStart(924)
                        log("count $count")
Coverage.statementStart(925)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(926)
                        helper.vektor.skipSize(count)
Coverage.statementStart(927)
                        helper.size += count
Coverage.statementStart(928)
                        if (count > 0) {
Coverage.ifStart(929)
                            for (i in 0 until count) {
Coverage.forLoopStart(930)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(931)
                            }
Coverage.statementStart(932)
                        } else {
Coverage.ifStart(933)
                            if (!expectException) {
Coverage.ifStart(934)
                                for (i in 0 until -count) {
Coverage.forLoopStart(935)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(936)
                                }
Coverage.statementStart(937)
                            }
Coverage.statementStart(938)
                        }
Coverage.statementStart(939)
                    }
                    2 -> {
Coverage.whenCaseStart(940)
                        helper.vektor.backupPosition()
Coverage.statementStart(941)
                        helper.backup = helper.pos
Coverage.statementStart(942)
                    }
                    3 -> {
Coverage.whenCaseStart(943)
expectException=helper.backup>helper.size
                        helper.vektor.restorePosition()
Coverage.statementStart(944)
                        helper.pos = helper.backup
Coverage.statementStart(945)
                    }
                    4 -> {
Coverage.whenCaseStart(946)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(947)
                        val c = helper.vektor.current()
Coverage.statementStart(948)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(949)
                    }
                    5 -> {
Coverage.whenCaseStart(950)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(951)
                        val c = helper.vektor.next()
Coverage.statementStart(952)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(953)
                        helper.pos++
Coverage.statementStart(954)
                    }
                    6 -> {
Coverage.whenCaseStart(955)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(956)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(957)
                    }
                    7 -> {
Coverage.whenCaseStart(958)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(959)
                    }
                    8 -> {
Coverage.whenCaseStart(960)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(961)
                    }
                    9 -> {
Coverage.whenCaseStart(962)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(963)
                    }
                    10 -> {
Coverage.whenCaseStart(964)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(965)
                        log("count $count")
Coverage.statementStart(966)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(967)
                        log("value $value")
Coverage.statementStart(968)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(969)
                        helper.vektor.append(value, count)
Coverage.statementStart(970)
                        for (i in 0 until count) {
Coverage.forLoopStart(971)
                            helper.kotlinList.add(value)
Coverage.statementStart(972)
                        }
Coverage.statementStart(973)
                        helper.size += count
Coverage.statementStart(974)
                    }
                    11 -> {
Coverage.whenCaseStart(975)
                        var same = 0
Coverage.statementStart(976)
                        var lastsame = -1
Coverage.statementStart(977)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(978)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(979)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(980)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(981)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(982)
                            }
Coverage.statementStart(983)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(984)
                                same++
Coverage.statementStart(985)
                            }
Coverage.statementStart(986)
                            if (same == tmp) {
Coverage.ifStart(987)
                                break
                            }
Coverage.statementStart(988)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(989)
                                same++
Coverage.statementStart(990)
                            }
Coverage.statementStart(991)
                            log("same $same $tmp")
Coverage.statementStart(992)
                        }
Coverage.statementStart(993)
                        require(same == tmp)
Coverage.statementStart(994)
                    }
                    12 -> {
Coverage.whenCaseStart(995)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(996)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(997)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(998)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(999)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(1000)
                        log("count $count")
Coverage.statementStart(1001)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(1002)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(1003)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(1004)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(1005)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(1006)
                        }
Coverage.statementStart(1007)
                        helper2.size += count
Coverage.statementStart(1008)
                        helper.pos += count
Coverage.statementStart(1009)
                    }
                    13 -> {
Coverage.whenCaseStart(1010)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(1011)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(1012)
                        var last = first
Coverage.statementStart(1013)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1014)
                        helper.pos = 0
Coverage.statementStart(1015)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(1016)
                            helper.vektor.skipPos(last)
Coverage.statementStart(1017)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(1018)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(1019)
                        }
Coverage.statementStart(1020)
                        while (last < lastTarget) {
Coverage.whileLoopStart(1021)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(1022)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(1023)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(1024)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(1025)
                            }
Coverage.statementStart(1026)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(1027)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(1028)
println("yyy $lastValue $thisValue")
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(1029)
                                break
                            }
Coverage.statementStart(1030)
                            last++
Coverage.statementStart(1031)
                        }
Coverage.statementStart(1032)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(1033)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(1034)
                        log("first $first")
Coverage.statementStart(1035)
                        log("last $last")
Coverage.statementStart(1036)
                        log("value $value")
Coverage.statementStart(1037)
                        log("count $count")
Coverage.statementStart(1038)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(1039)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(1040)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(1041)
                        for (i in 0 until first) {
//Coverage.forLoopStart(1042)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(1043)
                        }
Coverage.statementStart(1044)
                        for (i in 0 until count) {
//Coverage.forLoopStart(1045)
                            listB.add(value)
Coverage.statementStart(1046)
                        }
Coverage.statementStart(1047)
                        for (i in first until last) {
//Coverage.forLoopStart(1048)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1049)
                        }
Coverage.statementStart(1050)
                        for (i in last until helper.kotlinList.size) {
//Coverage.forLoopStart(1051)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1052)
                        }
Coverage.statementStart(1053)
                        log("inA $listA")
Coverage.statementStart(1054)
                        log("inB $listB")
Coverage.statementStart(1055)
                        listB.sort()
Coverage.statementStart(1056)
                        log("inB2 $listB")
Coverage.statementStart(1057)
                        log("inC $listC")
Coverage.statementStart(1058)
                        log("size " + listA.size)
Coverage.statementStart(1059)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1060)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1061)
                        log("${helper.vektor}")
Coverage.statementStart(1062)
                        log("asize ${listA.size}")
Coverage.statementStart(1063)
                        log("bsize ${listB.size}")
Coverage.statementStart(1064)
                        log("csize ${listC.size}")
Coverage.statementStart(1065)
                        log("ret $ret")
Coverage.statementStart(1066)
                        require(ret.second >= count)
Coverage.statementStart(1067)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1068)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1069)
                        listA.addAll(listB)
Coverage.statementStart(1070)
                        listA.addAll(listC)
Coverage.statementStart(1071)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1072)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE,{"$i : ${listA[i]} $value"})
Coverage.statementStart(1073)
                        }
Coverage.statementStart(1074)
                        helper.kotlinList = listA
Coverage.statementStart(1075)
                        helper.size += count
Coverage.statementStart(1076)
                    }
                    else -> {
Coverage.whenCaseStart(1077)
                        require(func < FUNCTION_COUNT)
Coverage.statementStart(1078)
                    }
                }
Coverage.statementStart(1079)
                if (expectException) {
Coverage.ifStart(1080)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1081)
                log("" + expectException)
Coverage.statementStart(1082)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1083)
                log(helper.kotlinList.toString())
Coverage.statementStart(1084)
                log("\n")
Coverage.statementStart(1085)
                for (helper in helpers) {
Coverage.forLoopStart(1086)
println("xxx${-helper.pos}")
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1087)
                    for (i in 0 until helper.size) {
//Coverage.forLoopStart(1088)
                        val v = helper.vektor.next()
//Coverage.statementStart(1089)
                        var l = i - 5
//Coverage.statementStart(1090)
                        var r = i + 6
//Coverage.statementStart(1091)
                        if (l < 0) {
//Coverage.ifStart(1092)
                            l = 0
//Coverage.statementStart(1093)
                        }
//Coverage.statementStart(1094)
                        if (r > helper.kotlinList.size) {
//Coverage.ifStart(1095)
                            r = helper.kotlinList.size
//Coverage.statementStart(1096)
                        }
//Coverage.statementStart(1097)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
//Coverage.statementStart(1098)
                    }
Coverage.statementStart(1099)
println("${helper.pos} ${helper.size}")
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1100)
                    log(helper.vektor.toString())
Coverage.statementStart(1101)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1102)
                }
Coverage.statementStart(1103)
                log("\n")
Coverage.statementStart(1104)
            }
Coverage.statementStart(1105)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1106)
        } catch (e: Throwable) {
Coverage.statementStart(1107)
            if (!expectException) {
Coverage.ifStart(1108)
                throw e
            }
Coverage.statementStart(1109)
        }
Coverage.statementStart(1110)
    }
}
