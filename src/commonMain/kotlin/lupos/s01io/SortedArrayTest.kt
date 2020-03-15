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
    val mySortedArray = SortedArray<Int>(MyComparatorInt(), { size -> Array<Int>(size) { 0 } })
    val kotlinList = mutableListOf<Int>()
    val mySortedSet = SortedSet<Int>(MyComparatorInt(), { size -> Array<Int>(size) { 0 } })
    for (i in 0 until Int.MAX_VALUE) {
//    for(i in 0 until 20) {
        try {
            val tmp = buffer.getNextInt() % 100
            if (tmp % 10 == 0)
                tests(mySortedArray, kotlinList, mySortedSet)
            mySortedArray.add(tmp)
            kotlinList.add(tmp)
            mySortedSet.add(tmp)
        } catch (e: Throwable) {
            break
        }
    }
    tests(mySortedArray, kotlinList, mySortedSet)
}

fun tests(mySortedArray: SortedArray<Int>, kotlinList: MutableList<Int>, mySortedSet: SortedSet<Int>) {
    testsArray(mySortedArray, kotlinList)
    testsSet(mySortedSet, kotlinList)
}

fun testsBase(mySorted: SortedArrayBase<Int>, kotlinList: MutableList<Int>) {
    var i = 0
    kotlinList.sort()
    mySorted.forEach {
        if (kotlinList[i] != it) {
            println(mySorted)
            println(kotlinList)
            require(false, { "sorted :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    require(i == kotlinList.size)
    i = 0
    for (it in mySorted.iterator(true)) {
        if (kotlinList[i] != it) {
            println(mySorted)
            println(kotlinList)
            require(false, { "sorted Iterator ASC :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    require(i == kotlinList.size)
    i = kotlinList.size - 1
    for (it in mySorted.iterator(false)) {
        if (kotlinList[i] != it) {
            println(mySorted)
            println(kotlinList)
            require(false, { "sorted Iterator DESC :: ${kotlinList[i]} != $it" })
        }
        i--
    }
    require(i == -1)
    for (i in 0 until kotlinList.size) {
        val x = kotlinList.contains(kotlinList[i])
        val y = mySorted.get(kotlinList[i])
        require((x && y == kotlinList[i]) || (!x && y == null))
        val x1 = kotlinList.contains(kotlinList[i] + 1)
        val y1 = mySorted.get(kotlinList[i] + 1)
        require((x1 && y1 == kotlinList[i] + 1) || (!x1 && y1 == null))
    }
}

fun testsArray(mySortedArray: SortedArray<Int>, kotlinList: MutableList<Int>) {
//array verification
    var i = 0
    mySortedArray.forEachUnordered {
        if (kotlinList[i] != it) {
            println(mySortedArray)
            println(kotlinList)
            require(false, { "original :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    require(i == kotlinList.size)
    testsBase(mySortedArray, kotlinList)
}

fun testsSet(mySortedSet: SortedSet<Int>, kotlinList: MutableList<Int>) {
    val kotlinSet = kotlinList.distinct().toMutableList()
    testsBase(mySortedSet, kotlinSet)
}
