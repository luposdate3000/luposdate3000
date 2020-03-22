package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(909)
            if (a < b) {
Coverage.ifStart(910)
                return -1
            }
Coverage.statementStart(911)
            if (a == b) {
Coverage.ifStart(912)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(913)
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
Coverage.funStart(914)
        try {
Coverage.statementStart(915)
            val res = buffer.getNextInt() % max
Coverage.statementStart(916)
            if (positiveOnly && res < 0) {
Coverage.ifStart(917)
                return -res
            }
Coverage.statementStart(918)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(919)
            throw NoMoreRandomException()
        }
Coverage.statementStart(920)
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
Coverage.funStart(921)
        if (verbose) {
Coverage.ifStart(922)
            println(s)
Coverage.statementStart(923)
        }
Coverage.statementStart(924)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(925)
        var expectException = false
Coverage.statementStart(926)
        log("start")
Coverage.statementStart(927)
        try {
Coverage.statementStart(928)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(929)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(930)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(931)
            while (true) {
Coverage.whileLoopStart(932)
                expectException = false
Coverage.statementStart(933)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(934)
                val helper = helpers[helperIdx]
Coverage.statementStart(935)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(936)
                log(helper.kotlinList.toString())
Coverage.statementStart(937)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(938)
                log("func $func")
Coverage.statementStart(939)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(940)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(941)
                        log("count $count")
Coverage.statementStart(942)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(943)
                        helper.vektor.skipPos(count)
Coverage.statementStart(944)
                        helper.pos += count
Coverage.statementStart(945)
                    }
                    1 -> {
Coverage.whenCaseStart(946)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(947)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(948)
                            count = helper.pos - helper.size
Coverage.statementStart(949)
                        }
Coverage.statementStart(950)
                        log("count $count")
Coverage.statementStart(951)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(952)
                        helper.vektor.skipSize(count)
Coverage.statementStart(953)
                        helper.size += count
Coverage.statementStart(954)
                        if (count > 0) {
Coverage.ifStart(955)
                            for (i in 0 until count) {
Coverage.forLoopStart(956)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(957)
                            }
Coverage.statementStart(958)
                        } else {
Coverage.ifStart(959)
                            if (!expectException) {
Coverage.ifStart(960)
                                for (i in 0 until -count) {
Coverage.forLoopStart(961)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(962)
                                }
Coverage.statementStart(963)
                            }
Coverage.statementStart(964)
                        }
Coverage.statementStart(965)
                    }
                    2 -> {
Coverage.whenCaseStart(966)
                        helper.vektor.backupPosition()
Coverage.statementStart(967)
                        helper.backup = helper.pos
Coverage.statementStart(968)
                    }
                    3 -> {
Coverage.whenCaseStart(969)
                        expectException = helper.backup > helper.size
Coverage.statementStart(970)
                        helper.vektor.restorePosition()
Coverage.statementStart(971)
                        helper.pos = helper.backup
Coverage.statementStart(972)
                    }
                    4 -> {
Coverage.whenCaseStart(973)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(974)
                        val c = helper.vektor.current()
Coverage.statementStart(975)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(976)
                    }
                    5 -> {
Coverage.whenCaseStart(977)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(978)
                        val c = helper.vektor.next()
Coverage.statementStart(979)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(980)
                        helper.pos++
Coverage.statementStart(981)
                    }
                    6 -> {
Coverage.whenCaseStart(982)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(983)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(984)
                    }
                    7 -> {
Coverage.whenCaseStart(985)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(986)
                    }
                    8 -> {
Coverage.whenCaseStart(987)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(988)
                    }
                    9 -> {
Coverage.whenCaseStart(989)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(990)
                    }
                    10 -> {
Coverage.whenCaseStart(991)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(992)
                        log("count $count")
Coverage.statementStart(993)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(994)
                        log("value $value")
Coverage.statementStart(995)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(996)
                        helper.vektor.append(value, count)
Coverage.statementStart(997)
                        for (i in 0 until count) {
Coverage.forLoopStart(998)
                            helper.kotlinList.add(value)
Coverage.statementStart(999)
                        }
Coverage.statementStart(1000)
                        helper.size += count
Coverage.statementStart(1001)
                    }
                    11 -> {
Coverage.whenCaseStart(1002)
                        var same = 0
Coverage.statementStart(1003)
                        var lastsame = -1
Coverage.statementStart(1004)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(1005)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(1006)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(1007)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(1008)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(1009)
                            }
Coverage.statementStart(1010)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(1011)
                                same++
Coverage.statementStart(1012)
                            }
Coverage.statementStart(1013)
                            if (same == tmp) {
Coverage.ifStart(1014)
                                break
                            }
Coverage.statementStart(1015)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(1016)
                                same++
Coverage.statementStart(1017)
                            }
Coverage.statementStart(1018)
                            log("same $same $tmp")
Coverage.statementStart(1019)
                        }
