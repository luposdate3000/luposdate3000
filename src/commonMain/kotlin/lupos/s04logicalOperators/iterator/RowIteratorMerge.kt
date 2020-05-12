package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

open class RowIteratorMerge(val a: RowIterator, val b: RowIterator, val comparator: Comparator<Value>, val compCount: Int) : RowIterator() {
    companion object {
        suspend operator fun invoke(a: RowIterator, comparator: Comparator<Value>, compCount: Int, columns: Array<String>): RowIterator {
            require(columns.size == a.columns.size)
            var buf1 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
            var buf2 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
            var done = false
            var resultList = mutableListOf<RowIterator?>()
            var columnMapping = IntArray(columns.size)
            for (i in 0 until columns.size) {
                for (j in 0 until columns.size) {
                    if (a.columns[j] == columns[i]) {
                        columnMapping[i] = j
                    }
                }
            }
            while (!done) {
                var i = 0
                while (i < buf1.size) {
                    val next = a.next()
                    if (next < 0) {
                        done = true
                        break
                    }
                    for (j in 0 until columns.size) {
                        buf1[i++] = a.buf[next + columnMapping[j]]
                    }
                }
                var total = i / columns.size
                var off = 0
                var shift = 0
                var size = 1 shl shift
                var count = 0
                var mid = 0
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
                        val aEnd = (off + mid) * columns.size
                        val bEnd = (off + count) * columns.size
                        var a = off * columns.size
                        var b = aEnd
                        var c = a
                        if (count < mid) {
                            b = a
                            a = aEnd
                        }
                        loop@ while (a < aEnd && b < bEnd) {
                            for (i in 0 until columns.size) {
                                var cmp = 0
                                var j = 0
                                while (j < compCount) {
                                    cmp = comparator.compare(buf1[a + i], buf1[b + i])
                                    if (cmp < 0) {
                                        for (j in 0 until columns.size) {
                                            buf2[c++] = buf1[a++]
                                        }
                                        continue@loop
                                    } else if (cmp > 0) {
                                        for (j in 0 until columns.size) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                                while (j < columns.size) {
                                    cmp = buf1[a + i] - buf1[b + i]
                                    if (cmp < 0) {
                                        for (j in 0 until columns.size) {
                                            buf2[c++] = buf1[a++]
                                        }
                                        continue@loop
                                    } else if (cmp > 0) {
                                        for (j in 0 until columns.size) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                            }
                            for (j in 0 until columns.size) {
                                buf2[c++] = buf1[a++]
                            }
                            for (j in 0 until columns.size) {
                                buf2[c++] = buf1[b++]
                            }
                        }
                        while (a < aEnd) {
                            buf2[c++] = buf1[a++]
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
                var it = RowIteratorBuf(buf1, columns, i)
                if (i > 0 || resultList.size == 0) {
                    if (resultList.size == 0) {
                        resultList.add(it)
                    } else if (resultList[0] == null) {
                        resultList[0] = it
                    } else {
                        resultList[0] = RowIteratorMerge(resultList[0]!!, it, comparator, compCount)
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
                                resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
                                resultList[j - 1] = null
                            }
                            j++
                        }
                    }
                }
                buf1 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
            }
            var j = 1
            while (j < resultList.size) {
                if (resultList[j] == null) {
                    resultList[j] = resultList[j - 1]
                } else if (resultList[j - 1] != null) {
                    resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
                }
                j++
            }
            require(resultList.size > 0)
            return resultList[resultList.size - 1]!!
        }
    }

    var flag = 3
    var aIdx = -1
    var bIdx = -1

    init {
        SanityCheck {
            require(a.columns.size == b.columns.size)
            for (i in 0 until a.columns.size) {
                require(a.columns[i] == b.columns[i])
            }
        }
        columns = a.columns
        next = {
            var res = -1
            when (flag) {
                1 -> {//call next on a, b is empty
                    res = a.next()
                    buf = a.buf
                    if (res < 0) {
                        flag = 0
                    }
                }
                2 -> {//call next on b, a is empty
                    res = b.next()
                    buf = b.buf
                    if (res < 0) {
                        flag = 0
                    }
                }
                4 -> {//call next on a, b is not empty
                    aIdx = a.next()
                    if (aIdx < 0) {
                        buf = b.buf
                        res = bIdx
                        flag = 2
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
                        })
                    }
                }
                5 -> {//call next on b, a is not empty
                    bIdx = b.next()
                    if (bIdx < 0) {
                        buf = a.buf
                        res = aIdx
                        flag = 1
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
                        })
                    }
                }
                3 -> {//call next on both
                    aIdx = a.next()
                    bIdx = b.next()
                    if (aIdx < 0 && bIdx < 0) {
                        res = -1
                        flag = 0
                    } else if (bIdx < 0) {
                        buf = a.buf
                        res = aIdx
                        flag = 1
                    } else if (aIdx < 0) {
                        buf = b.buf
                        res = bIdx
                        flag = 2
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
                        })
                    }
                }
            }
            /*return*/ res
        }
    }

    fun compare(actionA: () -> Unit, actionB: () -> Unit) {
        var i = 0
        while (i < compCount) {
            val cmp = comparator.compare(a.buf[aIdx + i], b.buf[bIdx + i])
            if (cmp < 0) {
                actionA()
                return
            } else if (cmp > 0) {
                actionB()
                return
            }
            i++
        }
        while (i < columns.size) {
            val cmp = a.buf[aIdx + i] - b.buf[bIdx + i]
            if (cmp < 0) {
                actionA()
                return
            } else if (cmp > 0) {
                actionB()
                return
            }
            i++
        }
        actionA()
    }
}
