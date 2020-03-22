package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultChunkBaseTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(158)
            if (a < b) {
                Coverage.ifStart(159)
                return -1
            }
            Coverage.statementStart(160)
            if (a == b) {
                Coverage.ifStart(161)
                throw Exception("dont compare equal values using comparator")
            }
            Coverage.statementStart(162)
            return 1
        }
    }

    val UNDEF_VALUE = Int.MAX_VALUE
    val DONT_CARE_VALUE = -Int.MAX_VALUE
    val MAX_COLUMNS = 10
    val MAX_DISTINCT_VALUES = 20
    val MAX_CAPACITY = 100
    val FUNCTION_COUNT = 14
    val MAX_LISTS = 4
    val verbose = false

    class NoMoreRandomException() : Exception("")

    fun nextRandom(buffer: DynamicByteArray, max: Int, positiveOnly: Boolean): Int {
        Coverage.funStart(163)
        try {
            Coverage.statementStart(164)
            val res = buffer.getNextInt() % max
            Coverage.statementStart(165)
            if (positiveOnly && res < 0) {
                Coverage.ifStart(166)
                return -res
            }
            Coverage.statementStart(167)
            return res
        } catch (e: Throwable) {
            Coverage.statementStart(168)
            throw NoMoreRandomException()
        }
        Coverage.statementStart(169)
    }

    fun max(a: Int, b: Int): Int {
        Coverage.funStart(170)
        if (a < b) {
            Coverage.ifStart(171)
            return b
        }
        Coverage.statementStart(172)
        return a
    }

    fun min(a: Int, b: Int): Int {
        Coverage.funStart(173)
        if (a > b) {
            Coverage.ifStart(174)
            return b
        }
        Coverage.statementStart(175)
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
                Coverage.forLoopStart(176)
                resultSet.createVariable("name$i")
            }
        }
    }

    fun log(s: String) {
        Coverage.funStart(177)
        if (verbose) {
            Coverage.ifStart(178)
            println(s)
            Coverage.statementStart(179)
        }
        Coverage.statementStart(180)
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(181)
        var expectException = false
        Coverage.statementStart(182)
        log("-----------------------start")
        Coverage.statementStart(183)
        try {
            Coverage.statementStart(184)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
            Coverage.statementStart(185)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            Coverage.statementStart(186)
            require(ResultVektor.capacity > 0)
            Coverage.statementStart(187)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            Coverage.statementStart(188)
            while (true) {
                Coverage.whileLoopStart(189)
                expectException = false
                Coverage.statementStart(190)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                Coverage.statementStart(191)
                val helper = helpers[helperIdx]
                Coverage.statementStart(192)
                log("helper ${helper.chunk}")
                Coverage.statementStart(193)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                Coverage.statementStart(194)
                log("func $func")
                Coverage.statementStart(195)
                when (func) {
                    0 -> {
                        Coverage.whenCaseStart(196)
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(197)
                        log("count $count")
                        Coverage.statementStart(198)
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
                        Coverage.statementStart(199)
                        helper.chunk.skipPos(count)
                        Coverage.statementStart(200)
                        helper.pos += count
                        Coverage.statementStart(201)
                    }
                    1 -> {
                        Coverage.whenCaseStart(202)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(203)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(204)
                            count = helper.pos - helper.size
                            Coverage.statementStart(205)
                        }
                        Coverage.statementStart(206)
                        log("count $count")
                        Coverage.statementStart(207)
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
                        Coverage.statementStart(208)
                        helper.chunk.skipSize(count)
                        Coverage.statementStart(209)
                        if (count > 0) {
                            Coverage.ifStart(210)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(211)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
                                Coverage.statementStart(212)
                            }
                            Coverage.statementStart(213)
                        } else {
                            Coverage.ifStart(214)
                            if (!expectException) {
                                Coverage.ifStart(215)
                                for (i in 0 until -count) {
                                    Coverage.forLoopStart(216)
                                    helper.kotlinList.removeAt(helper.size + count)
                                    Coverage.statementStart(217)
                                }
                                Coverage.statementStart(218)
                            }
                            Coverage.statementStart(219)
                        }
                        Coverage.statementStart(220)
                        helper.size += count
                        Coverage.statementStart(221)
                    }
                    2 -> {
                        Coverage.whenCaseStart(222)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(223)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(224)
                            count = helper.pos - helper.size
                            Coverage.statementStart(225)
                        }
                        Coverage.statementStart(226)
                        log("count $count")
                        Coverage.statementStart(227)
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
                        Coverage.statementStart(228)
                        expectException = count <= 0 || !helper.chunk.canAppend()
                        Coverage.statementStart(229)
                        helper.chunk.append(value, count)
                        Coverage.statementStart(230)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(231)
                            helper.kotlinList.add(helper.size, value)
                            Coverage.statementStart(232)
                        }
                        Coverage.statementStart(233)
                        helper.size += count
                        Coverage.statementStart(234)
                    }
                    3 -> {
                        Coverage.whenCaseStart(235)
                        helper.backup = helper.pos
                        Coverage.statementStart(236)
                        helper.chunk.backupPosition()
                        Coverage.statementStart(237)
                    }
                    4 -> {
                        Coverage.whenCaseStart(238)
                        helper.pos = helper.backup
                        Coverage.statementStart(239)
                        helper.chunk.restorePosition()
                        Coverage.statementStart(240)
                    }
                    5 -> {
                        Coverage.whenCaseStart(241)
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                        Coverage.statementStart(242)
                    }
                    6 -> {
                        Coverage.whenCaseStart(243)
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
                        Coverage.statementStart(244)
                    }
                    7 -> {
                        Coverage.whenCaseStart(245)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(246)
                        val v = helper.chunk.current()
                        Coverage.statementStart(247)
                        val w = helper.kotlinList[helper.pos]
                        Coverage.statementStart(248)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(249)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(250)
                        }
                        Coverage.statementStart(251)
                    }
                    8 -> {
                        Coverage.whenCaseStart(252)
                        expectException = helper.pos >= helper.size
                        Coverage.statementStart(253)
                        val v = helper.chunk.nextArr()
                        Coverage.statementStart(254)
                        val w = helper.kotlinList[helper.pos]
                        Coverage.statementStart(255)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(256)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(257)
                        }
                        Coverage.statementStart(258)
                        helper.pos++
                        Coverage.statementStart(259)
                    }
                    9 -> {
                        Coverage.whenCaseStart(260)
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                        Coverage.statementStart(261)
                    }
                    10 -> {
                        Coverage.whenCaseStart(262)
                        val colcount = nextRandom(buffer, columns, true)
                        Coverage.statementStart(263)
                        expectException = colcount == 0 || helper.pos >= helper.size
                        Coverage.statementStart(264)
                        val allcolumns = MutableList(columns) { it.toLong() }
                        Coverage.statementStart(265)
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        Coverage.statementStart(266)
                        val v = helper.chunk.current(columns)
                        Coverage.statementStart(267)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
                        Coverage.statementStart(268)
                        for (i in 0 until colcount) {
                            Coverage.forLoopStart(269)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
                            Coverage.statementStart(270)
                        }
                        Coverage.statementStart(271)
                    }
                    11 -> {
                        Coverage.whenCaseStart(272)
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
                        Coverage.statementStart(273)
                    }
                    12 -> {
                        Coverage.whenCaseStart(274)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(275)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(276)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(277)
                        expectException = count > helper.size - helper.pos || count <= 0
                        Coverage.statementStart(278)
                        if (count == 0) {
                            Coverage.ifStart(279)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(280)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(281)
                                helper.pos++
                                Coverage.statementStart(282)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(283)
                            }
                            Coverage.statementStart(284)
                            helper2.chunk.copy(helper.chunk, count)
                            Coverage.statementStart(285)
                        }
                        Coverage.statementStart(286)
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(287)
                            val c = min(helper2.chunk.availableWrite(), count)
                            Coverage.statementStart(288)
                            for (i in 0 until c) {
                                Coverage.forLoopStart(289)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(290)
                                helper.pos++
                                Coverage.statementStart(291)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(292)
                            }
                            Coverage.statementStart(293)
                            helper2.chunk.copy(helper.chunk, c)
                            Coverage.statementStart(294)
                            count -= c
                            Coverage.statementStart(295)
                        }
                        Coverage.statementStart(296)
                        if (count > 0) {
                            Coverage.ifStart(297)
                            expectException = true
                            Coverage.statementStart(298)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(299)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(300)
                                helper.pos++
                                Coverage.statementStart(301)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(302)
                            }
                            Coverage.statementStart(303)
                            helper2.chunk.copy(helper.chunk, count)
                            Coverage.statementStart(304)
                        }
                        Coverage.statementStart(305)
                    }
                    13 -> {
                        Coverage.whenCaseStart(306)
                        val colcount = nextRandom(buffer, columns, true)
                        Coverage.statementStart(307)
                        val allcolumns = MutableList(columns) { it.toLong() }
                        Coverage.statementStart(308)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
                        Coverage.statementStart(309)
                        val columns2 = allcolumns.toTypedArray()
                        Coverage.statementStart(310)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        Coverage.statementStart(311)
                        val helper2 = helpers[helperIdx2]
                        Coverage.statementStart(312)
                        log("helper2 ${helper2.chunk}")
                        Coverage.statementStart(313)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
                        Coverage.statementStart(314)
                        log("count $count")
                        Coverage.statementStart(315)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
                        Coverage.statementStart(316)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
                        Coverage.statementStart(317)
                        if (count == 0) {
                            Coverage.ifStart(318)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(319)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(320)
                                helper.pos++
                                Coverage.statementStart(321)
                                for (col in columns2) {
                                    Coverage.forLoopStart(322)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                    Coverage.statementStart(323)
                                }
                                Coverage.statementStart(324)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(325)
                            }
                            Coverage.statementStart(326)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            Coverage.statementStart(327)
                            helper.chunk.skipPos(columns2, count)
                            Coverage.statementStart(328)
                            helper2.chunk.skipSize(columns2, count)
                            Coverage.statementStart(329)
                        }
                        Coverage.statementStart(330)
                        while (helper2.chunk.canAppend() && count > 0) {
                            Coverage.whileLoopStart(331)
                            val c = min(helper2.chunk.availableWrite(), count)
                            Coverage.statementStart(332)
                            log("progress $c")
                            Coverage.statementStart(333)
                            for (i in 0 until c) {
                                Coverage.forLoopStart(334)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(335)
                                helper.pos++
                                Coverage.statementStart(336)
                                for (col in columns2) {
                                    Coverage.forLoopStart(337)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                    Coverage.statementStart(338)
                                }
                                Coverage.statementStart(339)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(340)
                            }
                            Coverage.statementStart(341)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
                            Coverage.statementStart(342)
                            helper.chunk.skipPos(columns2, c)
                            Coverage.statementStart(343)
                            helper2.chunk.skipSize(columns2, c)
                            Coverage.statementStart(344)
                            count -= c
                            Coverage.statementStart(345)
                        }
                        Coverage.statementStart(346)
                        if (count > 0) {
                            Coverage.ifStart(347)
                            expectException = true
                            Coverage.statementStart(348)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(349)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
                                Coverage.statementStart(350)
                                helper.pos++
                                Coverage.statementStart(351)
                                for (col in columns2) {
                                    Coverage.forLoopStart(352)
                                    v[col.toInt()] = DONT_CARE_VALUE
                                    Coverage.statementStart(353)
                                }
                                Coverage.statementStart(354)
                                helper2.kotlinList.add(helper2.size++, v)
                                Coverage.statementStart(355)
                            }
                            Coverage.statementStart(356)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
                            Coverage.statementStart(357)
                            helper.chunk.skipPos(columns2, count)
                            Coverage.statementStart(358)
                            helper2.chunk.skipSize(columns2, count)
                            Coverage.statementStart(359)
                        }
                        Coverage.statementStart(360)
                    }
                    else -> {
                        Coverage.whenCaseStart(361)
                        require(func < FUNCTION_COUNT)
                        Coverage.statementStart(362)
                    }
                }
                Coverage.statementStart(363)
                if (expectException) {
                    Coverage.ifStart(364)
                    throw Exception("there should be an exception")
                }
                Coverage.statementStart(365)
                log("" + expectException)
                Coverage.statementStart(366)
                log("\n")
                Coverage.statementStart(367)
                for (helper in helpers) {
                    Coverage.forLoopStart(368)
                    log("helper ${helper.chunk}")
                    Coverage.statementStart(369)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    Coverage.statementStart(370)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    Coverage.statementStart(371)
                    log("skippos ${-helper.pos}")
                    Coverage.statementStart(372)
                    if (helper.pos != 0) {
                        Coverage.ifStart(373)
                        helper.chunk.skipPos(-helper.pos)
                        Coverage.statementStart(374)
                    }
                    Coverage.statementStart(375)
                    for (j in 0 until helper.size) {
                        Coverage.forLoopStart(376)
                        val v = helper.chunk.nextArr()
                        Coverage.statementStart(377)
                        val w = helper.kotlinList[j]
                        Coverage.statementStart(378)
                        for (i in 0 until columns) {
                            Coverage.forLoopStart(379)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
                            Coverage.statementStart(380)
                        }
                        Coverage.statementStart(381)
                    }
                    Coverage.statementStart(382)
                    if (helper.pos - helper.size != 0) {
                        Coverage.ifStart(383)
                        helper.chunk.skipPos(helper.pos - helper.size)
                        Coverage.statementStart(384)
                    }
                    Coverage.statementStart(385)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
                    Coverage.statementStart(386)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
                    Coverage.statementStart(387)
                }
                Coverage.statementStart(388)
                log("\n")
                Coverage.statementStart(389)
            }
            Coverage.statementStart(390)
        } catch (e: NoMoreRandomException) {
            Coverage.statementStart(391)
        } catch (e: Throwable) {
            Coverage.statementStart(392)
            if (!expectException) {
                Coverage.ifStart(393)
                throw e
            }
            Coverage.statementStart(394)
        }
        Coverage.statementStart(395)
    }
}
/*
    fun append(row: ResultRow, count: Int = 1) {
Coverage.funStart(396)
    open fun copy(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, count: Int) {
Coverage.funStart(397)
    open fun copyNonNull(columnsTo: Array<Variable>, arrFrom: Array<Value>, columnsFrom: Array<Variable>, arrFromAlternative: Array<Value>, count: Int) {
Coverage.funStart(398)
    fun getColumn(variable: Variable) = data[variable.toInt()]
Coverage.statementStart(399)
    override fun next(): ResultRow {
Coverage.funStart(400)
    fun setColumn(variable: Variable, col: ResultVektor) {
Coverage.funStart(401)
    fun sameElements(columns: Array<Variable>): Int {
Coverage.funStart(402)
    fun sameElements(): Int {
Coverage.funStart(403)
    override fun toString(): String {
Coverage.funStart(404)
*/
