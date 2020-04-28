/* this File is autogenerated by generate-buildfile.kts */
/* DO NOT MODIFY DIRECTLY */
package lupos.s00misc
import lupos.s00misc.Coverage
/* Substitutions :: Generic,<Generic>,<Generic>,Array<Any?>,{null} */
class MyListGeneric<Generic> {
    companion object {
        val capacity = 5
    }
    class MyListGenericPage<Generic>() {
        var next: MyListGenericPage<Generic>? = null
        var size = 0/*local*/
        val data = MyListGenericSmall<Generic>()
    }
    var pagecount = 1
    @JvmField
    var size = 0
    @JvmField
    var page = MyListGenericPage<Generic>()
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
                val b = MyListGenericPage<Generic>()
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
                        t.data[j++] = it.next()
Coverage.statementStart(11)
                    }
Coverage.statementStart(12)
                    t.size = j
Coverage.statementStart(13)
                    if (it.hasNext()) {
Coverage.ifStart(14)
                        t.next = MyListGenericPage<Generic>()
Coverage.statementStart(15)
                        t = t.next!!
Coverage.statementStart(16)
                        c++
Coverage.statementStart(17)
                    }
Coverage.statementStart(18)
                }
Coverage.statementStart(19)
                pagecount = c
Coverage.statementStart(20)
                page = b
Coverage.statementStart(21)
                lastpage = t
Coverage.statementStart(22)
            }
Coverage.statementStart(23)
        }
