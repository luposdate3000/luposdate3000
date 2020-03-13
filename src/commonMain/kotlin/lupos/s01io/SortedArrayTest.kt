package lupos.s01io

import lupos.s00misc.*


class MyComparatorInt : Comparator<Int> {
    override fun compare(a: Int, b: Int): Int {
        if (a < b)
            return -1
        if (a == b)
            return 0
        return 1
    }
}

fun sortedArrayTest(buffer: DynamicByteArray) {
    val myArray = SortedArray<Int>(MyComparatorInt(), { size -> Array<Int>(size) { 0 } })
    val kotlinList = mutableListOf<Int>()
    for (i in 0 until Int.MAX_VALUE) {
//    for(i in 0 until 20) {
        try {
            val tmp = buffer.getNextInt()
            myArray.add(tmp)
            kotlinList.add(tmp)
        } catch (e: Throwable) {
            break
        }
    }
    var i = 0

    myArray.forEachUnordered {
        if (kotlinList[i] != it) {
            println(myArray)
            println(kotlinList)
            require(false, { "original :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    kotlinList.sort()
    i = 0
    myArray.forEach {
        if (kotlinList[i] != it) {
            println(myArray)
            println(kotlinList)
            require(false, { "sorted :: ${kotlinList[i]} != $it" })
        }
        i++
    }
}
