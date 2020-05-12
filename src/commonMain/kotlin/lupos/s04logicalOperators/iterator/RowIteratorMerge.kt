package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value

open class RowIteratorMerge(val a: RowIterator, val b: RowIterator, val comparator: Comparator<Value>, val compCount: Int) : RowIterator() {
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
