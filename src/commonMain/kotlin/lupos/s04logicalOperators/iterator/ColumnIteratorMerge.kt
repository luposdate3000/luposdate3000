package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

object ColumnIteratorMerge {
    suspend operator fun invoke(a: ColumnIterator, comparator: Comparator<Value>): ColumnIterator {
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
        var done = false
        var resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == null) {
                    done = true
                    break
                } else {
                    buf1[i++] = next
                }
            }
            var total = i
            var off: Int
            var shift = 0
            var size = 1 shl shift
            var count: Int
            var mid: Int
            while (size / 2 < total) {
                off = 0
                shift++
                size = 1 shl shift
                while (off < total) {
                    if (off + size <= total) {
                        count = size
                    } else {
                        count = total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a_ = off
                    var b = aEnd
                    var c = a_
                    if (count < mid) {
                        b = a_
                        a_ = aEnd
                    }
                    loop@ while (a_ < aEnd && b < bEnd) {
                        if (comparator.compare(buf1[a_], buf1[b]) < 0) {
                            buf2[c++] = buf1[a_++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a_ < aEnd) {
                        buf2[c++] = buf1[a_++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                var t = buf1
                buf1 = buf2
                buf2 = t
            }
            var it = ColumnIteratorMultiValue(buf1, total)
            if (i > 0 || resultList.size == 0) {
                if (resultList.size == 0) {
                    resultList.add(it)
                } else if (resultList[0] == null) {
                    resultList[0] = it
                } else {
                    resultList[0] = ColumnIteratorMerge1(resultList[0]!!, it, comparator)
                    if (resultList[resultList.size - 1] != null) {
                        resultList.add(null)
                    }
                    var j = 1
                    while (j < resultList.size) {
                        if (resultList[j] == null) {
                            resultList[j] = resultList[j - 1]
                            resultList[j - 1] = null
                            break
                        } else {
                            resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
                            resultList[j - 1] = null
                        }
                        j++
                    }
                }
            }
            buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        }
        var j = 1
        while (j < resultList.size) {
            if (resultList[j] == null) {
                resultList[j] = resultList[j - 1]
            } else if (resultList[j - 1] != null) {
                resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
            }
            j++
        }
        SanityCheck.check { resultList.size > 0 }
        return resultList[resultList.size - 1]!!
    }

    suspend operator fun invoke(a: ColumnIterator): ColumnIterator {
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
        var done = false
        var resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == null) {
                    done = true
                    break
                } else {
                    buf1[i++] = next
                }
            }
            var total = i
            var off: Int
            var shift = 0
            var size = 1 shl shift
            var count: Int
            var mid: Int
            while (size / 2 < total) {
                off = 0
                shift++
                size = 1 shl shift
                while (off < total) {
                    if (off + size <= total) {
                        count = size
                    } else {
                        count = total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a_ = off
                    var b = aEnd
                    var c = a_
                    if (count < mid) {
                        b = a_
                        a_ = aEnd
                    }
                    loop@ while (a_ < aEnd && b < bEnd) {
                        if (buf1[a_] < buf1[b]) {
                            buf2[c++] = buf1[a_++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a_ < aEnd) {
                        buf2[c++] = buf1[a_++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                var t = buf1
                buf1 = buf2
                buf2 = t
            }
            var it = ColumnIteratorMultiValue(buf1, total)
            if (i > 0 || resultList.size == 0) {
                if (resultList.size == 0) {
                    resultList.add(it)
                } else if (resultList[0] == null) {
                    resultList[0] = it
                } else {
                    resultList[0] = ColumnIteratorMerge2(resultList[0]!!, it)
                    if (resultList[resultList.size - 1] != null) {
                        resultList.add(null)
                    }
                    var j = 1
                    while (j < resultList.size) {
                        if (resultList[j] == null) {
                            resultList[j] = resultList[j - 1]
                            resultList[j - 1] = null
                            break
                        } else {
                            resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
                            resultList[j - 1] = null
                        }
                        j++
                    }
                }
            }
            buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        }
        var j = 1
        while (j < resultList.size) {
            if (resultList[j] == null) {
                resultList[j] = resultList[j - 1]
            } else if (resultList[j - 1] != null) {
                resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
            }
            j++
        }
        SanityCheck.check { resultList.size > 0 }
        return resultList[resultList.size - 1]!!
    }
}

class ColumnIteratorMerge1(val a: ColumnIterator, val b: ColumnIterator, val comparator: Comparator<Value>) : ColumnIterator() {
    var flag = 3
    var aBuf: Value? = null
    var bBuf: Value? = null

    init {
        next = {
            var res: Value? = null
            when (flag) {
                1 -> {//call next on a, b is empty
                    res = a.next()
                    if (res == null) {
                        flag = 0
                    }
                }
                2 -> {//call next on b, a is empty
                    res = b.next()
                    if (res == null) {
                        flag = 0
                    }
                }
                4 -> {//call next on a, b is not empty
                    aBuf = a.next()
                    if (aBuf == null) {
                        res = bBuf
                        flag = 2
                    } else {
                        if (comparator.compare(aBuf, bBuf) < 0) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
                5 -> {//call next on b, a is not empty
                    bBuf = b.next()
                    if (bBuf == null) {
                        res = aBuf
                        flag = 1
                    } else {
                        if (comparator.compare(aBuf, bBuf) < 0) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
                3 -> {//call next on both
                    aBuf = a.next()
                    bBuf = b.next()
                    if (aBuf == null && bBuf == null) {
                        res = null
                        flag = 0
                    } else if (bBuf == null) {
                        res = aBuf
                        flag = 1
                    } else if (aBuf == null) {
                        res = bBuf
                        flag = 2
                    } else {
                        if (comparator.compare(aBuf, bBuf) < 0) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
            }
            /*return*/ res
        }
    }
}

class ColumnIteratorMerge2(val a: ColumnIterator, val b: ColumnIterator) : ColumnIterator() {
    var flag = 3
    var aBuf: Value? = null
    var bBuf: Value? = null

    init {
        next = {
            var res: Value? = null
            when (flag) {
                1 -> {//call next on a, b is empty
                    res = a.next()
                    if (res == null) {
                        flag = 0
                    }
                }
                2 -> {//call next on b, a is empty
                    res = b.next()
                    if (res == null) {
                        flag = 0
                    }
                }
                4 -> {//call next on a, b is not empty
                    aBuf = a.next()
                    if (aBuf == null) {
                        res = bBuf
                        flag = 2
                    } else {
                        if (aBuf!! < bBuf!!) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
                5 -> {//call next on b, a is not empty
                    bBuf = b.next()
                    if (bBuf == null) {
                        res = aBuf
                        flag = 1
                    } else {
                        if (aBuf!! < bBuf!!) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
                3 -> {//call next on both
                    aBuf = a.next()
                    bBuf = b.next()
                    if (aBuf == null && bBuf == null) {
                        flag = 0
                    } else if (bBuf == null) {
                        res = aBuf
                        flag = 1
                    } else if (aBuf == null) {
                        res = bBuf
                        flag = 2
                    } else {
                        if (aBuf!! < bBuf!!) {
                            res = aBuf
                            flag = 4
                        } else {
                            res = bBuf
                            flag = 5
                        }
                    }
                }
            }
            /*return*/ res
        }
    }
}
