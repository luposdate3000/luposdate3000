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
Coverage.funStart(134)
        try {
Coverage.statementStart(135)
            val res = buffer.getNextInt() % max
Coverage.statementStart(136)
            if (positiveOnly && res < 0) {
Coverage.ifStart(137)
                return -res
            }
Coverage.statementStart(138)
            return res
        } catch (e: Throwable) {
Coverage.statementStart(139)
            throw NoMoreRandomException()
        }
/*Coverage Unreachable*/
    }
    fun max(a: Int, b: Int): Int {
Coverage.funStart(141)
        if (a < b) {
Coverage.ifStart(142)
            return b
        }
Coverage.statementStart(143)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(144)
        if (a > b) {
Coverage.ifStart(145)
            return b
        }
Coverage.statementStart(146)
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
Coverage.forLoopStart(147)
                resultSet.createVariable("name$i")
            }
        }
    }
    fun log(s: String) {
Coverage.funStart(148)
        if (verbose) {
Coverage.ifStart(149)
            println(s)
Coverage.statementStart(150)
        }
Coverage.statementStart(151)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(152)
        var expectException = false
Coverage.statementStart(153)
        log("-----------------------start")
Coverage.statementStart(154)
        try {
Coverage.statementStart(155)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(156)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(157)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(158)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(159)
            while (true) {
Coverage.whileLoopStart(160)
                expectException = false
Coverage.statementStart(161)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(162)
                val helper = helpers[helperIdx]
Coverage.statementStart(163)
                log("helper ${helper.chunk}")
Coverage.statementStart(164)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(165)
                log("func $func")
Coverage.statementStart(166)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(167)
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(168)
                        log("count $count")
Coverage.statementStart(169)
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
Coverage.statementStart(170)
                        helper.chunk.skipPos(count)
Coverage.statementStart(171)
                        helper.pos += count
Coverage.statementStart(172)
                    }
                    1 -> {
Coverage.whenCaseStart(173)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(174)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(175)
                            count = helper.pos - helper.size
Coverage.statementStart(176)
                        }
Coverage.statementStart(177)
                        log("count $count")
Coverage.statementStart(178)
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
Coverage.statementStart(179)
                        helper.chunk.skipSize(count)
Coverage.statementStart(180)
                        if (count > 0) {
Coverage.ifStart(181)
                            for (i in 0 until count) {
Coverage.forLoopStart(182)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
Coverage.statementStart(183)
                            }
Coverage.statementStart(184)
                        } else {
Coverage.ifStart(185)
                            if (!expectException) {
Coverage.ifStart(186)
                                for (i in 0 until -count) {
Coverage.forLoopStart(187)
                                    helper.kotlinList.removeAt(helper.size + count)
Coverage.statementStart(188)
                                }
Coverage.statementStart(189)
                            }
Coverage.statementStart(190)
                        }
Coverage.statementStart(191)
                        helper.size += count
Coverage.statementStart(192)
                    }
                    2 -> {
Coverage.whenCaseStart(193)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(194)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(195)
                            count = helper.pos - helper.size
Coverage.statementStart(196)
                        }
Coverage.statementStart(197)
                        log("count $count")
Coverage.statementStart(198)
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(199)
                        expectException = count <= 0 || !helper.chunk.canAppend()
Coverage.statementStart(200)
                        helper.chunk.append(value, count)
Coverage.statementStart(201)
                        for (i in 0 until count) {
Coverage.forLoopStart(202)
                            helper.kotlinList.add(helper.size, value)
Coverage.statementStart(203)
                        }
Coverage.statementStart(204)
                        helper.size += count
Coverage.statementStart(205)
                    }
                    3 -> {
Coverage.whenCaseStart(206)
                        helper.backup = helper.pos
Coverage.statementStart(207)
                        helper.chunk.backupPosition()
Coverage.statementStart(208)
                    }
                    4 -> {
Coverage.whenCaseStart(209)
                        helper.pos = helper.backup
Coverage.statementStart(210)
                        helper.chunk.restorePosition()
Coverage.statementStart(211)
                    }
                    5 -> {
Coverage.whenCaseStart(212)
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(213)
                    }
                    6 -> {
Coverage.whenCaseStart(214)
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(215)
                    }
                    7 -> {
Coverage.whenCaseStart(216)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(217)
                        val v = helper.chunk.current()
Coverage.statementStart(218)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(219)
                        for (i in 0 until columns) {
Coverage.forLoopStart(220)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(221)
                        }
Coverage.statementStart(222)
                    }
                    8 -> {
Coverage.whenCaseStart(223)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(224)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(225)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(226)
                        for (i in 0 until columns) {
Coverage.forLoopStart(227)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(228)
                        }
Coverage.statementStart(229)
                        helper.pos++
Coverage.statementStart(230)
                    }
                    9 -> {
Coverage.whenCaseStart(231)
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(232)
                    }
                    10 -> {
Coverage.whenCaseStart(233)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(234)
                        expectException = colcount == 0 || helper.pos >= helper.size
Coverage.statementStart(235)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(236)
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(237)
                        val v = helper.chunk.current(columns)
Coverage.statementStart(238)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
Coverage.statementStart(239)
                        for (i in 0 until colcount) {
Coverage.forLoopStart(240)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(241)
                        }
Coverage.statementStart(242)
                    }
                    11 -> {
Coverage.whenCaseStart(243)
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
Coverage.statementStart(244)
                    }
                    12 -> {
Coverage.whenCaseStart(245)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(246)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(247)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(248)
                        expectException = count > helper.size - helper.pos || count <= 0
Coverage.statementStart(249)
                        if (count == 0) {
Coverage.ifStart(250)
                            helper2.chunk.copy(helper.chunk, count)
/*Coverage Unreachable*/
                        }
Coverage.statementStart(257)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(258)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(259)
                            for (i in 0 until c) {
Coverage.forLoopStart(260)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(261)
                                helper.pos++
Coverage.statementStart(262)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(263)
                            }
Coverage.statementStart(264)
                            helper2.chunk.copy(helper.chunk, c)
Coverage.statementStart(265)
                            count -= c
Coverage.statementStart(266)
                        }
