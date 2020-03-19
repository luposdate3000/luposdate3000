package lupos.s03resultRepresentation

import lupos.s00misc.*


class MyComparatorValue : Comparator<Value> {
    override fun compare(a: Value, b: Value): Int {
        if (a < b)
            return -1
        if (a == b)
            return 0
        return 1
    }
}

val UNDEF_VALUE = Int.MAX_VALUE
val DONT_CARE_VALUE = -Int.MAX_VALUE
val MAX_DISTINCT_VALUES = 20
val MAX_CAPACITY = 100
val FUNCTION_COUNT = 13
val MAX_LISTS = 4
val verbose = false

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
    val vektor = ResultVektor(UNDEF_VALUE)
    val kotlinList = mutableListOf<Value>()
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
                    while (helper.pos + same < helper.size && (helper.kotlinList[helper.pos] == helper.kotlinList[helper.pos + same] || helper.kotlinList[helper.pos + same] == DONT_CARE_VALUE))
                        same++
                    val tmp = helper.vektor.sameElements()
                    log("same $same $tmp")
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


/*

    fun insertSorted(value: Value, first: Int = posIndex, last: Int = sizeIndex + 1, comparator: Comparator<Value>, count: Int): Int {

*/