Coverage.statementStart(24)
    }
    inline fun reserve(capacity: Int) {
Coverage.funStart(25)
    }
    constructor() {
    }
    constructor(value: Generic) {
        size = 1
        page.size = 1
        page.data[0] = value
    }
    constructor(initialCapacity: Int, init: (Int) -> Generic) {
        size = initialCapacity
        var i = 0
        var tmp = page
        while (i < size) {
Coverage.whileLoopStart(26)
            var j = tmp.size
            while (tmp.size < capacity && i < size) {
Coverage.whileLoopStart(27)
                tmp.data[j++] = init(i++)
            }
            tmp.size = j
            if (i < size) {
Coverage.ifStart(28)
                val p = MyListGenericPage<Generic>()
                pagecount++
                p.next = tmp.next
                tmp.next = p
                tmp = p
            }
        }
        lastpage = tmp
    }
    fun clear() {
Coverage.funStart(29)
        size = 0
Coverage.statementStart(30)
        page = MyListGenericPage<Generic>()
Coverage.statementStart(31)
        pagecount = 1
Coverage.statementStart(32)
        lastpage = page
Coverage.statementStart(33)
    }
    fun add(value: Generic) {
Coverage.funStart(34)
        if (lastpage.size < capacity) {
Coverage.ifStart(35)
            lastpage.data[lastpage.size++] = value
Coverage.statementStart(36)
        } else {
Coverage.ifStart(37)
            lastpage.next = MyListGenericPage<Generic>()
Coverage.statementStart(38)
            pagecount++
Coverage.statementStart(39)
            lastpage = lastpage.next!!
Coverage.statementStart(40)
            lastpage.data[lastpage.size++] = value
Coverage.statementStart(41)
        }
Coverage.statementStart(42)
        size++
Coverage.statementStart(43)
        shrinkToFit()
Coverage.statementStart(44)
    }
    inline operator fun get(idx: Int): Generic {
Coverage.funStart(45)
        require(idx < size)
Coverage.statementStart(46)
        var tmp = page
Coverage.statementStart(47)
        var offset = 0
Coverage.statementStart(48)
        while (offset + tmp.size <= idx) {
Coverage.whileLoopStart(49)
            offset += tmp.size
Coverage.statementStart(50)
            require(tmp.next != null) { debug() + " $offset $idx" }
Coverage.statementStart(51)
            tmp = tmp.next!!
Coverage.statementStart(52)
        }
Coverage.statementStart(53)
        return tmp.data[idx - offset] as Generic
    }
    fun remove(value: Generic): Boolean {
Coverage.funStart(54)
        var i = 0
Coverage.statementStart(55)
        var tmp: MyListGenericPage<Generic>? = page
Coverage.statementStart(56)
        while (i < size) {
Coverage.whileLoopStart(57)
            var j = 0
Coverage.statementStart(58)
            while (j < tmp!!.size) {
Coverage.whileLoopStart(59)
                if (tmp!!.data[j] == value) {
Coverage.ifStart(60)
                    while (j < tmp!!.size - 1) {
Coverage.whileLoopStart(61)
                        tmp.data[j] = tmp.data[j + 1]
Coverage.statementStart(62)
                        j++
Coverage.statementStart(63)
                    }
Coverage.statementStart(64)
                    tmp.size--
Coverage.statementStart(65)
                    size--
Coverage.statementStart(66)
                    return true
                }
Coverage.statementStart(67)
                j++
Coverage.statementStart(68)
                i++
Coverage.statementStart(69)
            }
Coverage.statementStart(70)
            tmp = tmp.next
Coverage.statementStart(71)
        }
Coverage.statementStart(72)
        return false
    }
    fun removeAt(idx: Int): Generic {
Coverage.funStart(73)
        var tmp = page
Coverage.statementStart(74)
        var offset = 0
Coverage.statementStart(75)
        while (offset + tmp.size < idx) {
Coverage.whileLoopStart(76)
            offset += tmp.size
Coverage.statementStart(77)
            tmp = tmp.next!!
Coverage.statementStart(78)
        }
Coverage.statementStart(79)
        var i = idx - offset
Coverage.statementStart(80)
        var res = tmp.data[i] as Generic
Coverage.statementStart(81)
        while (i < tmp.size - 1) {
Coverage.whileLoopStart(82)
            tmp.data[i] = tmp.data[i + 1]
Coverage.statementStart(83)
            i++
Coverage.statementStart(84)
        }
Coverage.statementStart(85)
        tmp.size--
Coverage.statementStart(86)
        size--
Coverage.statementStart(87)
        return res
    }
    inline operator fun set(idx: Int, value: Generic) {
Coverage.funStart(88)
        require(idx <= size)
Coverage.statementStart(89)
        if (idx == size) {
Coverage.ifStart(90)
            if (lastpage.size < capacity) {
Coverage.ifStart(91)
                lastpage.data[lastpage.size] = value
Coverage.statementStart(92)
                lastpage.size++
Coverage.statementStart(93)
            } else {
Coverage.ifStart(94)
                lastpage.next = MyListGenericPage<Generic>()
Coverage.statementStart(95)
                pagecount++
Coverage.statementStart(96)
                lastpage = lastpage.next!!
Coverage.statementStart(97)
                lastpage.size = 1
Coverage.statementStart(98)
                lastpage.data[0] = value
Coverage.statementStart(99)
            }
Coverage.statementStart(100)
            size++
Coverage.statementStart(101)
        } else {
Coverage.ifStart(102)
            var tmp = page
Coverage.statementStart(103)
            var offset = 0
Coverage.statementStart(104)
            var t = idx
Coverage.statementStart(105)
            while (t >= tmp.size) {
Coverage.whileLoopStart(106)
                offset += tmp.size
Coverage.statementStart(107)
                t = idx - offset
Coverage.statementStart(108)
                tmp = tmp.next!!
Coverage.statementStart(109)
            }
Coverage.statementStart(110)
            tmp.data[t] = value
Coverage.statementStart(111)
        }
Coverage.statementStart(112)
        shrinkToFit()
Coverage.statementStart(113)
    }
    fun add(idx: Int, value: Generic) {
Coverage.funStart(114)
        require(idx <= size)
Coverage.statementStart(115)
        if (idx == size) {
Coverage.ifStart(116)
            if (lastpage.size < capacity) {
Coverage.ifStart(117)
                lastpage.data[lastpage.size++] = value
Coverage.statementStart(118)
            } else {
Coverage.ifStart(119)
                lastpage.next = MyListGenericPage<Generic>()
Coverage.statementStart(120)
                pagecount++
Coverage.statementStart(121)
                lastpage = lastpage.next!!
Coverage.statementStart(122)
                lastpage.data[lastpage.size++] = value
Coverage.statementStart(123)
            }
Coverage.statementStart(124)
        } else {
Coverage.ifStart(125)
            var tmp = page
Coverage.statementStart(126)
            var offset = 0
Coverage.statementStart(127)
            var t = idx
Coverage.statementStart(128)
            while (t > tmp.size) {
Coverage.whileLoopStart(129)
                offset += tmp.size
Coverage.statementStart(130)
                t = idx - offset
Coverage.statementStart(131)
                tmp = tmp.next!!
Coverage.statementStart(132)
            }
Coverage.statementStart(133)
            if (t == tmp.size && tmp.size < capacity) {
Coverage.ifStart(134)
                tmp.data[t] = value
Coverage.statementStart(135)
                tmp.size++
Coverage.statementStart(136)
            } else {
Coverage.ifStart(137)
                if (t == tmp.size) {
Coverage.ifStart(138)
                    offset += tmp.size
Coverage.statementStart(139)
                    t = idx - offset
Coverage.statementStart(140)
                    tmp = tmp.next!!
Coverage.statementStart(141)
                }
Coverage.statementStart(142)
                if (tmp.size < capacity) {
Coverage.ifStart(143)
                    for (i in tmp.size downTo t + 1) {
Coverage.forLoopStart(144)
                        tmp.data[i] = tmp.data[i - 1]
Coverage.statementStart(145)
                    }
Coverage.statementStart(146)
                    tmp.data[t] = value
Coverage.statementStart(147)
                    tmp.size++
Coverage.statementStart(148)
                } else {
Coverage.ifStart(149)
                    var p = MyListGenericPage<Generic>()
Coverage.statementStart(150)
                    pagecount++
Coverage.statementStart(151)
                    p.next = tmp.next
Coverage.statementStart(152)
                    tmp.next = p
Coverage.statementStart(153)
                    var j = 0
Coverage.statementStart(154)
                    for (i in t until capacity) {
Coverage.forLoopStart(155)
                        p.data[j++] = tmp.data[i]
Coverage.statementStart(156)
                    }
Coverage.statementStart(157)
                    tmp.size = t + 1
Coverage.statementStart(158)
                    p.size = j
Coverage.statementStart(159)
                    tmp.data[t] = value
Coverage.statementStart(160)
                    if (lastpage == tmp) {
Coverage.ifStart(161)
                        lastpage = p
Coverage.statementStart(162)
                    }
Coverage.statementStart(163)
                }
Coverage.statementStart(164)
            }
Coverage.statementStart(165)
        }
Coverage.statementStart(166)
        size++
Coverage.statementStart(167)
        shrinkToFit()
Coverage.statementStart(168)
    }
    fun debug(): String {
Coverage.funStart(169)
        var res = StringBuilder()
Coverage.statementStart(170)
        var totalsize = 0
Coverage.statementStart(171)
        res.append("Generic<Generic> debug $size [")
Coverage.statementStart(172)
        var tmp = page
Coverage.statementStart(173)
        while (true) {
Coverage.whileLoopStart(174)
            totalsize += tmp.size
Coverage.statementStart(175)
            res.append("" + tmp.size + "{")
Coverage.statementStart(176)
            for (i in 0 until tmp.size) {
Coverage.forLoopStart(177)
                res.append("" + tmp.data[i] + ", ")
Coverage.statementStart(178)
            }
Coverage.statementStart(179)
            res.append("}, ")
Coverage.statementStart(180)
            if (tmp.next == null) {
Coverage.ifStart(181)
                break
            }
Coverage.statementStart(182)
            tmp = tmp.next!!
Coverage.statementStart(183)
        }
Coverage.statementStart(184)
        res.append("]")
Coverage.statementStart(185)
        require(totalsize == size, { "size incorrect ${res.toString()}" })
Coverage.statementStart(186)
        require(tmp == lastpage, { "lastpage incorrect ${res.toString()}" })
Coverage.statementStart(187)
        return res.toString()
    }
    inline operator fun iterator(): MyListGenericIterator<Generic> {
Coverage.funStart(188)
        return MyListGenericIterator(this)
    }
    class MyListGenericIterator<Generic>(@JvmField val data: MyListGeneric<Generic>) : Iterator<Generic> {
        var tmp = data.page
        var globalidx = 0
        var idx = 0
        override fun hasNext() = idx < tmp.size || tmp.next != null
        override fun next(): Generic {
Coverage.funStart(189)
            if (idx == tmp.size) {
Coverage.ifStart(190)
                globalidx += idx
Coverage.statementStart(191)
                tmp = tmp.next!!
Coverage.statementStart(192)
                idx = 0
Coverage.statementStart(193)
            }
Coverage.statementStart(194)
            return tmp.data[idx++] as Generic
        }
    }
    class MyListGenericSmall<Generic> {
        @JvmField
        var size = 0
        @JvmField
        var capacity = 1
        @JvmField
        var data: Array<Any?>
        inline fun reserve(capacity: Int) {
Coverage.funStart(195)
            if (this.capacity < capacity) {
Coverage.ifStart(196)
                this.capacity = capacity
Coverage.statementStart(197)
                val tmp = Array<Any?>(capacity) {null}
Coverage.statementStart(198)
                        data.copyInto(tmp)
Coverage.statementStart(199)
                data = tmp
Coverage.statementStart(200)
            }
Coverage.statementStart(201)
        }
        constructor() {
            data = Array<Any?>(capacity) {null}
        }
        constructor(value: Generic) {
            data = Array<Any?>(capacity) {null}
                    data[size++] = value
        }
        constructor(initialCapacity: Int, init: (Int) -> Generic) {
            capacity = initialCapacity
            size = capacity
            data = Array<Any?>(capacity) { init(it) }
        }
        fun clear() {
Coverage.funStart(202)
            size = 0
Coverage.statementStart(203)
        }
        fun add(value: Generic) {
Coverage.funStart(204)
            if (size + 1 >= capacity) {
Coverage.ifStart(205)
                reserve(capacity * 2)
Coverage.statementStart(206)
            }
Coverage.statementStart(207)
            data[size++] = value
Coverage.statementStart(208)
        }
        inline operator fun get(idx: Int): Generic {
Coverage.funStart(209)
            return data.get(idx) as Generic
        }
        inline operator fun set(idx: Int, value: Generic) {
require(idx<=size)
Coverage.funStart(210)
if(idx==size){
add(value)
}else{
            data.set(idx, value)
}
Coverage.statementStart(211)
        }
        fun remove(value: Generic): Boolean {
Coverage.funStart(212)
            for (idx in 0 until size) {
Coverage.forLoopStart(213)
                if (data[idx] == value) {
Coverage.ifStart(214)
                    removeAt(idx)
Coverage.statementStart(215)
                    return true
                }
Coverage.statementStart(216)
            }
Coverage.statementStart(217)
            return false
        }
        fun removeAt(idx: Int): Generic {
Coverage.funStart(218)
            val res = data[idx]
Coverage.statementStart(219)
            require(idx < size)
Coverage.statementStart(220)
            for (i in idx until size) {
Coverage.forLoopStart(221)
                data[i] = data[i + 1]
Coverage.statementStart(222)
            }
Coverage.statementStart(223)
            size--
Coverage.statementStart(224)
            return res as Generic
        }
        fun add(idx: Int, value: Generic) {
Coverage.funStart(225)
            if (size + 1 >= capacity) {
Coverage.ifStart(226)
                reserve(capacity * 2)
Coverage.statementStart(227)
            }
Coverage.statementStart(228)
            if (idx < size) {
Coverage.ifStart(229)
                size++
Coverage.statementStart(230)
                for (i in size downTo idx + 1) {
Coverage.forLoopStart(231)
                    data[i] = data[i - 1]
Coverage.statementStart(232)
                }
Coverage.statementStart(233)
                data[idx] = value
Coverage.statementStart(234)
            } else {
Coverage.ifStart(235)
                data[size++] = value
Coverage.statementStart(236)
            }
Coverage.statementStart(237)
        }
        inline operator fun iterator(): MyListGenericSmallIterator<Generic> {
Coverage.funStart(238)
            return MyListGenericSmallIterator(this)
        }
        class MyListGenericSmallIterator<Generic>(@JvmField val data: MyListGenericSmall<Generic>) : Iterator<Generic> {
            var index = 0
            override fun hasNext(): Boolean {
Coverage.funStart(239)
                return index < data.size
            }
            override fun next(): Generic {
Coverage.funStart(240)
                return data.data[index++] as Generic
            }
        }
    }
}
