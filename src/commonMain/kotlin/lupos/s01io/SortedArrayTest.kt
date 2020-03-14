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
            mySortedArray.add(tmp)
            kotlinList.add(tmp)
            mySortedSet.add(tmp)
        } catch (e: Throwable) {
            break
        }
    }
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
    kotlinList.sort()
    i = 0
    mySortedArray.forEach {
        if (kotlinList[i] != it) {
            println(mySortedArray)
            println(kotlinList)
            require(false, { "sorted :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    require(i == kotlinList.size)
    i = 0
    for (it in mySortedArray.iterator(true)) {
        if (kotlinList[i] != it) {
            println(mySortedArray)
            println(kotlinList)
            require(false, { "sorted Iterator ASC :: ${kotlinList[i]} != $it" })
        }
        i++
    }
    require(i == kotlinList.size)
    i = kotlinList.size - 1
    for (it in mySortedArray.iterator(false)) {
        if (kotlinList[i] != it) {
            println(mySortedArray)
            println(kotlinList)
            require(false, { "sorted Iterator DESC :: ${kotlinList[i]} != $it" })
        }
        i--
    }
    require(i == -1)
//set verification
    val kotlinSet = kotlinList.distinct()
    i = 0
    mySortedSet.forEach {
        if (kotlinSet[i] != it) {
            println(mySortedSet)
            println(kotlinSet)
            require(false, { "set :: ${kotlinSet[i]} != $it" })
        }
        i++
    }
    require(i == kotlinSet.size)
    i = 0
    for (it in mySortedSet.iterator(true)) {
        if (kotlinSet[i] != it) {
            println(mySortedSet)
            println(kotlinSet)
            require(false, { "set Iterator ASC :: ${kotlinSet[i]} != $it" })
        }
        i++
    }
    require(i == kotlinSet.size)
    i = kotlinSet.size - 1
    for (it in mySortedSet.iterator(false)) {
        if (kotlinSet[i] != it) {
            println(mySortedSet)
            println(kotlinSet)
            require(false, { "set Iterator DESC :: ${kotlinSet[i]} != $it" })
        }
        i--
    }
    require(i == -1)
}
