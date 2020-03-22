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
                        helper.pos = helper.backup
Coverage.statementStart(204)
                        helper.chunk.restorePosition()
Coverage.statementStart(205)
                    }
                    5 -> {
Coverage.whenCaseStart(206)
                        require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(207)
                    }
                    6 -> {
Coverage.whenCaseStart(208)
                        require(helper.chunk.canAppend() || helper.size >= ResultVektor.capacity)
Coverage.statementStart(209)
                    }
                    7 -> {
Coverage.whenCaseStart(210)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(211)
                        val v = helper.chunk.current()
Coverage.statementStart(212)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(213)
                        for (i in 0 until columns) {
Coverage.forLoopStart(214)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(215)
                        }
Coverage.statementStart(216)
                    }
                    8 -> {
Coverage.whenCaseStart(217)
                        expectException = helper.pos >= helper.size
Coverage.statementStart(218)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(219)
                        val w = helper.kotlinList[helper.pos]
Coverage.statementStart(220)
                        for (i in 0 until columns) {
Coverage.forLoopStart(221)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(222)
                        }
Coverage.statementStart(223)
                        helper.pos++
Coverage.statementStart(224)
                    }
                    9 -> {
Coverage.whenCaseStart(225)
                        require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(226)
                    }
                    10 -> {
Coverage.whenCaseStart(227)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(228)
                        expectException = colcount == 0 || helper.pos >= helper.size
Coverage.statementStart(229)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(230)
                        val columns = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(231)
                        val v = helper.chunk.current(columns)
Coverage.statementStart(232)
                        val w = Array(colcount) { helper.kotlinList[helper.pos][columns[it].toInt()] }
Coverage.statementStart(233)
                        for (i in 0 until colcount) {
Coverage.forLoopStart(234)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE)
Coverage.statementStart(235)
                        }
Coverage.statementStart(236)
                    }
                    11 -> {
Coverage.whenCaseStart(237)
                        require((helper.chunk.availableRead() > 0) == helper.chunk.hasNext())
Coverage.statementStart(238)
                    }
                    12 -> {
Coverage.whenCaseStart(239)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(240)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(241)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(242)
                        expectException = count > helper.size - helper.pos || count <= 0
Coverage.statementStart(243)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(244)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(245)
                            for (i in 0 until c) {
Coverage.forLoopStart(246)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(247)
                                helper.pos++
Coverage.statementStart(248)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(249)
                            }
Coverage.statementStart(250)
                            helper2.chunk.copy(helper.chunk, c)
Coverage.statementStart(251)
                            count -= c
Coverage.statementStart(252)
                        }
Coverage.statementStart(253)
                        if (count > 0) {
Coverage.ifStart(254)
                            expectException = true
Coverage.statementStart(255)
                            for (i in 0 until count) {
Coverage.forLoopStart(256)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(257)
                                helper.pos++
Coverage.statementStart(258)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(259)
                            }
Coverage.statementStart(260)
                            helper2.chunk.copy(helper.chunk, count)
Coverage.statementStart(261)
                        }
Coverage.statementStart(262)
                    }
                    13 -> {
Coverage.whenCaseStart(263)
                        val colcount = nextRandom(buffer, columns, true)
Coverage.statementStart(264)
                        val allcolumns = MutableList(columns) { it.toLong() }
Coverage.statementStart(265)
                        val columns1 = Array(colcount) { allcolumns.removeAt(nextRandom(buffer, allcolumns.size, true)) }
Coverage.statementStart(266)
                        val columns2 = allcolumns.toTypedArray()
Coverage.statementStart(267)
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
Coverage.statementStart(268)
                        val helper2 = helpers[helperIdx2]
Coverage.statementStart(269)
                        log("helper2 ${helper2.chunk}")
Coverage.statementStart(270)
                        var count = nextRandom(buffer, max(ResultVektor.capacity, helper.size), false)
Coverage.statementStart(271)
                        log("count $count")
Coverage.statementStart(272)
                        log("columns ${columns1.map { it }} ${columns2.map { it }}")
Coverage.statementStart(273)
                        expectException = count > helper.chunk.availableRead() || count <= 0 || colcount == 0
Coverage.statementStart(274)
                        if (count == 0) {
Coverage.ifStart(275)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(276)
                        }
Coverage.statementStart(277)
                        while (helper2.chunk.canAppend() && count > 0) {
Coverage.whileLoopStart(278)
                            val c = min(helper2.chunk.availableWrite(), count)
Coverage.statementStart(279)
                            log("progress $c")
Coverage.statementStart(280)
                            for (i in 0 until c) {
Coverage.forLoopStart(281)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(282)
                                helper.pos++
Coverage.statementStart(283)
                                for (col in columns2) {
Coverage.forLoopStart(284)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(285)
                                }
Coverage.statementStart(286)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(287)
                            }
Coverage.statementStart(288)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, c)
Coverage.statementStart(289)
                            helper.chunk.skipPos(columns2, c)
Coverage.statementStart(290)
                            helper2.chunk.skipSize(columns2, c)
Coverage.statementStart(291)
                            count -= c
Coverage.statementStart(292)
                        }
