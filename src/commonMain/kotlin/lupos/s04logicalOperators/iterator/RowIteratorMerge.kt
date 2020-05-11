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
println("flag $flag")
            var res = -1
            when (flag) {
                1 -> {//call next on a, b is empty
                    res = a.next()
println("call A next 6")
                    if (res < 0) {
                        flag = 0
println("using none !1")
                    }
println("using A ?")
                }
                2 -> {//call next on b, a is empty
                    res = b.next()
println("call B next 5")
                    if (res < 0) {
                        flag = 0
println("using none !2")
                    }
println("using B ?")
                }
                4 -> {//call next on a, b is not empty
                    aIdx = a.next()
println("call A next 4")
                    if (aIdx < 0) {
                        buf = b.buf
                        res = bIdx
                        flag = 2
println("using B !3")
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
println("using A !4")
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
println("using B !5")
                        })
                    }
                }
                5 -> {//call next on b, a is not empty
                    bIdx = b.next()
println("call B next 3")
                    if (bIdx < 0) {
                        buf = a.buf
                        res = aIdx
                        flag = 1
println("using A !6")
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
println("using A !7")
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
println("using B !8")
                        })
                    }
                }
                3 -> {//call next on both
                    aIdx = a.next()
println("call A next 1")
                    bIdx = b.next()
println("call B next 2")
                    if (aIdx < 0 && bIdx < 0) {
                        res = -1
                        flag = 0
println("using none !9")
                    } else if (bIdx < 0) {
                        buf = a.buf
                        res = aIdx
                        flag = 1
println("using A !10")
                    } else if (aIdx < 0) {
                        buf = b.buf
                        res = bIdx
                        flag = 2
println("using B !11")
                    } else {
                        compare({
                            buf = a.buf
                            res = aIdx
                            flag = 4
println("using A !12")
                        }, {
                            buf = b.buf
                            res = bIdx
                            flag = 5
println("using B !13")
                        })
                    }
                }
            }
println("next flag $flag")
            /*return*/ res
        }
    }

    fun compare(actionA: () -> Unit, actionB: () -> Unit) {
        var i = 0
        while (i < compCount) {
            val cmp = comparator.compare(a.buf[aIdx + i], b.buf[bIdx + i])
println("cmp real $i $cmp ${a.buf[aIdx + i]} and ${b.buf[bIdx + i]}")
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
println("cmp fast $i $cmp")
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