Coverage.statementStart(1020)
                        require(same == tmp)
Coverage.statementStart(1021)
                    }
                    12 -> {
Coverage.whenCaseStart(1022)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(1023)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(1024)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(1025)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(1026)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(1027)
                        log("count $count")
Coverage.statementStart(1028)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(1029)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(1030)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(1031)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(1032)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(1033)
                        }
Coverage.statementStart(1034)
                        helper2.size += count
Coverage.statementStart(1035)
                        helper.pos += count
Coverage.statementStart(1036)
                    }
                    13 -> {
Coverage.whenCaseStart(1037)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(1038)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(1039)
                        var last = first
Coverage.statementStart(1040)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1041)
                        helper.pos = 0
Coverage.statementStart(1042)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(1043)
                            helper.vektor.skipPos(last)
Coverage.statementStart(1044)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(1045)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(1046)
                        }
Coverage.statementStart(1047)
                        while (last < lastTarget) {
Coverage.whileLoopStart(1048)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(1049)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(1050)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(1051)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(1052)
                            }
Coverage.statementStart(1053)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(1054)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(1055)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
Coverage.ifStart(1056)
                                break
                            }
Coverage.statementStart(1057)
                            last++
Coverage.statementStart(1058)
                        }
Coverage.statementStart(1059)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(1060)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(1061)
                        log("first $first")
Coverage.statementStart(1062)
                        log("last $last")
Coverage.statementStart(1063)
                        log("value $value")
Coverage.statementStart(1064)
                        log("count $count")
Coverage.statementStart(1065)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(1066)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(1067)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(1068)
                        for (i in 0 until first) {
Coverage.forLoopStart(1069)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(1070)
                        }
Coverage.statementStart(1071)
                        for (i in 0 until count) {
Coverage.forLoopStart(1072)
                            listB.add(value)
Coverage.statementStart(1073)
                        }
Coverage.statementStart(1074)
                        for (i in first until last) {
Coverage.forLoopStart(1075)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1076)
                        }
Coverage.statementStart(1077)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(1078)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1079)
                        }
Coverage.statementStart(1080)
                        log("inA $listA")
Coverage.statementStart(1081)
                        log("inB $listB")
Coverage.statementStart(1082)
                        listB.sort()
Coverage.statementStart(1083)
                        log("inB2 $listB")
Coverage.statementStart(1084)
                        log("inC $listC")
Coverage.statementStart(1085)
                        log("size " + listA.size)
Coverage.statementStart(1086)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1087)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1088)
                        log("${helper.vektor}")
Coverage.statementStart(1089)
                        log("asize ${listA.size}")
Coverage.statementStart(1090)
                        log("bsize ${listB.size}")
Coverage.statementStart(1091)
                        log("csize ${listC.size}")
Coverage.statementStart(1092)
                        log("ret $ret")
Coverage.statementStart(1093)
                        require(ret.second >= count)
Coverage.statementStart(1094)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1095)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1096)
                        listA.addAll(listB)
Coverage.statementStart(1097)
                        listA.addAll(listC)
Coverage.statementStart(1098)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1099)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
Coverage.statementStart(1100)
                        }
Coverage.statementStart(1101)
                        helper.kotlinList = listA
Coverage.statementStart(1102)
                        helper.size += count
Coverage.statementStart(1103)
                    }
                    else -> {
Coverage.whenCaseStart(1104)
                        require(func < FUNCTION_COUNT)
Coverage.statementStart(1105)
                    }
                }
Coverage.statementStart(1106)
                if (expectException) {
Coverage.ifStart(1107)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1108)
                log("" + expectException)
Coverage.statementStart(1109)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1110)
                log(helper.kotlinList.toString())
Coverage.statementStart(1111)
                log("\n")
Coverage.statementStart(1112)
                for (helper in helpers) {
Coverage.forLoopStart(1113)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1114)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1115)
                        val v = helper.vektor.next()
Coverage.statementStart(1116)
                        var l = i - 5
Coverage.statementStart(1117)
                        var r = i + 6
Coverage.statementStart(1118)
                        if (l < 0) {
Coverage.ifStart(1119)
                            l = 0
Coverage.statementStart(1120)
                        }
Coverage.statementStart(1121)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1122)
                            r = helper.kotlinList.size
Coverage.statementStart(1123)
                        }
Coverage.statementStart(1124)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1125)
                    }
Coverage.statementStart(1126)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1127)
                    log(helper.vektor.toString())
Coverage.statementStart(1128)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1129)
                }
Coverage.statementStart(1130)
                log("\n")
Coverage.statementStart(1131)
            }
Coverage.statementStart(1132)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1133)
        } catch (e: Throwable) {
Coverage.statementStart(1134)
            if (!expectException) {
Coverage.ifStart(1135)
                throw e
            }
Coverage.statementStart(1136)
        }
Coverage.statementStart(1137)
    }
}