Coverage.statementStart(293)
                        if (count > 0) {
Coverage.ifStart(294)
                            expectException = true
Coverage.statementStart(295)
                            for (i in 0 until count) {
Coverage.forLoopStart(296)
                                val v = Array(columns) { helper.kotlinList[helper.pos][it] }
Coverage.statementStart(297)
                                helper.pos++
Coverage.statementStart(298)
                                for (col in columns2) {
Coverage.forLoopStart(299)
                                    v[col.toInt()] = DONT_CARE_VALUE
Coverage.statementStart(300)
                                }
Coverage.statementStart(301)
                                helper2.kotlinList.add(helper2.size++, v)
Coverage.statementStart(302)
                            }
Coverage.statementStart(303)
                            helper2.chunk.copy(columns1, helper.chunk, columns1, count)
Coverage.statementStart(304)
                            helper.chunk.skipPos(columns2, count)
Coverage.statementStart(305)
                            helper2.chunk.skipSize(columns2, count)
Coverage.statementStart(306)
                        }
Coverage.statementStart(307)
                    }
                    else -> {
/*Coverage Unreachable*/
                    }
                }
Coverage.statementStart(308)
                if (expectException) {
Coverage.ifStart(309)
                    throw Exception("there should be an exception")
                }
Coverage.statementStart(310)
                log("" + expectException)
Coverage.statementStart(311)
                log("\n")
Coverage.statementStart(312)
                for (helper in helpers) {
Coverage.forLoopStart(313)
                    log("helper ${helper.chunk}")
Coverage.statementStart(314)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(315)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(316)
                    log("skippos ${-helper.pos}")
Coverage.statementStart(317)
                    if (helper.pos != 0) {
Coverage.ifStart(318)
                        helper.chunk.skipPos(-helper.pos)
Coverage.statementStart(319)
                    }
Coverage.statementStart(320)
                    for (j in 0 until helper.size) {
Coverage.forLoopStart(321)
                        val v = helper.chunk.nextArr()
Coverage.statementStart(322)
                        val w = helper.kotlinList[j]
Coverage.statementStart(323)
                        for (i in 0 until columns) {
Coverage.forLoopStart(324)
                            require(v[i] == w[i] || w[i] == DONT_CARE_VALUE, { "${helper.kotlinList.map { it.map { it }.toString() + "\n" }}\n ${v.map { it }} ${w.map { it }}" })
Coverage.statementStart(325)
                        }
Coverage.statementStart(326)
                    }
Coverage.statementStart(327)
                    if (helper.pos - helper.size != 0) {
Coverage.ifStart(328)
                        helper.chunk.skipPos(helper.pos - helper.size)
Coverage.statementStart(329)
                    }
Coverage.statementStart(330)
                    require(helper.size - helper.pos == helper.chunk.availableRead(), { "${helper.size} ${helper.pos} ${helper.chunk.availableRead()}" })
Coverage.statementStart(331)
                    require(helper.chunk.availableWrite() >= ResultVektor.capacity - helper.size - 1, { "${helper.chunk.availableWrite()} ${ResultVektor.capacity} ${helper.size}" })
Coverage.statementStart(332)
                }
Coverage.statementStart(333)
                log("\n")
Coverage.statementStart(334)
            }
/*Coverage Unreachable*/
        } catch (e: NoMoreRandomException) {
Coverage.statementStart(335)
        } catch (e: Throwable) {
Coverage.statementStart(336)
            if (!expectException) {
/*Coverage Unreachable*/
                throw e
            }
Coverage.statementStart(337)
        }
Coverage.statementStart(338)
    }
}
