package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(910)
            if (a < b) {
Coverage.ifStart(911)
                return -1
            }
Coverage.statementStart(912)
            if (a == b) {
Coverage.ifStart(913)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(914)
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
Coverage.funStart(915)
        try {
Coverage.statementStart(916)
            val res = buffer.getNextInt() % max
Coverage.statementStart(917)
            if (positiveOnly && res < 0) {
Coverage.ifStart(918)
                return -res
            }
Coverage.statementStart(919)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(920)
            throw NoMoreRandomException()
        }
Coverage.statementStart(921)
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
Coverage.funStart(922)
        if (verbose) {
Coverage.ifStart(923)
            println(s)
Coverage.statementStart(924)
        }
Coverage.statementStart(925)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(926)
        var expectException = false
Coverage.statementStart(927)
        log("start")
Coverage.statementStart(928)
        try {
Coverage.statementStart(929)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(930)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(931)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(932)
            while (true) {
Coverage.whileLoopStart(933)
                expectException = false
Coverage.statementStart(934)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(935)
                val helper = helpers[helperIdx]
Coverage.statementStart(936)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(937)
                log(helper.kotlinList.toString())
Coverage.statementStart(938)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(939)
                log("func $func")
Coverage.statementStart(940)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(941)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(942)
                        log("count $count")
Coverage.statementStart(943)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(944)
                        helper.vektor.skipPos(count)
Coverage.statementStart(945)
                        helper.pos += count
Coverage.statementStart(946)
                    }
                    1 -> {
Coverage.whenCaseStart(947)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(948)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(949)
                            count = helper.pos - helper.size
Coverage.statementStart(950)
                        }
Coverage.statementStart(951)
                        log("count $count")
Coverage.statementStart(952)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(953)
                        helper.vektor.skipSize(count)
Coverage.statementStart(954)
                        helper.size += count
Coverage.statementStart(955)
                        if (count > 0) {
Coverage.ifStart(956)
                            for (i in 0 until count) {
Coverage.forLoopStart(957)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(958)
                            }
Coverage.statementStart(959)
                        } else {
Coverage.ifStart(960)
                            if (!expectException) {
Coverage.ifStart(961)
                                for (i in 0 until -count) {
Coverage.forLoopStart(962)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(963)
                                }
Coverage.statementStart(964)
                            }
Coverage.statementStart(965)
                        }
Coverage.statementStart(966)
                    }
                    2 -> {
Coverage.whenCaseStart(967)
                        helper.vektor.backupPosition()
Coverage.statementStart(968)
                        helper.backup = helper.pos
Coverage.statementStart(969)
                    }
                    3 -> {
Coverage.whenCaseStart(970)
                        expectException = helper.backup > helper.size
Coverage.statementStart(971)
                        helper.vektor.restorePosition()
Coverage.statementStart(972)
                        helper.pos = helper.backup
Coverage.statementStart(973)
                    }
                    4 -> {
Coverage.whenCaseStart(974)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(975)
                        val c = helper.vektor.current()
Coverage.statementStart(976)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(977)
                    }
                    5 -> {
Coverage.whenCaseStart(978)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(979)
                        val c = helper.vektor.next()
Coverage.statementStart(980)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(981)
                        helper.pos++
Coverage.statementStart(982)
                    }
                    6 -> {
Coverage.whenCaseStart(983)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(984)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(985)
                    }
                    7 -> {
Coverage.whenCaseStart(986)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(987)
                    }
                    8 -> {
Coverage.whenCaseStart(988)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(989)
                    }
                    9 -> {
Coverage.whenCaseStart(990)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(991)
                    }
                    10 -> {
Coverage.whenCaseStart(992)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(993)
                        log("count $count")
Coverage.statementStart(994)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(995)
                        log("value $value")
Coverage.statementStart(996)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(997)
                        helper.vektor.append(value, count)
Coverage.statementStart(998)
                        for (i in 0 until count) {
Coverage.forLoopStart(999)
                            helper.kotlinList.add(value)
Coverage.statementStart(1000)
                        }
Coverage.statementStart(1001)
                        helper.size += count
Coverage.statementStart(1002)
                    }
                    11 -> {
Coverage.whenCaseStart(1003)
                        var same = 0
Coverage.statementStart(1004)
                        var lastsame = -1
Coverage.statementStart(1005)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(1006)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(1007)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(1008)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(1009)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(1010)
                            }
Coverage.statementStart(1011)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(1012)
                                same++
Coverage.statementStart(1013)
                            }
Coverage.statementStart(1014)
                            if (same == tmp) {
Coverage.ifStart(1015)
                                break
                            }
Coverage.statementStart(1016)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(1017)
                                same++
Coverage.statementStart(1018)
                            }
Coverage.statementStart(1019)
                            log("same $same $tmp")
Coverage.statementStart(1020)
                        }