Coverage.statementStart(267)
                        if (count > 0) {
Coverage.ifStart(268)
                            expectException = true
Coverage.statementStart(269)
                            for (i in 0 until count) {
Coverage.forLoopStart(270)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(271)
                                helper.pos++
Coverage.statementStart(272)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(273)
                            }
Coverage.statementStart(274)
                            helper2.chunk.copy(helper.chunk, count)
Coverage.statementStart(275)
                        }
Coverage.statementStart(276)
                    }
                    13 -> {
Coverage.whenCaseStart(277)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(278)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(279)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(280)
                        val columns2 = allcolumns.toTypedArray()
Coverage.statementStart(281)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(282)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(283)
                        log("helper2 ${helper2.chunk}")
Coverage.statementStart(284)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(285)
                        log("count $count")
Coverage.statementStart(286)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
Coverage.statementStart(287)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
Coverage.statementStart(288)
                        if (count == 0) {
Coverage.ifStart(289)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(290)
                        }
Coverage.statementStart(291)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(292)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(293)
                            log("progress $c")
Coverage.statementStart(294)
                            for (i in 0 until c) {
Coverage.forLoopStart(295)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(296)
                                helper.pos++
Coverage.statementStart(297)
                                for (col in columns2) {
Coverage.forLoopStart(298)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(299)
                                }
Coverage.statementStart(300)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(301)
                            }
Coverage.statementStart(302)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
Coverage.statementStart(303)
                            helper.chunk.skipPos(columns2, c)
Coverage.statementStart(304)
                            helper2.chunk.skipSize(columns2, c)
Coverage.statementStart(305)
                            count -= c
Coverage.statementStart(306)
                        }
Coverage.statementStart(307)
                        if (count > 0) {
Coverage.ifStart(308)
                            expectException = true
Coverage.statementStart(309)
                            for (i in 0 until count) {
Coverage.forLoopStart(310)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(311)
                                helper.pos++
Coverage.statementStart(312)
                                for (col in columns2) {
Coverage.forLoopStart(313)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(314)
                                }
Coverage.statementStart(315)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(316)
                            }
Coverage.statementStart(317)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(318)
                            helper.chunk.skipPos(columns2, count)
Coverage.statementStart(319)
                            helper2.chunk.skipSize(columns2, count)
Coverage.statementStart(320)
                        }
Coverage.statementStart(321)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(323)
                if (expectException) {
Coverage.ifStart(324)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(325)
                log("" + expectException)
Coverage.statementStart(326)
                log("\n")
Coverage.statementStart(327)
                for (helper in helpers) {
Coverage.forLoopStart(328)
                    log("helper ${helper.chunk}")
Coverage.statementStart(329)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(330)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(331)
                    log("skippos ${-helper.pos}")
Coverage.statementStart(332)
                    if (helper.pos != 0) {
Coverage.ifStart(333)
                        helper.chunk.skipPos(-helper.pos)
Coverage.statementStart(334)
                    }
Coverage.statementStart(335)
                    for (j in 0 until helper.size) {
Coverage.forLoopStart(336)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(337)
                        val w = helper.kotlinList[j]
Coverage.statementStart(338)
                        for (i in 0 until columns) {
Coverage.forLoopStart(339)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
Coverage.statementStart(340)
                        }
Coverage.statementStart(341)
                    }
Coverage.statementStart(342)
                    if (helper.pos - helper.size != 0) {
Coverage.ifStart(343)
                        helper.chunk.skipPos(helper.pos - helper.size)
Coverage.statementStart(344)
                    }
Coverage.statementStart(345)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(346)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(347)
                }
Coverage.statementStart(348)
                log("\n")
Coverage.statementStart(349)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(351)
        } catch (e: Throwable) {
Coverage.statementStart(352)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(354)
        }
Coverage.statementStart(355)
    }
}
