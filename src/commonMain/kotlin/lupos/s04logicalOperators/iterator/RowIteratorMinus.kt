package lupos.s04logicalOperators.iterator

import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast

open class RowIteratorMinus(val a: RowIterator, val b: RowIterator, projection: Array<String>) : RowIterator() {
    var flag = 2
    var aIdx = -1
    var bIdx = -1

    init {
        var compCount = 0
        var columnsA = mutableListOf<String>()
        var columnsB = mutableListOf<String>()
        for (i in 0 until a.columns.size) {
            for (j in 0 until b.columns.size) {
                if (a.columns[i] == b.columns[j]) {
                    columnsA.add(a.columns[i])
                    columnsB.add(a.columns[i])
                    compCount++
                }
            }
        }
        for (i in 0 until a.columns.size) {
            if (!columnsA.contains(a.columns[i])) {
                columnsA.add(a.columns[i])
            }
        }
        for (j in 0 until b.columns.size) {
            if (!columnsB.contains(b.columns[j])) {
                columnsB.add(b.columns[j])
            }
        }
        columns = projection
        val mapping = IntArray(projection.size)
        for (i in 0 until projection.size) {
            for (j in 0 until a.columns.size) {
                if (projection[i] == a.columns[j]) {
                    mapping[i] = j
                }
            }
        }
        buf = IntArray(mapping.size)
        runBlocking {
            val a1 = RowIteratorMerge(a, ValueComparatorFast(), compCount, columnsA.toTypedArray())
            val b1 = RowIteratorMerge(b, ValueComparatorFast(), compCount, columnsB.toTypedArray())
            bIdx = b1.next()
            if (bIdx < 0) {
                flag = 1
            }
            next = {
                var res = -1
                loop@ while (true) {
                    when (flag) {
                        0 -> {
                            break@loop
                        }
                        1 -> {//nothing to remove left
                            aIdx = a1.next()
                            if (aIdx < 0) {
                                flag = 0
                            } else {
                                res = 0
                                for (i in 0 until mapping.size) {
                                    buf[i] = a1.buf[mapping[i] + aIdx]
                                }
                            }
                            break@loop
                        }
                        2 -> {
                            aIdx = a1.next()
                            if (aIdx >= 0) {
                                for (i in 0 until compCount) {
                                    if (buf[i] < b1.buf[i]) {
                                        res = 0
                                        for (i in 0 until mapping.size) {
                                            buf[i] = a1.buf[mapping[i] + aIdx]
                                        }
                                        break@loop
                                    } else if (buf[i] > b1.buf[i]) {
                                        bIdx = b1.next()
                                        if (bIdx < 0) {
                                            flag = 1
                                        }
                                        continue@loop
                                    }
                                }
                            } else {
                                flag = 0
                                break@loop
                            }
                        }
                    }
                }
                /*return*/ res
            }
        }
    }
}
