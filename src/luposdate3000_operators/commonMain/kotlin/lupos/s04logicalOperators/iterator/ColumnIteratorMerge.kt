package lupos.s04logicalOperators.iterator

import kotlin.jvm.JvmField
import lupos.s00misc.MERGE_SORT_MIN_ROWS
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt

object ColumnIteratorMerge {
    /*suspend*/ operator fun invoke(a: ColumnIterator, comparator: Comparator<Int>): ColumnIterator {
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
        var done = false
        val resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == ResultSetDictionaryExt.nullValue) {
                    done = true
                    a.close()
                    break
                } else {
                    buf1[i++] = next
                }
            }
            val total = i
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
                    count = if (off + size <= total) {
                        size
                    } else {
                        total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a2 = off
                    var b = aEnd
                    var c = a2
                    if (count < mid) {
                        b = a2
                        a2 = aEnd
                    }
                    loop@ while (a2 < aEnd && b < bEnd) {
                        if (comparator.compare(buf1[a2], buf1[b]) < 0) {
                            buf2[c++] = buf1[a2++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a2 < aEnd) {
                        buf2[c++] = buf1[a2++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                val t = buf1
                buf1 = buf2
                buf2 = t
            }
            val it = ColumnIteratorMultiValue(buf1, total)
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

    /*suspend*/ operator fun invoke(a: ColumnIterator): ColumnIterator {
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
        var done = false
        val resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == ResultSetDictionaryExt.nullValue) {
                    done = true
                    a.close()
                    break
                } else {
                    buf1[i++] = next
                }
            }
            val total = i
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
                    count = if (off + size <= total) {
                        size
                    } else {
                        total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a2 = off
                    var b = aEnd
                    var c = a2
                    if (count < mid) {
                        b = a2
                        a2 = aEnd
                    }
                    loop@ while (a2 < aEnd && b < bEnd) {
                        if (buf1[a2] < buf1[b]) {
                            buf2[c++] = buf1[a2++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a2 < aEnd) {
                        buf2[c++] = buf1[a2++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                val t = buf1
                buf1 = buf2
                buf2 = t
            }
            val it = ColumnIteratorMultiValue(buf1, total)
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

class ColumnIteratorMerge1(@JvmField val a: ColumnIterator, @JvmField val b: ColumnIterator, @JvmField val comparator: Comparator<Int>) : ColumnIterator() {
    @JvmField
    var label = 3

    @JvmField
    var aBuf: Int = ResultSetDictionaryExt.nullValue

    @JvmField
    var bBuf: Int = ResultSetDictionaryExt.nullValue
    override /*suspend*/ fun close() {
        if (label != 0) {
            label = 0
            a.close()
            b.close()
        }
    }

    override /*suspend*/ fun next(): Int {
        var res: Int = ResultSetDictionaryExt.nullValue
        when (label) {
            1 -> {//call next on a, b is empty
                res = a.next()
                if (res == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    label = 0
                }
            }
            2 -> {//call next on b, a is empty
                res = b.next()
                if (res == ResultSetDictionaryExt.nullValue) {
                    b.close()
                    label = 0
                }
            }
            4 -> {//call next on a, b is not empty
                aBuf = a.next()
                if (aBuf == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    res = bBuf
                    label = 2
                } else {
                    if (comparator.compare(aBuf, bBuf) < 0) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
            5 -> {//call next on b, a is not empty
                bBuf = b.next()
                if (bBuf == ResultSetDictionaryExt.nullValue) {
                    b.close()
                    res = aBuf
                    label = 1
                } else {
                    if (comparator.compare(aBuf, bBuf) < 0) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
            3 -> {//call next on both
                aBuf = a.next()
                bBuf = b.next()
                if (aBuf == ResultSetDictionaryExt.nullValue && bBuf == ResultSetDictionaryExt.nullValue) {
                    res = ResultSetDictionaryExt.nullValue
                    a.close()
                    b.close()
                    label = 0
                } else if (bBuf == ResultSetDictionaryExt.nullValue) {
                    res = aBuf
                    b.close()
                    label = 1
                } else if (aBuf == ResultSetDictionaryExt.nullValue) {
                    res = bBuf
                    a.close()
                    label = 2
                } else {
                    if (comparator.compare(aBuf, bBuf) < 0) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
        }
        return res
    }
}

class ColumnIteratorMerge2(@JvmField val a: ColumnIterator, @JvmField val b: ColumnIterator) : ColumnIterator() {
    @JvmField
    var label = 3

    @JvmField
    var aBuf: Int = ResultSetDictionaryExt.nullValue

    @JvmField
    var bBuf: Int = ResultSetDictionaryExt.nullValue
    override /*suspend*/ fun close() {
        if (label != 0) {
            label = 0
            a.close()
            b.close()
        }
    }

    override /*suspend*/ fun next(): Int {
        var res: Int = ResultSetDictionaryExt.nullValue
        when (label) {
            1 -> {//call next on a, b is empty
                res = a.next()
                if (res == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    label = 0
                }
            }
            2 -> {//call next on b, a is empty
                res = b.next()
                if (res == ResultSetDictionaryExt.nullValue) {
                    b.close()
                    label = 0
                }
            }
            4 -> {//call next on a, b is not empty
                aBuf = a.next()
                if (aBuf == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    res = bBuf
                    label = 2
                } else {
                    if (aBuf < bBuf) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
            5 -> {//call next on b, a is not empty
                bBuf = b.next()
                if (bBuf == ResultSetDictionaryExt.nullValue) {
                    b.close()
                    res = aBuf
                    label = 1
                } else {
                    if (aBuf < bBuf) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
            3 -> {//call next on both
                aBuf = a.next()
                bBuf = b.next()
                if (aBuf == ResultSetDictionaryExt.nullValue && bBuf == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    b.close()
                    label = 0
                } else if (bBuf == ResultSetDictionaryExt.nullValue) {
                    b.close()
                    res = aBuf
                    label = 1
                } else if (aBuf == ResultSetDictionaryExt.nullValue) {
                    a.close()
                    res = bBuf
                    label = 2
                } else {
                    if (aBuf < bBuf) {
                        res = aBuf
                        label = 4
                    } else {
                        res = bBuf
                        label = 5
                    }
                }
            }
        }
        return res
    }
}
