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
Coverage.funStart(136)
        if (a < b) {
Coverage.ifStart(137)
            return b
        }
Coverage.statementStart(138)
        return a
    }
    fun min(a: Int, b: Int): Int {
Coverage.funStart(139)
        if (a > b) {
Coverage.ifStart(140)
            return b
        }
Coverage.statementStart(141)
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
Coverage.forLoopStart(142)
                resultSet.createVariable("name$i")
            }
        }
    }
    fun log(s: String) {
Coverage.funStart(143)
        if (verbose) {
Coverage.ifStart(144)
            println(s)
Coverage.statementStart(145)
        }
Coverage.statementStart(146)
    }
    operator fun invoke(buffer: DynamicByteArray) {
Coverage.funStart(147)
        var expectException = false
Coverage.statementStart(148)
        log("-----------------------start")
Coverage.statementStart(149)
        try {
Coverage.statementStart(150)
            columns = nextRandom(buffer, MAX_COLUMNS - 1, true) + 1
Coverage.statementStart(151)
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
Coverage.statementStart(152)
            require(ResultVektor.capacity > 0)
Coverage.statementStart(153)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
Coverage.statementStart(154)
            while (true) {
Coverage.whileLoopStart(155)
                expectException = false
Coverage.statementStart(156)
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(157)
                val helper = helpers[helperIdx]
Coverage.statementStart(158)
                log("helper ${helper.chunk}")
Coverage.statementStart(159)
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
Coverage.statementStart(160)
                log("func $func")
Coverage.statementStart(161)
                when (func) {
                    0 -> {
Coverage.whenCaseStart(162)
                        val count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(163)
                        log("count $count")
Coverage.statementStart(164)
                        expectException = helper.pos + count > helper.size || count == 0 || helper.pos + count < 0
Coverage.statementStart(165)
                        helper.chunk.skipPos(count)
Coverage.statementStart(166)
                        helper.pos += count
Coverage.statementStart(167)
                    }
                    1 -> {
Coverage.whenCaseStart(168)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(169)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(170)
                            count = helper.pos - helper.size
Coverage.statementStart(171)
                        }
Coverage.statementStart(172)
                        log("count $count")
Coverage.statementStart(173)
                        expectException = helper.size + count < 0 || count == 0 || !helper.chunk.canAppend()
Coverage.statementStart(174)
                        helper.chunk.skipSize(count)
Coverage.statementStart(175)
                        if (count > 0) {
Coverage.ifStart(176)
                            for (i in 0 until count) {
Coverage.forLoopStart(177)
                                helper.kotlinList.add(helper.size, Array(columns) { DONT_CARE_VALUE })
Coverage.statementStart(178)
                            }
Coverage.statementStart(179)
                        } else {
Coverage.ifStart(180)
                            if (!expectException) {
Coverage.ifStart(181)
                                for (i in 0 until -count) {
Coverage.forLoopStart(182)
                                    helper.kotlinList.removeAt(helper.size + count)
Coverage.statementStart(183)
                                }
Coverage.statementStart(184)
                            }
Coverage.statementStart(185)
                        }
Coverage.statementStart(186)
                        helper.size += count
Coverage.statementStart(187)
                    }
                    2 -> {
Coverage.whenCaseStart(188)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(189)
                        if (count < 0 && helper.pos > helper.size + count) {
Coverage.ifStart(190)
                            count = helper.pos - helper.size
Coverage.statementStart(191)
                        }
Coverage.statementStart(192)
                        log("count $count")
Coverage.statementStart(193)
                        val value = Array(columns) { nextRandom(buffer, MAX_DISTINCT_VALUES, false) }
Coverage.statementStart(194)
                        expectException = count <= 0 || !helper.chunk.canAppend()
Coverage.statementStart(195)
                        helper.chunk.append(value, count)
Coverage.statementStart(196)
                        for (i in 0 until count) {
Coverage.forLoopStart(197)
                            helper.kotlinList.add(helper.size, value)
Coverage.statementStart(198)
                        }
Coverage.statementStart(199)
                        helper.size += count
Coverage.statementStart(200)
                    }
                    3 -> {
Coverage.whenCaseStart(201)
                        helper.backup = helper.pos
Coverage.statementStart(202)
                        helper.chunk.backupPosition()
Coverage.statementStart(203)
                    }
                    4 -> {
Coverage.whenCaseStart(204)
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
                        if (count == 0) {
Coverage.ifStart(245)
                            helper2.chunk.copy(helper.chunk, count)
/*Coverage Unreachable*/
                        }
Coverage.statementStart(247)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(248)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(249)
                            for (i in 0 until c) {
Coverage.forLoopStart(250)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(251)
                                helper.pos++
Coverage.statementStart(252)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(253)
                            }
Coverage.statementStart(254)
                            helper2.chunk.copy(helper.chunk, c)
Coverage.statementStart(255)
                            count -= c
Coverage.statementStart(256)
                        }
Coverage.statementStart(257)
                        if (count > 0) {
Coverage.ifStart(258)
                            expectException = true
Coverage.statementStart(259)
                            for (i in 0 until count) {
Coverage.forLoopStart(260)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(261)
                                helper.pos++
Coverage.statementStart(262)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(263)
                            }
Coverage.statementStart(264)
                            helper2.chunk.copy(helper.chunk, count)
Coverage.statementStart(265)
                        }
Coverage.statementStart(266)
                    }
                    13 -> {
Coverage.whenCaseStart(267)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(268)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(269)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(270)
                        val columns2 = allcolumns.toTypedArray()
Coverage.statementStart(271)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(272)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(273)
                        log("helper2 ${helper2.chunk}")
Coverage.statementStart(274)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(275)
                        log("count $count")
Coverage.statementStart(276)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
Coverage.statementStart(277)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
Coverage.statementStart(278)
                        if (count == 0) {
Coverage.ifStart(279)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(280)
                        }
Coverage.statementStart(281)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(282)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(283)
                            log("progress $c")
Coverage.statementStart(284)
                            for (i in 0 until c) {
Coverage.forLoopStart(285)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(286)
                                helper.pos++
Coverage.statementStart(287)
                                for (col in columns2) {
Coverage.forLoopStart(288)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(289)
                                }
Coverage.statementStart(290)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(291)
                            }
Coverage.statementStart(292)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
Coverage.statementStart(293)
                            helper.chunk.skipPos(columns2, c)
Coverage.statementStart(294)
                            helper2.chunk.skipSize(columns2, c)
Coverage.statementStart(295)
                            count -= c
Coverage.statementStart(296)
                        }
Coverage.statementStart(297)
                        if (count > 0) {
Coverage.ifStart(298)
                            expectException = true
Coverage.statementStart(299)
                            for (i in 0 until count) {
Coverage.forLoopStart(300)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(301)
                                helper.pos++
Coverage.statementStart(302)
                                for (col in columns2) {
Coverage.forLoopStart(303)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(304)
                                }
Coverage.statementStart(305)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(306)
                            }
Coverage.statementStart(307)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(308)
                            helper.chunk.skipPos(columns2, count)
Coverage.statementStart(309)
                            helper2.chunk.skipSize(columns2, count)
Coverage.statementStart(310)
                        }
Coverage.statementStart(311)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(313)
                if (expectException) {
Coverage.ifStart(314)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(315)
                log("" + expectException)
Coverage.statementStart(316)
                log("\n")
Coverage.statementStart(317)
                for (helper in helpers) {
Coverage.forLoopStart(318)
                    log("helper ${helper.chunk}")
Coverage.statementStart(319)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(320)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(321)
                    log("skippos ${-helper.pos}")
Coverage.statementStart(322)
                    if (helper.pos != 0) {
Coverage.ifStart(323)
                        helper.chunk.skipPos(-helper.pos)
Coverage.statementStart(324)
                    }
Coverage.statementStart(325)
                    for (j in 0 until helper.size) {
Coverage.forLoopStart(326)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(327)
                        val w = helper.kotlinList[j]
Coverage.statementStart(328)
                        for (i in 0 until columns) {
Coverage.forLoopStart(329)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
Coverage.statementStart(330)
                        }
Coverage.statementStart(331)
                    }
Coverage.statementStart(332)
                    if (helper.pos - helper.size != 0) {
Coverage.ifStart(333)
                        helper.chunk.skipPos(helper.pos - helper.size)
Coverage.statementStart(334)
                    }
Coverage.statementStart(335)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(336)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(337)
                }
Coverage.statementStart(338)
                log("\n")
Coverage.statementStart(339)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(341)
        } catch (e: Throwable) {
Coverage.statementStart(342)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(344)
        }
Coverage.statementStart(345)
    }
}
