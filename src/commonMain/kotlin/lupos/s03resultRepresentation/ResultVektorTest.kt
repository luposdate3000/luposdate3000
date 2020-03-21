package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
Coverage.funStart(923)
            if (a < b) {
Coverage.ifStart(924)
                return -1
            }
Coverage.statementStart(925)
            if (a == b) {
Coverage.ifStart(926)
                throw Exception("dont compare equal values using comparator")
            }
Coverage.statementStart(927)
            return 1
        }
    }
    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = false
    class NoMoreRandomException() : Exception("")
    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
Coverage.funStart(928)
        try {
Coverage.statementStart(929)
            val res = buffer.getNextInt() % max
Coverage.statementStart(930)
            if (positiveOnly && res < 0) {
Coverage.ifStart(931)
                return -res
            }
Coverage.statementStart(932)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(933)
            throw NoMoreRandomException()
        }
Coverage.statementStart(934)
    }
    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }
    fun log(s: String) {
Coverage.funStart(935)
        if (verbose) {
Coverage.ifStart(936)
            println(s)
Coverage.statementStart(937)
        }
Coverage.statementStart(938)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(939)
        var expectException = false
Coverage.statementStart(940)
        log("start")
Coverage.statementStart(941)
        try {
Coverage.statementStart(942)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(943)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(944)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(945)
            while (true) {
Coverage.whileLoopStart(946)
                expectException = false
Coverage.statementStart(947)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(948)
                val helper = helpers[helperIdx]
Coverage.statementStart(949)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(950)
                log(helper.kotlinList.toString())
Coverage.statementStart(951)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(952)
                log("func $func")
Coverage.statementStart(953)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(954)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(955)
                        log("count $count")
Coverage.statementStart(956)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
Coverage.statementStart(957)
                        helper.vektor.skipPos(count)
Coverage.statementStart(958)
                        helper.pos += count
Coverage.statementStart(959)
                    }
                    1 -> {
Coverage.whenCaseStart(960)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(961)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(962)
                            count = helper.pos - helper.size
Coverage.statementStart(963)
                        }
Coverage.statementStart(964)
                        log("count $count")
Coverage.statementStart(965)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
Coverage.statementStart(966)
                        helper.vektor.skipSize(count)
Coverage.statementStart(967)
                        helper.size += count
Coverage.statementStart(968)
                        if (count > 0) {
Coverage.ifStart(969)
                            for (i in 0 until count) {
Coverage.forLoopStart(970)
                                helper.kotlinList.add(DONT_CARE_VALUE)
Coverage.statementStart(971)
                            }
Coverage.statementStart(972)
                        } else {
Coverage.ifStart(973)
                            if (!expectException) {
Coverage.ifStart(974)
                                for (i in 0 until -count) {
Coverage.forLoopStart(975)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
Coverage.statementStart(976)
                                }
Coverage.statementStart(977)
                            }
Coverage.statementStart(978)
                        }
Coverage.statementStart(979)
                    }
                    2 -> {
Coverage.whenCaseStart(980)
                        helper.vektor.backupPosition()
Coverage.statementStart(981)
                        helper.backup = helper.pos
Coverage.statementStart(982)
                    }
                    3 -> {
Coverage.whenCaseStart(983)
                        helper.vektor.restorePosition()
Coverage.statementStart(984)
                        helper.pos = helper.backup
Coverage.statementStart(985)
                    }
                    4 -> {
Coverage.whenCaseStart(986)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(987)
                        val c = helper.vektor.current()
Coverage.statementStart(988)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(989)
                    }
                    5 -> {
Coverage.whenCaseStart(990)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(991)
                        val c = helper.vektor.next()
Coverage.statementStart(992)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
Coverage.statementStart(993)
                        helper.pos++
Coverage.statementStart(994)
                    }
                    6 -> {
Coverage.whenCaseStart(995)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
Coverage.statementStart(996)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
Coverage.statementStart(997)
                    }
                    7 -> {
Coverage.whenCaseStart(998)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
Coverage.statementStart(999)
                    }
                    8 -> {
Coverage.whenCaseStart(1000)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
Coverage.statementStart(1001)
                    }
                    9 -> {
Coverage.whenCaseStart(1002)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(1003)
                    }
                    10 -> {
Coverage.whenCaseStart(1004)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(1005)
                        log("count $count")
Coverage.statementStart(1006)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(1007)
                        log("value $value")
Coverage.statementStart(1008)
                        expectException = count <= 0 || !helper.vektor.canAppend()
Coverage.statementStart(1009)
                        helper.vektor.append(value, count)
Coverage.statementStart(1010)
                        for (i in 0 until count) {
Coverage.forLoopStart(1011)
                            helper.kotlinList.add(value)
Coverage.statementStart(1012)
                        }
Coverage.statementStart(1013)
                        helper.size += count
Coverage.statementStart(1014)
                    }
                    11 -> {
Coverage.whenCaseStart(1015)
                        var same = 0
Coverage.statementStart(1016)
                        var lastsame = -1
Coverage.statementStart(1017)
                        var helperValue = DONT_CARE_VALUE
Coverage.statementStart(1018)
                        val tmp = helper.vektor.sameElements()
Coverage.statementStart(1019)
                        while (same != lastsame && same != tmp) {
Coverage.whileLoopStart(1020)
                            if (helperValue == DONT_CARE_VALUE) {
Coverage.ifStart(1021)
                                helperValue = helper.kotlinList[helper.pos]
Coverage.statementStart(1022)
                            }
Coverage.statementStart(1023)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
Coverage.whileLoopStart(1024)
                                same++
Coverage.statementStart(1025)
                            }
Coverage.statementStart(1026)
                            if (same == tmp) {
Coverage.ifStart(1027)
                                break
                            }
Coverage.statementStart(1028)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
Coverage.whileLoopStart(1029)
                                same++
Coverage.statementStart(1030)
                            }
Coverage.statementStart(1031)
                            log("same $same $tmp")
Coverage.statementStart(1032)
                        }
