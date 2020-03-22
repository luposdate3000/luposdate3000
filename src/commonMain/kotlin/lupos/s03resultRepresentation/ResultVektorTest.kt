package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(1013)
            if (a < b) {
                Coverage.ifStart(1014)
                return -1
            }
            Coverage.statementStart(1015)
            if (a == b) {
                Coverage.ifStart(1016)
                throw Exception("dont compare equal values using comparator")
            }
            Coverage.statementStart(1017)
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
        Coverage.funStart(1018)
//
        Coverage.statementStart(1019)
        try {
            Coverage.statementStart(1020)
//
            Coverage.statementStart(1021)
            val res = buffer.getNextInt() % max
            Coverage.statementStart(1022)
//
            Coverage.statementStart(1023)
            if (positiveOnly && res < 0) {
                Coverage.ifStart(1024)
//
                Coverage.statementStart(1025)
                return -res
            }
            Coverage.statementStart(1026)
//
            Coverage.statementStart(1027)
            return res
        } catch (e: Throwable) {
            Coverage.statementStart(1028)
//
            Coverage.statementStart(1029)
            throw NoMoreRandomException()
        }
        Coverage.statementStart(1030)
//
        Coverage.statementStart(1031)
    }

    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }

    fun log(s: String) {
        Coverage.funStart(1032)
//
        Coverage.statementStart(1033)
        if (verbose) {
            Coverage.ifStart(1034)
//
            Coverage.statementStart(1035)
            println(s)
            Coverage.statementStart(1036)
//
            Coverage.statementStart(1037)
        }
        Coverage.statementStart(1038)
//
        Coverage.statementStart(1039)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(1040)
        var expectException = false
        Coverage.statementStart(1041)
        log("start")
        Coverage.statementStart(1042)
        try {
            Coverage.statementStart(1043)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            Coverage.statementStart(1044)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(1045)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            Coverage.statementStart(1046)
            while (true) {
                Coverage.whileLoopStart(1047)
                expectException = false
                Coverage.statementStart(1048)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                Coverage.statementStart(1049)
                val helper = helpers[helperIdx]
                Coverage.statementStart(1050)
                log("helperIdx $helperIdx ${helper.vektor}")
                Coverage.statementStart(1051)
                log(helper.kotlinList.toString())
                Coverage.statementStart(1052)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                Coverage.statementStart(1053)
                log("func $func")
                Coverage.statementStart(1054)
                when (func) {
                    0 -> {
                        Coverage.whenCaseStart(1055)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(1056)
                        log("count $count")
                        Coverage.statementStart(1057)
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
                        Coverage.statementStart(1058)
                        helper.vektor.skipPos(count)
                        Coverage.statementStart(1059)
                        helper.pos += count
                        Coverage.statementStart(1060)
                    }
                    1 -> {
                        Coverage.whenCaseStart(1061)
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(1062)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(1063)
                            count = helper.pos - helper.size
                            Coverage.statementStart(1064)
                        }
                        Coverage.statementStart(1065)
                        log("count $count")
                        Coverage.statementStart(1066)
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
                        Coverage.statementStart(1067)
                        helper.vektor.skipSize(count)
                        Coverage.statementStart(1068)
                        helper.size += count
                        Coverage.statementStart(1069)
                        if (count > 0) {
                            Coverage.ifStart(1070)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(1071)
                                helper.kotlinList.add(DONT_CARE_VALUE)
                                Coverage.statementStart(1072)
                            }
                            Coverage.statementStart(1073)
                        } else {
                            Coverage.ifStart(1074)
                            if (!expectException) {
                                Coverage.ifStart(1075)
                                for (i in 0 until -count) {
                                    Coverage.forLoopStart(1076)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
                                    Coverage.statementStart(1077)
                                }
                                Coverage.statementStart(1078)
                            }
                            Coverage.statementStart(1079)
                        }
                        Coverage.statementStart(1080)
                    }
                    2 -> {
                        Coverage.whenCaseStart(1081)
                        helper.vektor.backupPosition()
                        Coverage.statementStart(1082)
                        helper.backup = helper.pos
                        Coverage.statementStart(1083)
                    }
                    3 -> {
                        Coverage.whenCaseStart(1084)
                        expectException = helper.backup > helper.size
                        Coverage.statementStart(1085)
                        helper.vektor.restorePosition()
                        Coverage.statementStart(1086)
                        helper.pos = helper.backup
                        Coverage.statementStart(1087)
                    }
                    4 -> {
                        Coverage.whenCaseStart(1088)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(1089)
                        val c = helper.vektor.current()
                        Coverage.statementStart(1090)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                        Coverage.statementStart(1091)
                    }
                    5 -> {
                        Coverage.whenCaseStart(1092)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(1093)
                        val c = helper.vektor.next()
                        Coverage.statementStart(1094)
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                        Coverage.statementStart(1095)
                        helper.pos++
                        Coverage.statementStart(1096)
                    }
                    6 -> {
                        Coverage.whenCaseStart(1097)
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
                        Coverage.statementStart(1098)
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
                        Coverage.statementStart(1099)
                    }
                    7 -> {
                        Coverage.whenCaseStart(1100)
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
                        Coverage.statementStart(1101)
                    }
                    8 -> {
                        Coverage.whenCaseStart(1102)
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
                        Coverage.statementStart(1103)
                    }
                    9 -> {
                        Coverage.whenCaseStart(1104)
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
                        Coverage.statementStart(1105)
                    }
                    10 -> {
                        Coverage.whenCaseStart(1106)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(1107)
                        log("count $count")
                        Coverage.statementStart(1108)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        Coverage.statementStart(1109)
                        log("value $value")
                        Coverage.statementStart(1110)
                        expectException = count <= 0 || !helper.vektor.canAppend()
                        Coverage.statementStart(1111)
                        helper.vektor.append(value, count)
                        Coverage.statementStart(1112)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(1113)
                            helper.kotlinList.add(value)
                            Coverage.statementStart(1114)
                        }
                        Coverage.statementStart(1115)
                        helper.size += count
                        Coverage.statementStart(1116)
                    }
                    11 -> {
                        Coverage.whenCaseStart(1117)
                        var same = 0
                        Coverage.statementStart(1118)
                        var lastsame = -1
                        Coverage.statementStart(1119)
                        var helperValue = DONT_CARE_VALUE
                        Coverage.statementStart(1120)
                        val tmp = helper.vektor.sameElements()
                        Coverage.statementStart(1121)
                        while (same != lastsame && same != tmp) {
                            Coverage.whileLoopStart(1122)
                            if (helperValue == DONT_CARE_VALUE) {
                                Coverage.ifStart(1123)
                                helperValue = helper.kotlinList[helper.pos]
                                Coverage.statementStart(1124)
                            }
                            Coverage.statementStart(1125)
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
                                Coverage.whileLoopStart(1126)
                                same++
                                Coverage.statementStart(1127)
                            }
                            Coverage.statementStart(1128)
                            if (same == tmp) {
                                Coverage.ifStart(1129)
                                break
                            }
                            Coverage.statementStart(1130)
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
                                Coverage.whileLoopStart(1131)
                                same++
                                Coverage.statementStart(1132)
                            }
                            Coverage.statementStart(1133)
                            log("same $same $tmp")
                            Coverage.statementStart(1134)
                        }
                        Coverage.statementStart(1135)
                        require(same == tmp)
                        Coverage.statementStart(1136)
                    }
                    12 -> {
                        Coverage.whenCaseStart(1137)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(1138)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(1139)
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
                        Coverage.statementStart(1140)
                        log(helper2.kotlinList.toString())
                        Coverage.statementStart(1141)
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        Coverage.statementStart(1142)
                        log("count $count")
                        Coverage.statementStart(1143)
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
                        Coverage.statementStart(1144)
                        helper2.vektor.copy(helper.vektor, count)
                        Coverage.statementStart(1145)
                        expectException = helper.vektor.availableRead() < count || count <= 0
                        Coverage.statementStart(1146)
                        for (i in helper.pos until helper.pos + count) {
                            Coverage.forLoopStart(1147)
                            helper2.kotlinList.add(helper.kotlinList[i])
                            Coverage.statementStart(1148)
                        }
                        Coverage.statementStart(1149)
                        helper2.size += count
                        Coverage.statementStart(1150)
                        helper.pos += count
                        Coverage.statementStart(1151)
                    }
                    13 -> {
                        Coverage.whenCaseStart(1152)
                        val first = nextRandom(buffer, helper.size, true)
                        Coverage.statementStart(1153)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
                        Coverage.statementStart(1154)
                        var last = first
                        Coverage.statementStart(1155)
                        helper.vektor.skipPos(-helper.pos)
                        Coverage.statementStart(1156)
                        helper.pos = 0
                        Coverage.statementStart(1157)
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
                            Coverage.ifStart(1158)
                            helper.vektor.skipPos(last)
                            Coverage.statementStart(1159)
                            helper.kotlinList[last] = helper.vektor.current()
                            Coverage.statementStart(1160)
                            helper.vektor.skipPos(-last)
                            Coverage.statementStart(1161)
                        }
                        Coverage.statementStart(1162)
                        while (last < lastTarget) {
                            Coverage.whileLoopStart(1163)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
                                Coverage.ifStart(1164)
                                helper.vektor.skipPos(last + 1)
                                Coverage.statementStart(1165)
                                helper.kotlinList[last + 1] = helper.vektor.current()
                                Coverage.statementStart(1166)
                                helper.vektor.skipPos(-last - 1)
                                Coverage.statementStart(1167)
                            }
                            Coverage.statementStart(1168)
                            val lastValue = helper.kotlinList[last]
                            Coverage.statementStart(1169)
                            val thisValue = helper.kotlinList[last + 1]
                            Coverage.statementStart(1170)
                            println("yyy $lastValue $thisValue")
                            Coverage.statementStart(1171)
                            if (lastValue != thisValue && MyComparatorValue().compare(lastValue, thisValue) > 0) {
                                Coverage.ifStart(1172)
                                break
                            }
                            Coverage.statementStart(1173)
                            last++
                            Coverage.statementStart(1174)
                        }
                        Coverage.statementStart(1175)
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
                        Coverage.statementStart(1176)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        Coverage.statementStart(1177)
                        log("first $first")
                        Coverage.statementStart(1178)
                        log("last $last")
                        Coverage.statementStart(1179)
                        log("value $value")
                        Coverage.statementStart(1180)
                        log("count $count")
                        Coverage.statementStart(1181)
                        val listA = mutableListOf<Value>()
                        Coverage.statementStart(1182)
                        val listB = mutableListOf<Value>()
                        Coverage.statementStart(1183)
                        val listC = mutableListOf<Value>()
                        Coverage.statementStart(1184)
                        for (i in 0 until first) {
                            Coverage.forLoopStart(1185)
//
                            Coverage.statementStart(1186)
                            listA.add(helper.kotlinList[i])
                            Coverage.statementStart(1187)
                        }
                        Coverage.statementStart(1188)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(1189)
