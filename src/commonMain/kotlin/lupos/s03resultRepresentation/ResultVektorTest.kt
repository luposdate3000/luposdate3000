package lupos.s03resultRepresentation

import lupos.s00misc.*
import lupos.s00misc.Coverage

object ResultVektorTest {
    class MyComparatorValue : Comparator<Value> {
        override fun compare(a: Value, b: Value): Int {
            Coverage.funStart(245)
            if (a < b) {
                Coverage.ifStart(246)
                return -1
            }
            if (a == b) {
                Coverage.ifStart(247)
                throw Exception("dont compare equal values using comparator")
            }
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
        Coverage.funStart(248)
        try {
            val res = buffer.getNextInt() % max
            if (positiveOnly && res < 0) {
                Coverage.ifStart(249)
                return -res
            }
            return res
        } catch (e: Throwable) {
            throw NoMoreRandomException()
        }
    }

    class ResultVektorTestHelper {
        var vektor = ResultVektor(UNDEF_VALUE)
        var kotlinList = mutableListOf<Value>()
        var pos = 0
        var size = 0
        var backup = 0
    }

    fun log(s: String) {
        if (verbose) {
            println(s)
        }
    }

    operator fun invoke(buffer: DynamicByteArray) {
        Coverage.funStart(252)
        var expectException = false
        log("start")
        try {
            ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
            require(ResultVektor.capacity > 0)
            val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
            while (true) {
                Coverage.whileLoopStart(253)
                expectException = false
                val helperIdx = nextRandom(buffer, MAX_LISTS, true)
                val helper = helpers[helperIdx]
                log("helperIdx $helperIdx ${helper.vektor}")
                log(helper.kotlinList.toString())
                val func = nextRandom(buffer, FUNCTION_COUNT, true)
                log("func $func")
                when (func) {
                    0 -> {
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        log("count $count")
                        expectException = helper.pos + count > helper.size || helper.pos + count < 0
                        helper.vektor.skipPos(count)
                        helper.pos += count
                    }
                    1 -> {
                        var count = nextRandom(buffer, MAX_CAPACITY, false)
                        if (count < 0 && helper.pos > helper.size + count) {
                            Coverage.ifStart(254)
                            count = helper.pos - helper.size
                        }
                        log("count $count")
                        expectException = helper.size + count < 0 || !helper.vektor.canAppend()
                        helper.vektor.skipSize(count)
                        helper.size += count
                        if (count > 0) {
                            Coverage.ifStart(255)
                            for (i in 0 until count) {
                                Coverage.forLoopStart(256)
                                helper.kotlinList.add(DONT_CARE_VALUE)
                            }
                        } else {
                            Coverage.ifStart(257)
                            if (!expectException) {
                                Coverage.ifStart(258)
                                for (i in 0 until -count) {
                                    Coverage.forLoopStart(259)
                                    helper.kotlinList.removeAt(helper.kotlinList.size - 1)
                                }
                            }
                        }
                    }
                    2 -> {
                        helper.vektor.backupPosition()
                        helper.backup = helper.pos
                    }
                    3 -> {
                        helper.vektor.restorePosition()
                        helper.pos = helper.backup
                    }
                    4 -> {
                        expectException = helper.pos >= helper.size
                        val c = helper.vektor.current()
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                    }
                    5 -> {
                        expectException = helper.pos >= helper.size
                        val c = helper.vektor.next()
                        require(c == helper.kotlinList[helper.pos] || helper.kotlinList[helper.pos] == DONT_CARE_VALUE)
                        helper.pos++
                    }
                    6 -> {
                        log("${helper.pos} ${helper.size} ${(helper.pos < helper.size)} ${helper.vektor.hasNext()}")
                        require((helper.pos < helper.size) == helper.vektor.hasNext())
                    }
                    7 -> {
                        require(ResultVektor.capacity - helper.size - 1 <= helper.vektor.availableWrite())
                    }
                    8 -> {
                        require(helper.size - helper.pos == helper.vektor.availableRead(), { "${helper.size} ${helper.pos} ${helper.size - helper.pos} ${helper.vektor.availableRead()}" })
                    }
                    9 -> {
                        require(helper.vektor.canAppend() || helper.size >= ResultVektor.capacity)
                    }
                    10 -> {
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        log("count $count")
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        log("value $value")
                        expectException = count <= 0 || !helper.vektor.canAppend()
                        helper.vektor.append(value, count)
                        for (i in 0 until count) {
                            Coverage.forLoopStart(260)
                            helper.kotlinList.add(value)
                        }
                        helper.size += count
                    }
                    11 -> {
                        var same = 0
                        var lastsame = -1
                        var helperValue = DONT_CARE_VALUE
                        val tmp = helper.vektor.sameElements()
                        while (same != lastsame && same != tmp) {
                            Coverage.whileLoopStart(261)
                            if (helperValue == DONT_CARE_VALUE) {
                                Coverage.ifStart(262)
                                helperValue = helper.kotlinList[helper.pos]
                            }
                            while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same]) {
                                Coverage.whileLoopStart(263)
                                same++
                            }
                            if (same == tmp) {
                                Coverage.ifStart(264)
                                break
                            }
                            while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE) {
                                Coverage.whileLoopStart(265)
                                same++
                            }
                            log("same $same $tmp")
                        }
                        require(same == tmp)
                    }
                    12 -> {
                        val helperIdx2 = nextRandom(buffer, MAX_LISTS, true)
                        val helper2 = helpers[helperIdx2]
                        log("helperIdx2 $helperIdx2 ${helper2.vektor}")
                        log(helper2.kotlinList.toString())
                        val count = nextRandom(buffer, MAX_CAPACITY, false)
                        log("count $count")
                        expectException = helper.vektor.availableRead() < count || count <= 0 || helper2.vektor.availableWrite() < count
                        helper2.vektor.copy(helper.vektor, count)
                        expectException = helper.vektor.availableRead() < count || count <= 0
                        for (i in helper.pos until helper.pos + count) {
                            Coverage.forLoopStart(266)
                            helper2.kotlinList.add(helper.kotlinList[i])
                        }
                        helper2.size += count
                        helper.pos += count
                    }
                    13 -> {
                        val first = nextRandom(buffer, helper.size, true)
                        val lastTarget = first + nextRandom(buffer, helper.size - first, true)
                        var last = first
                        helper.vektor.skipPos(-helper.pos)
                        helper.pos = 0
                        if (helper.kotlinList[last] == DONT_CARE_VALUE) {
                            Coverage.ifStart(267)
                            helper.vektor.skipPos(last)
                            helper.kotlinList[last] = helper.vektor.current()
                            helper.vektor.skipPos(-last)
                        }
                        while (last < lastTarget) {
                            Coverage.whileLoopStart(268)
                            if (helper.kotlinList[last + 1] == DONT_CARE_VALUE) {
                                Coverage.ifStart(269)
                                helper.vektor.skipPos(last + 1)
                                helper.kotlinList[last + 1] = helper.vektor.current()
                                helper.vektor.skipPos(-last - 1)
                            }
                            val lastValue = helper.kotlinList[last]
                            val thisValue = helper.kotlinList[last + 1]
                            if (lastValue == thisValue || MyComparatorValue().compare(lastValue, thisValue) < 0) {
                                Coverage.ifStart(270)
                                break
                            }
                            last++
                        }
                        val count = nextRandom(buffer, MAX_CAPACITY, true)
                        val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                        log("first $first")
                        log("last $last")
                        log("value $value")
                        log("count $count")
                        val listA = mutableListOf<Value>()
                        val listB = mutableListOf<Value>()
                        val listC = mutableListOf<Value>()
                        for (i in 0 until first) {
                            Coverage.forLoopStart(271)
                            listA.add(helper.kotlinList[i])
                        }
                        for (i in 0 until count) {
                            Coverage.forLoopStart(272)
                            listB.add(value)
                        }
                        for (i in first until last) {
                            Coverage.forLoopStart(273)
                            listB.add(helper.kotlinList[i])
                        }
                        for (i in last until helper.kotlinList.size) {
                            Coverage.forLoopStart(274)
                            listC.add(helper.kotlinList[i])
                        }
                        log("inA $listA")
                        log("inB $listB")
                        listB.sort()
                        log("inB2 $listB")
                        log("inC $listC")
                        log("size " + listA.size)
                        expectException = helper.vektor.availableWrite() < 2 || count == 0
                        val ret = helper.vektor.insertSorted(value, first, last, MyComparatorValue(), count)
                        log("${helper.vektor}")
                        log("asize ${listA.size}")
                        log("bsize ${listB.size}")
                        log("csize ${listC.size}")
                        log("ret $ret")
                        require(ret.second >= count)
                        require((ret.first >= listA.size) || (listA[listA.size - 1] == value) || (listA[listA.size - 1] == DONT_CARE_VALUE), { "${ret.first} ${listA.size}" })
                        require((ret.first + ret.second <= listA.size + listB.size) || (listC[0] == value) || (listC[0] == DONT_CARE_VALUE), { "${ret.first + ret.second} ${listA.size + listB.size}" })
                        listA.addAll(listB)
                        listA.addAll(listC)
                        for (i in ret.first until ret.first + ret.second) {
                            Coverage.forLoopStart(275)
                            require(listA[i] == value || listA[i] == DONT_CARE_VALUE)
                        }
                        helper.kotlinList = listA
                        helper.size += count
                    }
                    else -> {
                        Coverage.ifStart(276)
                        require(func < FUNCTION_COUNT)
                    }
                }
                if (expectException) {
                    Coverage.ifStart(277)
                    throw Exception("there should be an exception")
                }
                log("" + expectException)
                log("helperIdx $helperIdx ${helper.vektor}")
                log(helper.kotlinList.toString())
                log("\n")
                for (helper in helpers) {
                    Coverage.forLoopStart(278)
                    helper.vektor.skipPos(-helper.pos)
                    for (i in 0 until helper.size) {
                        Coverage.forLoopStart(279)
                        val v = helper.vektor.next()
                        var l = i - 5
                        var r = i + 6
                        if (l < 0) {
                            Coverage.ifStart(280)
                            l = 0
                        }
                        if (r > helper.kotlinList.size) {
                            Coverage.ifStart(281)
                            r = helper.kotlinList.size
                        }
                        require(v == helper.kotlinList[i] || helper.kotlinList[i] == DONT_CARE_VALUE, { "$i -> $v != ${helper.kotlinList.subList(l, r)}" })
                    }
                    helper.vektor.skipPos(helper.pos - helper.size)
                    log(helper.vektor.toString())
                    require(helper.vektor.data[helper.vektor.sizeIndex].count > 0 || helper.vektor.sizeIndex == 0)
                }
                log("\n")
            }
        } catch (e: NoMoreRandomException) {
        } catch (e: Throwable) {
            if (!expectException) {
                Coverage.ifStart(282)
                throw e
            }
        }
    }
}
