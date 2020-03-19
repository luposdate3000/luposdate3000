package lupos.s03resultRepresentation

import lupos.s00misc.*


class MyComparatorValue : Comparator<Value> {
    override fun compare(a: Value, b: Value): Int {
        if (a < b)
            return -1
        if (a == b)
            throw Exception("dont compare equal values using comparator")
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
    try {
        val res = buffer.getNextInt() % max
        if (positiveOnly && res < 0)
            return -res
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
    if (verbose)
        println(s)
}

fun ResultVektorTest(buffer: DynamicByteArray) {
    var expectException = false
    log("start")
    try {
        ResultVektor.capacity = nextRandom(buffer, MAX_CAPACITY - 2, true) + 2
        require(ResultVektor.capacity > 0)
        val helpers = Array(MAX_LISTS) { ResultVektorTestHelper() }
        while (true) {
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
                    val count = nextRandom(buffer, MAX_CAPACITY, false)
                    log("count $count")
                    expectException = helper.size + count < 0 || !helper.vektor.canAppend()
                    helper.vektor.skipSize(count)
                    helper.size += count
                    if (count > 0) {
                        for (i in 0 until count)
                            helper.kotlinList.add(DONT_CARE_VALUE)
                    } else {
                        expectException = helper.size + count < 0
                        if (!expectException)
                            for (i in 0 until -count)
                                helper.kotlinList.removeAt(helper.kotlinList.size - 1)
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
                    require(helper.size - helper.pos == helper.vektor.availableRead())
                }
                9 -> {
                    require(helper.size >= ResultVektor.capacity || helper.vektor.canAppend())
                }
                10 -> {
                    val count = nextRandom(buffer, MAX_CAPACITY, false)
                    log("count $count")
                    val value = nextRandom(buffer, MAX_DISTINCT_VALUES, false)
                    log("value $value")
                    expectException = count <= 0 || !helper.vektor.canAppend()
                    helper.vektor.append(value, count)
                    for (i in 0 until count)
                        helper.kotlinList.add(value)
                    helper.size += count
                }
                11 -> {
                    var same = 0
                    var lastsame = -1
                    var helperValue = DONT_CARE_VALUE
                    val tmp = helper.vektor.sameElements()
                    while (same != lastsame && same != tmp) {
                        if (helperValue == DONT_CARE_VALUE)
                            helperValue = helper.kotlinList[helper.pos]
                        while (helper.pos + same < helper.size && helperValue == helper.kotlinList[helper.pos + same])
                            same++
                        if (same == tmp)
                            break
                        while (helper.pos + same < helper.size && helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE)
                            same++
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
                    expectException = helper.vektor.availableRead() < count || count < 0
                    helper2.vektor.copy(helper.vektor, count)
                    for (i in helper.pos until helper.pos + count)
                        helper2.kotlinList.add(helper.kotlinList[i])
                    helper2.size += count
                    helper.pos += count
                }
                13 -> {
                    if (helper.size > 0) {
                        val first = nextRandom(buffer, helper.size, true)
                        val last = first + nextRandom(buffer, helper.size - first, true)
                        log("first $first")
                        log("last $last")
                        for (i in first until last) {
                            if (helper.kotlinList[i] == DONT_CARE_VALUE) {
                                helper.vektor.skipPos(i - helper.pos)
                                helper.pos = i
                                val v = helper.vektor.current()
                                helper.kotlinList[i] = v
                            }
                        }
                        val listA = mutableListOf<Value>()
                        val listB = mutableListOf<Value>()
                        val listC = mutableListOf<Value>()
                        for (i in 0 until first)
                            listA.add(helper.kotlinList[i])
                        for (i in first until last)
                            listB.add(helper.kotlinList[i])
                        for (i in last until helper.kotlinList.size)
                            listC.add(helper.kotlinList[i])
                        listB.sort()
                        listA.addAll(listB)
                        listA.addAll(listC)
                        val newVektor = ResultVektor(UNDEF_VALUE)
                        helper.vektor.skipPos(-helper.pos)
                        newVektor.copy(helper.vektor, first)
                        helper.vektor.skipPos(last - first)
                        newVektor.copy(helper.vektor, helper.size - last)
                        var idx = first
                        log("helpera ${newVektor}")
                        while (idx < last) {
                            val value = helper.kotlinList[idx]
                            var count = 0
                            while (idx + count < last && helper.kotlinList[idx + count] == value)
                                count++
                            log("insert $first $idx $count $value")
                            newVektor.insertSorted(value, first, idx, MyComparatorValue(), count)
                            log("helperb ${newVektor}")
                            idx += count
                        }
                        newVektor.skipPos(helper.size - helper.pos)
                        helper.vektor = newVektor
                        helper.kotlinList = listA
                    }
                }
                else -> {
                    require(func < FUNCTION_COUNT)
                }
            }
            if (expectException)
                throw Exception("there should be an exception")
            log("" + expectException)
            log("helperIdx $helperIdx ${helper.vektor}")
            log(helper.kotlinList.toString())
            log("\n")
        }
    } catch (e: NoMoreRandomException) {
    } catch (e: Throwable) {
        if (!expectException)
            throw e
    }
}
