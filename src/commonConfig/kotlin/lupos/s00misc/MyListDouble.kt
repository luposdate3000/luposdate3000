package lupos.s00misc

import lupos.s00misc.Coverage

/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
/* Substitutions :: Double,,,DoubleArray, */
class MyListDouble {
    companion object {
        val capacity = 8
    }

    class MyListDoublePage() {
        var next: MyListDoublePage? = null
        var size = 0/*local*/
        val data = MyListDoubleSmall()
    }

    var pagecount = 1
    @JvmField
    var size = 0
    @JvmField
    var page = MyListDoublePage()
    @JvmField
    var lastpage = page

    fun shrinkToFit() {
        Coverage.funStart(0)
        if (pagecount > 5) {
            Coverage.ifStart(1)
            var capacity = pagecount * capacity
            Coverage.statementStart(2)
            if (capacity > size * 2) {
                Coverage.ifStart(3)
                var c = 1
                Coverage.statementStart(4)
                val b = MyListDoublePage()
                Coverage.statementStart(5)
                var t = b
                Coverage.statementStart(6)
                var it = iterator()
                Coverage.statementStart(7)
                while (it.hasNext()) {
                    Coverage.whileLoopStart(8)
                    var j = 0
                    Coverage.statementStart(9)
                    while (it.hasNext() && j < capacity) {
                        Coverage.whileLoopStart(10)
                        t.data[j] = it.next()
                        Coverage.statementStart(11)
                        j++
                        Coverage.statementStart(12)
                    }
                    Coverage.statementStart(13)
                    t.size = j
                    Coverage.statementStart(14)
                    if (it.hasNext()) {
                        Coverage.ifStart(15)
                        t.next = MyListDoublePage()
                        Coverage.statementStart(16)
                        t = t.next!!
                        Coverage.statementStart(17)
                        c++
                        Coverage.statementStart(18)
                    }
                    Coverage.statementStart(19)
                }
                Coverage.statementStart(20)
                pagecount = c
                Coverage.statementStart(21)
                page = b
                Coverage.statementStart(22)
                lastpage = t
                Coverage.statementStart(23)
            }
            Coverage.statementStart(24)
        }
        Coverage.statementStart(25)
        debug()
        Coverage.statementStart(26)
    }

    inline fun reserve(capacity: Int) {
        Coverage.funStart(27)
    }

    constructor() {
    }

    constructor(value: Double) {
        size = 1
        page.size = 1
        page.data[0] = value
        debug()
    }

