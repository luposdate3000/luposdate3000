package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage
object ResultChunkBaseTest {
    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 3
    val MAX_DISTINCT_VALUES = 5
    val MAX_CAPACITY = 50
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 3
    val verbose = false
    class NoMoreRandomException() : Exception("")
    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
Coverage.funStart(129)
        try {
Coverage.statementStart(130)
            val res = buffer.getNextInt() % max
Coverage.statementStart(131)
            if (positiveOnly && res < 0) {
Coverage.ifStart(132)
                return -res
            }
Coverage.statementStart(133)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(134)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    fun max(a: Int, b: Int): Int {
Coverage.funStart(135)
        if (a < b) {
Coverage.ifStart(136)
            return b
        }
Coverage.statementStart(137)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(138)
        if (a > b) {
Coverage.ifStart(139)
            return b
        }
Coverage.statementStart(140)
        return a
    }
    var columns = 4
    class ResultVektorTestHelper {
        var kotlinList = mutableListOf<Array<Value>>()
        var resultSetDictionary = ResultSetDictionary()
        var resultSet = ResultSet(resultSetDictionary)
        var chunk = ResultChunkBase(resultSet, columns)
        var pos = 0
        var size = 0
        var backup = 0
        constructor() {
            for (i in 0 until columns) {
Coverage.forLoopStart(141)
                resultSet.createVariable("name$i")
            }
        }
    }
    fun log(s: String) {
Coverage.funStart(142)
        if (verbose) {
Coverage.ifStart(143)
            println(s)
Coverage.statementStart(144)
        }
Coverage.statementStart(145)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(146)
        var expectException = false
Coverage.statementStart(147)
        log("-----------------------start")
Coverage.statementStart(148)
        try {
Coverage.statementStart(149)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(150)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(151)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(152)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(153)
            while (true) {
Coverage.whileLoopStart(154)
                expectException = false
Coverage.statementStart(155)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(156)
                val helper = helpers[helperIdx]
Coverage.statementStart(157)
                log("helper ${helper.chunk}")
Coverage.statementStart(158)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(159)
                log("func $func")
Coverage.statementStart(160)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(161)
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(162)
                        log("count $count")
Coverage.statementStart(163)
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
Coverage.statementStart(164)
                        helper.chunk.skipPos(count)
Coverage.statementStart(165)
                        helper.pos += count
Coverage.statementStart(166)
                    }
                    1 -> {
Coverage.whenCaseStart(167)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(168)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(169)
                            count = helper.pos - helper.size
Coverage.statementStart(170)
                        }
Coverage.statementStart(171)
                        log("count $count")
Coverage.statementStart(172)
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
Coverage.statementStart(173)
                        helper.chunk.skipSize(count)
Coverage.statementStart(174)
                        if (count > 0) {
Coverage.ifStart(175)
                            for (i in 0 until count) {
Coverage.forLoopStart(176)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
Coverage.statementStart(177)
                            }
Coverage.statementStart(178)
                        } else {
Coverage.ifStart(179)
                            if (!expectException) {
Coverage.ifStart(180)
                                for (i in 0 until -count) {
Coverage.forLoopStart(181)
                                    helper.kotlinList.removeAt(helper.size + count)
Coverage.statementStart(182)
                                }
Coverage.statementStart(183)
                            }
Coverage.statementStart(184)
                        }
Coverage.statementStart(185)
                        helper.size += count
Coverage.statementStart(186)
                    }
                    2 -> {
Coverage.whenCaseStart(187)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(188)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(189)
                            count = helper.pos - helper.size
Coverage.statementStart(190)
                        }
Coverage.statementStart(191)
                        log("count $count")
Coverage.statementStart(192)
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(193)
                        expectException = count <= 0 || !helper.chunk.canAppend()
Coverage.statementStart(194)
                        helper.chunk.append(value, count)
Coverage.statementStart(195)
                        for (i in 0 until count) {
Coverage.forLoopStart(196)
                            helper.kotlinList.add(helper.size, value)
Coverage.statementStart(197)
                        }
Coverage.statementStart(198)
                        helper.size += count
Coverage.statementStart(199)
                    }
                    3 -> {
Coverage.whenCaseStart(200)
                        helper.backup = helper.pos
Coverage.statementStart(201)
                        helper.chunk.backupPosition()
Coverage.statementStart(202)
                    }
                    4 -> {
Coverage.whenCaseStart(203)
expectException=helper.backup>helper.size
Coverage.statementStart(204)
                        helper.pos = helper.backup
Coverage.statementStart(205)
                        helper.chunk.restorePosition()
Coverage.statementStart(206)
                    }
                    5 -> {
Coverage.whenCaseStart(207)
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(208)
                    }
                    6 -> {
Coverage.whenCaseStart(209)
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(210)
                    }
                    7 -> {
Coverage.whenCaseStart(211)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(212)
                        val v = helper.chunk.current()
Coverage.statementStart(213)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(214)
                        for (i in 0 until columns) {
Coverage.forLoopStart(215)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(216)
                        }
Coverage.statementStart(217)
                    }
                    8 -> {
Coverage.whenCaseStart(218)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(219)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(220)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(221)
                        for (i in 0 until columns) {
Coverage.forLoopStart(222)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(223)
                        }
Coverage.statementStart(224)
                        helper.pos++
Coverage.statementStart(225)
                    }
                    9 -> {
Coverage.whenCaseStart(226)
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(227)
                    }
                    10 -> {
Coverage.whenCaseStart(228)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(229)
                        expectException = colcount == 0 || helper.pos >= helper.size
Coverage.statementStart(230)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(231)
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(232)
                        val v = helper.chunk.current(columns)
Coverage.statementStart(233)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
Coverage.statementStart(234)
                        for (i in 0 until colcount) {
Coverage.forLoopStart(235)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(236)
                        }
Coverage.statementStart(237)
                    }
                    11 -> {
Coverage.whenCaseStart(238)
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
Coverage.statementStart(239)
                    }
                    12 -> {
Coverage.whenCaseStart(240)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(241)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(242)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(243)
                        expectException = count > helper.size - helper.pos || count <= 0
Coverage.statementStart(244)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(245)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(246)
                            for (i in 0 until c) {
Coverage.forLoopStart(247)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(248)
                                helper.pos++
Coverage.statementStart(249)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(250)
                            }
Coverage.statementStart(251)
                            helper2.chunk.copy(helper.chunk, c)
Coverage.statementStart(252)
                            count -= c
Coverage.statementStart(253)
                        }
Coverage.statementStart(254)
                        if (count > 0) {
Coverage.ifStart(255)
                            expectException = true
Coverage.statementStart(256)
                            for (i in 0 until count) {
Coverage.forLoopStart(257)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(258)
                                helper.pos++
Coverage.statementStart(259)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(260)
                            }
Coverage.statementStart(261)
                            helper2.chunk.copy(helper.chunk, count)
Coverage.statementStart(262)
                        }
Coverage.statementStart(263)
                    }
                    13 -> {
Coverage.whenCaseStart(264)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(265)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(266)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(267)
                        val columns2 = allcolumns.toTypedArray()
Coverage.statementStart(268)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(269)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(270)
                        log("helper2 ${helper2.chunk}")
Coverage.statementStart(271)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(272)
                        log("count $count")
Coverage.statementStart(273)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
Coverage.statementStart(274)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
Coverage.statementStart(275)
                        if (count == 0) {
Coverage.ifStart(276)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(277)
                        }
Coverage.statementStart(278)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(279)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(280)
                            log("progress $c")
Coverage.statementStart(281)
                            for (i in 0 until c) {
Coverage.forLoopStart(282)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(283)
                                helper.pos++
Coverage.statementStart(284)
                                for (col in columns2) {
Coverage.forLoopStart(285)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(286)
                                }
Coverage.statementStart(287)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(288)
                            }
Coverage.statementStart(289)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
Coverage.statementStart(290)
                            helper.chunk.skipPos(columns2, c)
Coverage.statementStart(291)
                            helper2.chunk.skipSize(columns2, c)
Coverage.statementStart(292)
                            count -= c
Coverage.statementStart(293)
                        }
Coverage.statementStart(294)
                        if (count > 0) {
Coverage.ifStart(295)
                            expectException = true
Coverage.statementStart(296)
                            for (i in 0 until count) {
Coverage.forLoopStart(297)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(298)
                                helper.pos++
Coverage.statementStart(299)
                                for (col in columns2) {
Coverage.forLoopStart(300)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(301)
                                }
Coverage.statementStart(302)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(303)
                            }
Coverage.statementStart(304)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(305)
                            helper.chunk.skipPos(columns2, count)
Coverage.statementStart(306)
                            helper2.chunk.skipSize(columns2, count)
Coverage.statementStart(307)
                        }
Coverage.statementStart(308)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(309)
                if (expectException) {
Coverage.ifStart(310)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(311)
                log("" + expectException)
Coverage.statementStart(312)
                log("\n")
Coverage.statementStart(313)
                for (helper in helpers) {
Coverage.forLoopStart(314)
                    log("helper ${helper.chunk}")
Coverage.statementStart(315)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(316)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(317)
                    log("skippos ${-helper.pos}")
Coverage.statementStart(318)
                    if (helper.pos != 0) {
Coverage.ifStart(319)
                        helper.chunk.skipPos(-helper.pos)
Coverage.statementStart(320)
                    }
Coverage.statementStart(321)
                    for (j in 0 until helper.size) {
Coverage.forLoopStart(322)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(323)
                        val w = helper.kotlinList[j]
Coverage.statementStart(324)
                        for (i in 0 until columns) {
Coverage.forLoopStart(325)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
Coverage.statementStart(326)
                        }
Coverage.statementStart(327)
                    }
Coverage.statementStart(328)
                    if (helper.pos - helper.size != 0) {
Coverage.ifStart(329)
                        helper.chunk.skipPos(helper.pos - helper.size)
Coverage.statementStart(330)
                    }
Coverage.statementStart(331)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(332)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(333)
                }
Coverage.statementStart(334)
                log("\n")
Coverage.statementStart(335)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(336)
        } catch (e: Throwable) {
Coverage.statementStart(337)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(338)
        }
Coverage.statementStart(339)
    }
}