//
                            Coverage.statementStart(1190)
                            listB.add(value)
                            Coverage.statementStart(1191)
                        }
                        Coverage.statementStart(1192)
                        for (i in first until last) {
                            Coverage.forLoopStart(1193)
//
                            Coverage.statementStart(1194)
                            listB.add(helper.kotlinList[i])
                            Coverage.statementStart(1195)
                        }
                        Coverage.statementStart(1196)
                        for (i in last until helper.kotlinList.size) {
                            Coverage.forLoopStart(1197)
//
                            Coverage.statementStart(1198)
                            listC.add(helper.kotlinList[i])
                            Coverage.statementStart(1199)
                        }
                        Coverage.statementStart(1200)
                        log("inA $listA")
                        Coverage.statementStart(1201)
                        log("inB $listB")
                        Coverage.statementStart(1202)
                        listB.sort()
                        Coverage.statementStart(1203)
                        log("inB2 $listB")
                        Coverage.statementStart(1204)
                        log("inC $listC")
                        Coverage.statementStart(1205)
                        log("size " + listA.size)
                        Coverage.statementStart(1206)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
                        Coverage.statementStart(1207)
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
                        Coverage.statementStart(1208)
                        log("${helper.vektor}")
                        Coverage.statementStart(1209)
                        log("asize ${listA.size}")
                        Coverage.statementStart(1210)
                        log("bsize ${listB.size}")
                        Coverage.statementStart(1211)
                        log("csize ${listC.size}")
                        Coverage.statementStart(1212)
                        log("ret $ret")
                        Coverage.statementStart(1213)
                        require(ret.second >= count)
                        Coverage.statementStart(1214)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
                        Coverage.statementStart(1215)
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
                        Coverage.statementStart(1216)
                        listA.addAll(listB)
                        Coverage.statementStart(1217)
                        listA.addAll(listC)
                        Coverage.statementStart(1218)
                        for (i in ret.first until ret.first + ret.second) {
                            Coverage.forLoopStart(1219)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE, { "$i : ${listA[i]} $value" })
                            Coverage.statementStart(1220)
                        }
                        Coverage.statementStart(1221)
                        helper.kotlinList = listA
                        Coverage.statementStart(1222)
                        helper.size += count
                        Coverage.statementStart(1223)
                    }
                    else -> {
                        Coverage.whenCaseStart(1224)
                        require(func < FUNCTION_COUNT)
                        Coverage.statementStart(1225)
                    }
                }
                Coverage.statementStart(1226)
                if (expectException) {
                    Coverage.ifStart(1227)
                    throw Exception("there should be an exception")
                }
                Coverage.statementStart(1228)
                log("" + expectException)
                Coverage.statementStart(1229)
                log("helperIdx $helperIdx ${helper.vektor}")
                Coverage.statementStart(1230)
                log(helper.kotlinList.toString())
                Coverage.statementStart(1231)
                log("\n")
                Coverage.statementStart(1232)
                for (helper in helpers) {
                    Coverage.forLoopStart(1233)
                    println("xxx${-helper.pos}")
                    Coverage.statementStart(1234)
                    helper.vektor.skipPos(-helper.pos)
                    Coverage.statementStart(1235)
                    for (i in 0 until helper.size) {
                        Coverage.forLoopStart(1236)
//
                        Coverage.statementStart(1237)
                        val v = helper.vektor.next()
                        Coverage.statementStart(1238)
//
                        Coverage.statementStart(1239)
                        var l = i - 5
                        Coverage.statementStart(1240)
//
                        Coverage.statementStart(1241)
                        var r = i + 6
                        Coverage.statementStart(1242)
//
                        Coverage.statementStart(1243)
                        if (l < 0) {
                            Coverage.ifStart(1244)
//
                            Coverage.statementStart(1245)
                            l = 0
                            Coverage.statementStart(1246)
//
                            Coverage.statementStart(1247)
                        }
                        Coverage.statementStart(1248)
//
                        Coverage.statementStart(1249)
                        if (r > helper.kotlinList.size) {
                            Coverage.ifStart(1250)
//
                            Coverage.statementStart(1251)
                            r = helper.kotlinList.size
                            Coverage.statementStart(1252)
//
                            Coverage.statementStart(1253)
                        }
                        Coverage.statementStart(1254)
//
                        Coverage.statementStart(1255)
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i - > $v != ${helper.kotlinList.subList(l, r)}" })
                        Coverage.statementStart(1256)
//
                        Coverage.statementStart(1257)
                    }
                    Coverage.statementStart(1258)
                    println("${helper.pos} ${helper.size}")
                    Coverage.statementStart(1259)
                    helper.vektor.skipPos(helper.pos - helper.size)
                    Coverage.statementStart(1260)
                    log(helper.vektor.toString())
                    Coverage.statementStart(1261)
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
                    Coverage.statementStart(1262)
                }
                Coverage.statementStart(1263)
                log("\n")
                Coverage.statementStart(1264)
            }
            Coverage.statementStart(1265)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(1266)
        } catch (e: Throwable) {
            Coverage.statementStart(1267)
            if (!expectException) {
                Coverage.ifStart(1268)
                throw e
            }
            Coverage.statementStart(1269)
        }
        Coverage.statementStart(1270)
    }
}
