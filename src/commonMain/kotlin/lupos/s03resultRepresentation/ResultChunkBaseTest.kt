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
        Coverage.statementStart(136)
    }

    fun max(a: Int, b: Int): Int {
        Coverage.funStart(137)
        if (a < b) {
            Coverage.ifStart(138)
            return b
        }
        Coverage.statementStart(139)
        return a
    }

    fun min(a: Int, b: Int): Int {
        Coverage.funStart(140)
        if (a > b) {
            Coverage.ifStart(141)
            return b
        }
        Coverage.statementStart(142)
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
                Coverage.forLoopStart(143)
                resultSet.createVariable("name$i")
            }
        }
    }

    fun log(s: String) {
        Coverage.funStart(144)
        if (verbose) {
            Coverage.ifStart(145)
            println(s)
            Coverage.statementStart(146)
        }
        Coverage.statementStart(147)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(148)
        var expectException = false
        Coverage.statementStart(149)
        log("-----------------------start")
        Coverage.statementStart(150)
        try {
            Coverage.statementStart(151)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            Coverage.statementStart(152)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            Coverage.statementStart(153)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(154)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            Coverage.statementStart(155)
            while (true) {
                Coverage.whileLoopStart(156)
                expectException = false
                Coverage.statementStart(157)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                Coverage.statementStart(158)
                val helper = helpers[helperIdx]
                Coverage.statementStart(159)
                log("helper ${helper.chunk}")
                Coverage.statementStart(160)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                Coverage.statementStart(161)
                log("func $func")
                Coverage.statementStart(162)
                when (func) {
                    0 -> {
                        Coverage.whenCaseStart(163)
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(164)
                        log("count $count")
                        Coverage.statementStart(165)
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
                        Coverage.statementStart(166)
                        helper.chunk.skipPos(count)
                        Coverage.statementStart(167)
                        helper.pos += count
                        Coverage.statementStart(168)
                    }
                    1 -> {
                        Coverage.whenCaseStart(169)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(170)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(171)
                            count = helper.pos - helper.size
                            Coverage.statementStart(172)
                        }
                        Coverage.statementStart(173)
                        log("count $count")
                        Coverage.statementStart(174)
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
                        Coverage.statementStart(175)
                        helper.chunk.skipSize(count)
                        Coverage.statementStart(176)
                        if (count > 0) {
                            Coverage.ifStart(177)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(178)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
                                Coverage.statementStart(179)
                            }
                            Coverage.statementStart(180)
                        } else {
                            Coverage.ifStart(181)
                            if (!expectException) {
                                Coverage.ifStart(182)
                                for (i in 0 until -count) {
                                    Coverage.forLoopStart(183)
                                    helper.kotlinList.removeAt(helper.size + count)
                                    Coverage.statementStart(184)
                                }
                                Coverage.statementStart(185)
                            }
                            Coverage.statementStart(186)
                        }
                        Coverage.statementStart(187)
                        helper.size += count
                        Coverage.statementStart(188)
                    }
                    2 -> {
                        Coverage.whenCaseStart(189)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(190)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(191)
                            count = helper.pos - helper.size
                            Coverage.statementStart(192)
                        }
                        Coverage.statementStart(193)
                        log("count $count")
                        Coverage.statementStart(194)
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                        Coverage.statementStart(195)
                        expectException = count <= 0 || !helper.chunk.canAppend()
                        Coverage.statementStart(196)
                        helper.chunk.append(value, count)
                        Coverage.statementStart(197)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(198)
                            helper.kotlinList.add(helper.size, value)
                            Coverage.statementStart(199)
                        }
                        Coverage.statementStart(200)
                        helper.size += count
                        Coverage.statementStart(201)
                    }
                    3 -> {
                        Coverage.whenCaseStart(202)
                        helper.backup = helper.pos
                        Coverage.statementStart(203)
                        helper.chunk.backupPosition()
                        Coverage.statementStart(204)
                    }
                    4 -> {
                        Coverage.whenCaseStart(205)
                        helper.pos = helper.backup
                        Coverage.statementStart(206)
                        helper.chunk.restorePosition()
                        Coverage.statementStart(207)
                    }
                    5 -> {
                        Coverage.whenCaseStart(208)
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                        Coverage.statementStart(209)
                    }
                    6 -> {
                        Coverage.whenCaseStart(210)
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
                        Coverage.statementStart(211)
                    }
                    7 -> {
                        Coverage.whenCaseStart(212)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(213)
                        val v = helper.chunk.current()
                        Coverage.statementStart(214)
                        val w = helper.kotlinList[helper.pos]
                        Coverage.statementStart(215)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(216)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(217)
                        }
                        Coverage.statementStart(218)
                    }
                    8 -> {
                        Coverage.whenCaseStart(219)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(220)
                        val v = helper.chunk.nextArr()
                        Coverage.statementStart(221)
                        val w = helper.kotlinList[helper.pos]
                        Coverage.statementStart(222)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(223)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(224)
                        }
                        Coverage.statementStart(225)
                        helper.pos++
                        Coverage.statementStart(226)
                    }
                    9 -> {
                        Coverage.whenCaseStart(227)
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                        Coverage.statementStart(228)
                    }
                    10 -> {
                        Coverage.whenCaseStart(229)
                        val colcount = nextRandom(buffer, columns, true)
                        Coverage.statementStart(230)
                        expectException = colcount == 0 || helper.pos >= helper.size
                        Coverage.statementStart(231)
                        val allcolumns = MutableList(columns) { it.toLong() }
                        Coverage.statementStart(232)
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        Coverage.statementStart(233)
                        val v = helper.chunk.current(columns)
                        Coverage.statementStart(234)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
                        Coverage.statementStart(235)
                        for (i in 0 until colcount) {
                            Coverage.forLoopStart(236)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(237)
                        }
                        Coverage.statementStart(238)
                    }
                    11 -> {
                        Coverage.whenCaseStart(239)
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
                        Coverage.statementStart(240)
                    }
                    12 -> {
                        Coverage.whenCaseStart(241)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(242)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(243)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(244)
                        expectException = count > helper.size - helper.pos || count <= 0
                        Coverage.statementStart(245)
                        if (count == 0) {
                            Coverage.ifStart(246)
                            helper2.chunk.copy(helper.chunk, count)
/*Coverage Unreachable*/
                            Coverage.statementStart(248)
                        }
                        Coverage.statementStart(249)
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(250)
                            val c = min(helper2.chunk.availableWrite(), count)
                            Coverage.statementStart(251)
                            for (i in 0 until c) {
                                Coverage.forLoopStart(252)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(253)
                                helper.pos++
                                Coverage.statementStart(254)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(255)
                            }
                            Coverage.statementStart(256)
                            helper2.chunk.copy(helper.chunk, c)
                            Coverage.statementStart(257)
                            count -= c
                            Coverage.statementStart(258)
                        }
                        Coverage.statementStart(259)
                        if (count > 0) {
                            Coverage.ifStart(260)
                            expectException = true
                            Coverage.statementStart(261)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(262)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(263)
                                helper.pos++
                                Coverage.statementStart(264)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(265)
                            }
                            Coverage.statementStart(266)
                            helper2.chunk.copy(helper.chunk, count)
                            Coverage.statementStart(267)
                        }
                        Coverage.statementStart(268)
                    }
                    13 -> {
                        Coverage.whenCaseStart(269)
                        val colcount = nextRandom(buffer, columns, true)
                        Coverage.statementStart(270)
                        val allcolumns = MutableList(columns) { it.toLong() }
                        Coverage.statementStart(271)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        Coverage.statementStart(272)
                        val columns2 = allcolumns.toTypedArray()
                        Coverage.statementStart(273)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(274)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(275)
                        log("helper2 ${helper2.chunk}")
                        Coverage.statementStart(276)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(277)
                        log("count $count")
                        Coverage.statementStart(278)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
                        Coverage.statementStart(279)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
                        Coverage.statementStart(280)
                        if (count == 0) {
                            Coverage.ifStart(281)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            Coverage.statementStart(282)
                        }
                        Coverage.statementStart(283)
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(284)
                            val c = min(helper2.chunk.availableWrite(), count)
                            Coverage.statementStart(285)
                            log("progress $c")
                            Coverage.statementStart(286)
                            for (i in 0 until c) {
                                Coverage.forLoopStart(287)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(288)
                                helper.pos++
                                Coverage.statementStart(289)
                                for (col in columns2) {
                                    Coverage.forLoopStart(290)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                    Coverage.statementStart(291)
                                }
                                Coverage.statementStart(292)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(293)
                            }
                            Coverage.statementStart(294)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
                            Coverage.statementStart(295)
                            helper.chunk.skipPos(columns2, c)
                            Coverage.statementStart(296)
                            helper2.chunk.skipSize(columns2, c)
                            Coverage.statementStart(297)
                            count -= c
                            Coverage.statementStart(298)
                        }
                        Coverage.statementStart(299)
                        if (count > 0) {
                            Coverage.ifStart(300)
                            expectException = true
                            Coverage.statementStart(301)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(302)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(303)
                                helper.pos++
                                Coverage.statementStart(304)
                                for (col in columns2) {
                                    Coverage.forLoopStart(305)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                    Coverage.statementStart(306)
                                }
                                Coverage.statementStart(307)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(308)
                            }
                            Coverage.statementStart(309)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            Coverage.statementStart(310)
                            helper.chunk.skipPos(columns2, count)
                            Coverage.statementStart(311)
                            helper2.chunk.skipSize(columns2, count)
                            Coverage.statementStart(312)
                        }
                        Coverage.statementStart(313)
                    }
                    else -> {
/*Coverage Unreachable*/
                        Coverage.statementStart(315)
                    }
                }
                Coverage.statementStart(316)
                if (expectException) {
                    Coverage.ifStart(317)
                    throw Exception("there should be an exception")
                }
                Coverage.statementStart(318)
                log("" + expectException)
                Coverage.statementStart(319)
                log("\n")
                Coverage.statementStart(320)
                for (helper in helpers) {
                    Coverage.forLoopStart(321)
                    log("helper ${helper.chunk}")
                    Coverage.statementStart(322)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    Coverage.statementStart(323)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    Coverage.statementStart(324)
                    log("skippos ${-helper.pos}")
                    Coverage.statementStart(325)
                    if (helper.pos != 0) {
                        Coverage.ifStart(326)
                        helper.chunk.skipPos(-helper.pos)
                        Coverage.statementStart(327)
                    }
                    Coverage.statementStart(328)
                    for (j in 0 until helper.size) {
                        Coverage.forLoopStart(329)
                        val v = helper.chunk.nextArr()
                        Coverage.statementStart(330)
                        val w = helper.kotlinList[j]
                        Coverage.statementStart(331)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(332)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
                            Coverage.statementStart(333)
                        }
                        Coverage.statementStart(334)
                    }
                    Coverage.statementStart(335)
                    if (helper.pos - helper.size != 0) {
                        Coverage.ifStart(336)
                        helper.chunk.skipPos(helper.pos - helper.size)
                        Coverage.statementStart(337)
                    }
                    Coverage.statementStart(338)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    Coverage.statementStart(339)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    Coverage.statementStart(340)
                }
                Coverage.statementStart(341)
                log("\n")
                Coverage.statementStart(342)
            }
/*Coverage Unreachable*/
            Coverage.statementStart(344)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(345)
        } catch (e: Throwable) {
            Coverage.statementStart(346)
            if (!expectException) {
/*Coverage Unreachable*/
                Coverage.statementStart(348)
                throw e
            }
            Coverage.statementStart(349)
        }
        Coverage.statementStart(350)
    }
}