Coverage.statementStart(1033)
                        require(same == tmp)
Coverage.statementStart(1034)
                    }
                    12 -> {
Coverage.whenCaseStart(1035)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(1036)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(1037)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
Coverage.statementStart(1038)
                        log(helper2.kotlinList.toString())
Coverage.statementStart(1039)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
Coverage.statementStart(1040)
                        log("count $count")
Coverage.statementStart(1041)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
Coverage.statementStart(1042)
                        helper2.vektor.copy(helper.vektor, count)
Coverage.statementStart(1043)
                        expectException = helper.vektor.availableRead() < count || count <= 0
Coverage.statementStart(1044)
                        for (i in helper.pos until helper.pos + count) {
Coverage.forLoopStart(1045)
                            helper2.kotlinList.add(helper.kotlinList[i])
Coverage.statementStart(1046)
                        }
Coverage.statementStart(1047)
                        helper2.size += count
Coverage.statementStart(1048)
                        helper.pos += count
Coverage.statementStart(1049)
                    }
                    13 -> {
Coverage.whenCaseStart(1050)
                        val first = nextRandom(buffer, helper.size, true)
Coverage.statementStart(1051)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
Coverage.statementStart(1052)
                        var last = first
Coverage.statementStart(1053)
                        helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1054)
                        helper.pos = 0
Coverage.statementStart(1055)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
Coverage.ifStart(1056)
                            helper.vektor.skipPos(last)
Coverage.statementStart(1057)
                            helper.kotlinList[last] = helper.vektor.current()
Coverage.statementStart(1058)
                            helper.vektor.skipPos(-last)
Coverage.statementStart(1059)
                        }
Coverage.statementStart(1060)
                        while (last < lastTarget) {
Coverage.whileLoopStart(1061)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
Coverage.ifStart(1062)
                                helper.vektor.skipPos(last + 1)
Coverage.statementStart(1063)
                                helper.kotlinList[last + 1] = helper.vektor.current()
Coverage.statementStart(1064)
                                helper.vektor.skipPos(-last - 1)
Coverage.statementStart(1065)
                            }
Coverage.statementStart(1066)
                            val lastValue = helper.kotlinList[last]
Coverage.statementStart(1067)
                            val thisValue = helper.kotlinList[last + 1]
Coverage.statementStart(1068)
                            if (lastValue == thisValue || MyComparatorValue().compare(lastValue, thisValue) < 0) {
Coverage.ifStart(1069)
                                break
                            }
Coverage.statementStart(1070)
                            last++
Coverage.statementStart(1071)
                        }
Coverage.statementStart(1072)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
Coverage.statementStart(1073)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
Coverage.statementStart(1074)
                        log("first $first")
Coverage.statementStart(1075)
                        log("last $last")
Coverage.statementStart(1076)
                        log("value $value")
Coverage.statementStart(1077)
                        log("count $count")
Coverage.statementStart(1078)
                        val listA = mutableListOf<Value>()