    constructor(initialCapacity: Int, init: (Int) -> Double) {
        size = initialCapacity
        var i = 0
        var tmp = page
        while (i < size) {
            Coverage.whileLoopStart(28)
            var j = tmp.size
            while (tmp.size < capacity && i < size) {
                Coverage.whileLoopStart(29)
                tmp.data[j] = init(i++)
                j++
            }
            tmp.size = j
            if (i < size) {
                Coverage.ifStart(30)
                val p = MyListDoublePage()
                pagecount++
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
        debug()
    }

    fun clear() {
        Coverage.funStart(31)
        size = 0
        Coverage.statementStart(32)
        page = MyListDoublePage()
        Coverage.statementStart(33)
        pagecount = 1
        Coverage.statementStart(34)
        lastpage = page
        Coverage.statementStart(35)
        debug()
        Coverage.statementStart(36)
    }

    fun add(value: Double) {
        Coverage.funStart(37)
        if (lastpage.size < capacity) {
            Coverage.ifStart(38)
            println("lastpage.size < capacity ${lastpage.size} ${capacity}")
            lastpage.data[lastpage.size] = value
            Coverage.statementStart(39)
            lastpage.size++
            Coverage.statementStart(40)
        } else {
            Coverage.ifStart(41)
            lastpage.next = MyListDoublePage()
            Coverage.statementStart(42)
            pagecount++
            Coverage.statementStart(43)
            lastpage = lastpage.next!!
            Coverage.statementStart(44)
            lastpage.data[lastpage.size] = value
            Coverage.statementStart(45)
            lastpage.size++
            Coverage.statementStart(46)
        }
        Coverage.statementStart(47)
        size++
        Coverage.statementStart(48)
        debug()
        Coverage.statementStart(49)
        shrinkToFit()
        Coverage.statementStart(50)
    }

    inline operator fun get(idx: Int): Double {
        Coverage.funStart(51)
        require(idx < size)
        Coverage.statementStart(52)
        var tmp = page
        Coverage.statementStart(53)
        var offset = 0
        Coverage.statementStart(54)
        while (offset + tmp.size <= idx) {
            Coverage.whileLoopStart(55)
            offset += tmp.size
            Coverage.statementStart(56)
            require(tmp.next != null) { debug() + " $offset $idx" }
            Coverage.statementStart(57)
            tmp = tmp.next!!
            Coverage.statementStart(58)
        }
        Coverage.statementStart(59)
        debug()
        Coverage.statementStart(60)
        return tmp.data[idx - offset] as Double
    }

    fun remove(value: Double): Boolean {
        Coverage.funStart(61)
        var i = 0
        Coverage.statementStart(62)
        var tmp: MyListDoublePage? = page
        Coverage.statementStart(63)
        while (i < size) {
            Coverage.whileLoopStart(64)
            var j = 0
            Coverage.statementStart(65)
            while (j < tmp!!.size) {
                Coverage.whileLoopStart(66)
                if (tmp!!.data[j] == value) {
                    Coverage.ifStart(67)
                    while (j < tmp!!.size - 1) {
                        Coverage.whileLoopStart(68)
                        tmp.data[j] = tmp.data[j + 1]
                        Coverage.statementStart(69)
                        j++
                        Coverage.statementStart(70)
                    }
                    Coverage.statementStart(71)
                    tmp.size--
                    Coverage.statementStart(72)
                    size--
                    Coverage.statementStart(73)
                    debug()
                    Coverage.statementStart(74)
                    return true
                }
                Coverage.statementStart(75)
                j++
                Coverage.statementStart(76)
                i++
                Coverage.statementStart(77)
            }
            Coverage.statementStart(78)
            tmp = tmp.next
            Coverage.statementStart(79)
        }
        Coverage.statementStart(80)
        debug()
        Coverage.statementStart(81)
        return false
    }

    fun removeAt(idx: Int): Double {
        Coverage.funStart(82)
        var tmp = page
        Coverage.statementStart(83)
        var offset = 0
        Coverage.statementStart(84)
        while (offset + tmp.size < idx) {
            Coverage.whileLoopStart(85)
            offset += tmp.size
            Coverage.statementStart(86)
            tmp = tmp.next!!
            Coverage.statementStart(87)
        }
        Coverage.statementStart(88)
        var i = idx - offset
        Coverage.statementStart(89)
        var res = tmp.data[i] as Double
        Coverage.statementStart(90)
        while (i < tmp.size - 1) {
            Coverage.whileLoopStart(91)
            tmp.data[i] = tmp.data[i + 1]
            Coverage.statementStart(92)
            i++
            Coverage.statementStart(93)
        }
        Coverage.statementStart(94)
        tmp.size--
        Coverage.statementStart(95)
        size--
        Coverage.statementStart(96)
        debug()
        Coverage.statementStart(97)
        return res
    }

    inline operator fun set(idx: Int, value: Double) {
        Coverage.funStart(98)
        require(idx <= size)
        Coverage.statementStart(99)
        if (idx == size) {
            Coverage.ifStart(100)
            if (lastpage.size < capacity) {
                Coverage.ifStart(101)
                lastpage.data[lastpage.size] = value
                Coverage.statementStart(102)
                lastpage.size++
                Coverage.statementStart(103)
            } else {
                Coverage.ifStart(104)
                lastpage.next = MyListDoublePage()
                Coverage.statementStart(105)
                pagecount++
                Coverage.statementStart(106)
                lastpage = lastpage.next!!
                Coverage.statementStart(107)
                lastpage.size = 1
                Coverage.statementStart(108)
                lastpage.data[0] = value
                Coverage.statementStart(109)
            }
            Coverage.statementStart(110)
            size++
            Coverage.statementStart(111)
        } else {
            Coverage.ifStart(112)
            var tmp = page
            Coverage.statementStart(113)
            var offset = 0
            Coverage.statementStart(114)
            var t = idx
            Coverage.statementStart(115)
            while (t >= tmp.size) {
                Coverage.whileLoopStart(116)
                offset += tmp.size
                Coverage.statementStart(117)
                t = idx - offset
                Coverage.statementStart(118)
                tmp = tmp.next!!
                Coverage.statementStart(119)
            }
            Coverage.statementStart(120)
            tmp.data[t] = value
            Coverage.statementStart(121)
        }
        Coverage.statementStart(122)
        debug()
        Coverage.statementStart(123)
        shrinkToFit()
        Coverage.statementStart(124)
    }

    var flag = 0
    var flag2 = 0
    var flag3 = 0
    var flag4: Double? = null
    fun add(idx: Int, value: Double) {
        Coverage.funStart(125)
        flag3 = idx
        Coverage.statementStart(126)
        flag4 = value
        Coverage.statementStart(127)
        require(idx <= size)
        Coverage.statementStart(128)
        if (idx == size) {
            Coverage.ifStart(129)
            if (lastpage.size < capacity) {
                Coverage.ifStart(130)
                flag = 1
                Coverage.statementStart(131)
                lastpage.data[lastpage.size] = value
                Coverage.statementStart(132)
                lastpage.size++
                Coverage.statementStart(133)
            } else {
                Coverage.ifStart(134)
                flag = 2
                Coverage.statementStart(135)
                lastpage.next = MyListDoublePage()
                Coverage.statementStart(136)
                pagecount++
                Coverage.statementStart(137)
                lastpage = lastpage.next!!
                Coverage.statementStart(138)
                lastpage.data[lastpage.size] = value
                Coverage.statementStart(139)
                lastpage.size++
                Coverage.statementStart(140)
            }
            Coverage.statementStart(141)
        } else {
            Coverage.ifStart(142)
            flag = 3
            Coverage.statementStart(143)
            var tmp = page
            Coverage.statementStart(144)
            var offset = 0
            Coverage.statementStart(145)
            var t = idx
            Coverage.statementStart(146)
            while (t > tmp.size) {
                Coverage.whileLoopStart(147)
                offset += tmp.size
                Coverage.statementStart(148)
                t = idx - offset
                Coverage.statementStart(149)
                tmp = tmp.next!!
                Coverage.statementStart(150)
            }
            Coverage.statementStart(151)
            if (t == tmp.size && tmp.size < capacity) {
                Coverage.ifStart(152)
                flag = 5
                Coverage.statementStart(153)
                tmp.data[t] = value
                Coverage.statementStart(154)
                tmp.size++
                Coverage.statementStart(155)
            } else {
                Coverage.ifStart(156)
                flag = 6
                Coverage.statementStart(157)
                flag2 = 0
                Coverage.statementStart(158)
                if (t == tmp.size) {
                    Coverage.ifStart(159)
                    flag2 = 1
                    Coverage.statementStart(160)
                    offset += tmp.size
                    Coverage.statementStart(161)
                    t = idx - offset
                    Coverage.statementStart(162)
                    tmp = tmp.next!!
                    Coverage.statementStart(163)
                }
                Coverage.statementStart(164)
                if (tmp.size < capacity) {
                    Coverage.ifStart(165)
                    flag = 7
                    Coverage.statementStart(166)
                    for (i in tmp.size downTo t + 1) {
                        Coverage.forLoopStart(167)
                        tmp.data[i] = tmp.data[i - 1]
                        Coverage.statementStart(168)
                    }
                    Coverage.statementStart(169)
                    tmp.data[t] = value
                    Coverage.statementStart(170)
                    tmp.size++
                    Coverage.statementStart(171)
                } else {
                    Coverage.ifStart(172)
                    flag = 8
                    Coverage.statementStart(173)
                    var p = MyListDoublePage()
                    Coverage.statementStart(174)
                    pagecount++
                    Coverage.statementStart(175)
                    p.next = tmp.next
                    Coverage.statementStart(176)
                    tmp.next = p
                    Coverage.statementStart(177)
                    var j = 0
                    Coverage.statementStart(178)
                    for (i in t until capacity) {
                        Coverage.forLoopStart(179)
                        p.data[j] = tmp.data[i]
                        Coverage.statementStart(180)
                        j++
                        Coverage.statementStart(181)
                    }
                    Coverage.statementStart(182)
                    tmp.size = t + 1
                    Coverage.statementStart(183)
                    p.size = j
                    Coverage.statementStart(184)
                    tmp.data[t] = value
                    Coverage.statementStart(185)
                    if (lastpage == tmp) {
                        Coverage.ifStart(186)
                        lastpage = p
                        Coverage.statementStart(187)
                    }
                    Coverage.statementStart(188)
                }
                Coverage.statementStart(189)
            }
            Coverage.statementStart(190)
        }
        Coverage.statementStart(191)
        size++
        Coverage.statementStart(192)
        debug()
        Coverage.statementStart(193)
        shrinkToFit()
        Coverage.statementStart(194)
    }

    fun debug(): String {
        Coverage.funStart(195)
        var res = StringBuilder()
        Coverage.statementStart(196)
        var totalsize = 0
        Coverage.statementStart(197)
        res.append("Double debug $size [")
        Coverage.statementStart(198)
        var tmp = page
        Coverage.statementStart(199)
        while (true) {
            Coverage.whileLoopStart(200)
            totalsize += tmp.size
            Coverage.statementStart(201)
            res.append("" + tmp.size + "{")
            Coverage.statementStart(202)
            for (i in 0 until tmp.size) {
                Coverage.forLoopStart(203)
                res.append("" + tmp.data[i] + ", ")
                Coverage.statementStart(204)
            }
            Coverage.statementStart(205)
            res.append("}, ")
            Coverage.statementStart(206)
            if (tmp.next == null) {
                Coverage.ifStart(207)
                break
            }
            Coverage.statementStart(208)
            tmp = tmp.next!!
            Coverage.statementStart(209)
        }
        Coverage.statementStart(210)
        res.append("]")
        Coverage.statementStart(211)
        require(totalsize == size, { "size incorrect ${res.toString()} $flag $flag2 $flag3 $flag4" })
        Coverage.statementStart(212)
        require(tmp == lastpage, { "lastpage incorrect ${res.toString()}" })
        Coverage.statementStart(213)
        return res.toString()
    }

    inline operator fun iterator(): MyListDoubleIterator {
        Coverage.funStart(214)
        return MyListDoubleIterator(this)
    }

    class MyListDoubleIterator(@JvmField val data: MyListDouble) : Iterator<Double> {
        var tmp = data.page
        var globalidx = 0
        var idx = 0
        override fun hasNext() = idx < tmp.size || tmp.next != null
        override fun next(): Double {
            Coverage.funStart(215)
            if (idx == tmp.size) {
                Coverage.ifStart(216)
                globalidx += idx
                Coverage.statementStart(217)
                tmp = tmp.next!!
                Coverage.statementStart(218)
                idx = 0
                Coverage.statementStart(219)
            }
            Coverage.statementStart(220)
            val res = tmp.data[idx] as Double
            Coverage.statementStart(221)
            idx++
            Coverage.statementStart(222)
            return res
        }
    }

    class MyListDoubleSmall {
        @JvmField
        var size = 0
        @JvmField
        var capacity = 1
        @JvmField
        var data: DoubleArray

        inline fun reserve(capacity: Int) {
            Coverage.funStart(223)
            require(capacity <= MyListDouble.capacity, { "capacity too large" })
            Coverage.statementStart(224)
            if (this.capacity < capacity) {
                Coverage.ifStart(225)
                this.capacity = capacity
                Coverage.statementStart(226)
                val tmp = DoubleArray(capacity)
                Coverage.statementStart(227)
                data.copyInto(tmp)
                Coverage.statementStart(228)
                data = tmp
                Coverage.statementStart(229)
            }
            Coverage.statementStart(230)
        }

        constructor() {
            data = DoubleArray(capacity)
        }

        constructor(value: Double) {
            data = DoubleArray(capacity)
            data[size] = value
            size++
        }

        constructor(initialCapacity: Int, init: (Int) -> Double) {
            capacity = initialCapacity
            size = capacity
            data = DoubleArray(capacity) { init(it) }
        }

        fun clear() {
            Coverage.funStart(231)
            size = 0
            Coverage.statementStart(232)
        }

        fun add(value: Double) {
            Coverage.funStart(233)
            println("size capacity $size $capacity ${capacity * 2}")
            if (size >= capacity) {
                Coverage.ifStart(234)
                reserve(capacity * 2)
                Coverage.statementStart(235)
            }
            Coverage.statementStart(236)
            data[size] = value
            Coverage.statementStart(237)
            size++
            Coverage.statementStart(238)
        }

        inline operator fun get(idx: Int): Double {
            Coverage.funStart(239)
            return data.get(idx) as Double
        }

        inline operator fun set(idx: Int, value: Double) {
            println("idx size $idx $size")
            Coverage.funStart(240)
            require(idx <= size)
            Coverage.statementStart(241)
            if (idx == size) {
                Coverage.ifStart(242)
                add(value)
                Coverage.statementStart(243)
            } else {
                {
                    {
                        {
                            Coverage.ifStart(244)
                            data.set(idx, value)
                        }
                        Coverage.statementStart(245)
                    }
                    Coverage.statementStart(246)
                }
                Coverage.statementStart(247)
            }
            Coverage.statementStart(248)
        }

        fun remove(value: Double): Boolean {
            Coverage.funStart(249)
            for (idx in 0 until size) {
                Coverage.forLoopStart(250)
                if (data[idx] == value) {
                    Coverage.ifStart(251)
                    removeAt(idx)
                    Coverage.statementStart(252)
                    return true
                }
                Coverage.statementStart(253)
            }
            Coverage.statementStart(254)
            return false
        }

        fun removeAt(idx: Int): Double {
            Coverage.funStart(255)
            val res = data[idx]
            Coverage.statementStart(256)
            require(idx < size)
            Coverage.statementStart(257)
            for (i in idx until size) {
                Coverage.forLoopStart(258)
                data[i] = data[i + 1]
                Coverage.statementStart(259)
            }
            Coverage.statementStart(260)
            size--
            Coverage.statementStart(261)
            return res as Double
        }

        fun add(idx: Int, value: Double) {
            Coverage.funStart(262)
            if (size >= capacity) {
                Coverage.ifStart(263)
                reserve(capacity * 2)
                Coverage.statementStart(264)
            }
            Coverage.statementStart(265)
            if (idx < size) {
                Coverage.ifStart(266)
                size++
                Coverage.statementStart(267)
                for (i in size downTo idx + 1) {
                    Coverage.forLoopStart(268)
                    data[i] = data[i - 1]
                    Coverage.statementStart(269)
                }
                Coverage.statementStart(270)
                data[idx] = value
                Coverage.statementStart(271)
            } else {
                Coverage.ifStart(272)
                data[size] = value
                Coverage.statementStart(273)
                size++
                Coverage.statementStart(274)
            }
            Coverage.statementStart(275)
        }

        inline operator fun iterator(): MyListDoubleSmallIterator {
            Coverage.funStart(276)
            return MyListDoubleSmallIterator(this)
        }
    }

    class MyListDoubleSmallIterator(@JvmField val data: MyListDoubleSmall) : Iterator<Double> {
        var index = 0
        override fun hasNext(): Boolean {
            Coverage.funStart(277)
            return index < data.size
        }

        override fun next(): Double {
            Coverage.funStart(278)
            val res = data.data[index] as Double
            Coverage.statementStart(279)
            index++
            Coverage.statementStart(280)
            return res
        }
    }
}