Coverage.statementStart(1021)
                        require(same == tmp)
Coverage.statementStart(1022)
                    }
                    12 -> {
Coverage.whenCaseStart(1023)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(1024)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(1025)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(1026)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(1027)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(1028)
                        log("count $count")
Coverage.statementStart(1029)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(1030)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(1031)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(1032)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(1033)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(1034)
                        }
Coverage.statementStart(1035)
                        helper2.size += count
Coverage.statementStart(1036)
                        helper.pos += count
Coverage.statementStart(1037)
                    }
                    13 -> {
Coverage.whenCaseStart(1038)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(1039)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(1040)
                        var last = first
Coverage.statementStart(1041)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1042)
                        helper.pos = 0
Coverage.statementStart(1043)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(1044)
                            helper.vektor.skipPos(last)
Coverage.statementStart(1045)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(1046)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(1047)
                        }
Coverage.statementStart(1048)
                        while (last < lastTarget) {
Coverage.whileLoopStart(1049)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(1050)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(1051)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(1052)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(1053)
                            }
Coverage.statementStart(1054)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(1055)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(1056)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(1057)
                                break
                            }
Coverage.statementStart(1058)
                            last++
Coverage.statementStart(1059)
                        }
Coverage.statementStart(1060)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(1061)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(1062)
                        log("first $first")
Coverage.statementStart(1063)
                        log("last $last")
Coverage.statementStart(1064)
                        log("value $value")
Coverage.statementStart(1065)
                        log("count $count")
Coverage.statementStart(1066)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(1067)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(1068)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(1069)
                        for (i in 0 until first) {
Coverage.forLoopStart(1070)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(1071)
                        }
Coverage.statementStart(1072)
                        for (i in 0 until count) {
Coverage.forLoopStart(1073)
                            listB.add(value)
Coverage.statementStart(1074)
                        }
Coverage.statementStart(1075)
                        for (i in first until last) {
Coverage.forLoopStart(1076)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1077)
                        }
Coverage.statementStart(1078)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(1079)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1080)
                        }
Coverage.statementStart(1081)
                        log("inA $listA")
Coverage.statementStart(1082)
                        log("inB $listB")
Coverage.statementStart(1083)
                        listB.sort()
Coverage.statementStart(1084)
                        log("inB2 $listB")
Coverage.statementStart(1085)
                        log("inC $listC")
Coverage.statementStart(1086)
                        log("size " + listA.size)
Coverage.statementStart(1087)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1088)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1089)
                        log("${helper.vektor}")
Coverage.statementStart(1090)
                        log("asize ${listA.size}")
Coverage.statementStart(1091)
                        log("bsize ${listB.size}")
Coverage.statementStart(1092)
                        log("csize ${listC.size}")
Coverage.statementStart(1093)
                        log("ret $ret")
Coverage.statementStart(1094)
                        require(ret.second >= count)
Coverage.statementStart(1095)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1096)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1097)
                        listA.addAll(listB)
Coverage.statementStart(1098)
                        listA.addAll(listC)
Coverage.statementStart(1099)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1100)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1101)
                        }
Coverage.statementStart(1102)
                        helper.kotlinList = listA
Coverage.statementStart(1103)
                        helper.size += count
Coverage.statementStart(1104)
                    }
                    else -> {
Coverage.whenCaseStart(1105)
                        require(func < FUNCTION_COUNT)
Coverage.statementStart(1106)
                    }
                }
Coverage.statementStart(1107)
                if (expectException) {
Coverage.ifStart(1108)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1109)
                log("" + expectException)
Coverage.statementStart(1110)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1111)
                log(helper.kotlinList.toString())
Coverage.statementStart(1112)
                log("\n")
Coverage.statementStart(1113)
                for (helper in helpers) {
Coverage.forLoopStart(1114)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1115)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1116)
                        val v = helper.vektor.next()
Coverage.statementStart(1117)
                        var l = i - 5
Coverage.statementStart(1118)
                        var r = i + 6
Coverage.statementStart(1119)
                        if (l < 0) {
Coverage.ifStart(1120)
                            l = 0
Coverage.statementStart(1121)
                        }
Coverage.statementStart(1122)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1123)
                            r = helper.kotlinList.size
Coverage.statementStart(1124)
                        }
Coverage.statementStart(1125)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1126)
                    }
Coverage.statementStart(1127)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1128)
                    log(helper.vektor.toString())
Coverage.statementStart(1129)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1130)
                }
Coverage.statementStart(1131)
                log("\n")
Coverage.statementStart(1132)
            }
Coverage.statementStart(1133)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1134)
        } catch (e: Throwable) {
Coverage.statementStart(1135)
            if (!expectException) {
Coverage.ifStart(1136)
                throw e
            }
Coverage.statementStart(1137)
        }
Coverage.statementStart(1138)
    }
}