Coverage.statementStart(1079)
                        val listB = mutableListOf<Value>()
Coverage.statementStart(1080)
                        val listC = mutableListOf<Value>()
Coverage.statementStart(1081)
                        for (i in 0 until first) {
Coverage.forLoopStart(1082)
                            listA.add(helper.kotlinList[i])
Coverage.statementStart(1083)
                        }
Coverage.statementStart(1084)
                        for (i in 0 until count) {
Coverage.forLoopStart(1085)
                            listB.add(value)
Coverage.statementStart(1086)
                        }
Coverage.statementStart(1087)
                        for (i in first until last) {
Coverage.forLoopStart(1088)
                            listB.add(helper.kotlinList[i])
Coverage.statementStart(1089)
                        }
Coverage.statementStart(1090)
                        for (i in last until helper.kotlinList.size) {
Coverage.forLoopStart(1091)
                            listC.add(helper.kotlinList[i])
Coverage.statementStart(1092)
                        }
Coverage.statementStart(1093)
                        log("inA $listA")
Coverage.statementStart(1094)
                        log("inB $listB")
Coverage.statementStart(1095)
                        listB.sort()
Coverage.statementStart(1096)
                        log("inB2 $listB")
Coverage.statementStart(1097)
                        log("inC $listC")
Coverage.statementStart(1098)
                        log("size " + listA.size)
Coverage.statementStart(1099)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
Coverage.statementStart(1100)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
Coverage.statementStart(1101)
                        log("${helper.vektor}")
Coverage.statementStart(1102)
                        log("asize ${listA.size}")
Coverage.statementStart(1103)
                        log("bsize ${listB.size}")
Coverage.statementStart(1104)
                        log("csize ${listC.size}")
Coverage.statementStart(1105)
                        log("ret $ret")
Coverage.statementStart(1106)
                        require(ret.second >= count)
Coverage.statementStart(1107)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
Coverage.statementStart(1108)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
Coverage.statementStart(1109)
                        listA.addAll(listB)
Coverage.statementStart(1110)
                        listA.addAll(listC)
Coverage.statementStart(1111)
                        for (i in ret.first until ret.first + ret.second) {
Coverage.forLoopStart(1112)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE)
Coverage.statementStart(1113)
                        }
Coverage.statementStart(1114)
                        helper.kotlinList = listA
Coverage.statementStart(1115)
                        helper.size += count
Coverage.statementStart(1116)
                    }
                    else -> {
Coverage.whenCaseStart(1117)
                        require(func < FUNCTION_COUNT)
Coverage.statementStart(1118)
                    }
                }
Coverage.statementStart(1119)
                if (expectException) {
Coverage.ifStart(1120)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(1121)
                log("" + expectException)
Coverage.statementStart(1122)
                log("helperIdx $helperIdx ${helper.vektor}")
Coverage.statementStart(1123)
                log(helper.kotlinList.toString())
Coverage.statementStart(1124)
                log("\n")
Coverage.statementStart(1125)
                for (helper in helpers) {
Coverage.forLoopStart(1126)
                    helper.vektor.skipPos(-helper.pos)
Coverage.statementStart(1127)
                    for (i in 0 until helper.size) {
Coverage.forLoopStart(1128)
                        val v = helper.vektor.next()
Coverage.statementStart(1129)
                        var l = i - 5
Coverage.statementStart(1130)
                        var r = i + 6
Coverage.statementStart(1131)
                        if (l < 0) {
Coverage.ifStart(1132)
                            l = 0
Coverage.statementStart(1133)
                        }
Coverage.statementStart(1134)
                        if (r > helper.kotlinList.size) {
Coverage.ifStart(1135)
                            r = helper.kotlinList.size
Coverage.statementStart(1136)
                        }
Coverage.statementStart(1137)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
Coverage.statementStart(1138)
                    }
Coverage.statementStart(1139)
                    helper.vektor.skipPos(helper.pos - helper.size)
Coverage.statementStart(1140)
                    log(helper.vektor.toString())
Coverage.statementStart(1141)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
Coverage.statementStart(1142)
                }
Coverage.statementStart(1143)
                log("\n")
Coverage.statementStart(1144)
            }
Coverage.statementStart(1145)
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(1146)
        } catch (e: Throwable) {
Coverage.statementStart(1147)
            if (!expectException) {
Coverage.ifStart(1148)
                throw e
            }
Coverage.statementStart(1149)
        }
Coverage.statementStart(1150)
    }
}
