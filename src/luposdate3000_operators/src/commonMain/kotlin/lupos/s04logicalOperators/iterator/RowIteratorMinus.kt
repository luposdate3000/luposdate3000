package lupos.s04logicalOperators.iterator
import lupos.s00misc.Parallel
open class RowIteratorMinus(val a: RowIterator, val b: RowIterator, private val projection: Array<String>) : RowIterator() {
    var flag: Int = 2
    private var aIdx = -1
    private var bIdx = -1
    /*suspend*/ fun _init() {
        var compCount = 0
        val columnsA = mutableListOf<String>()
        val columnsB = mutableListOf<String>()
        for (i in a.columns.indices) {
            for (element in b.columns) {
                if (a.columns[i] == element) {
                    columnsA.add(a.columns[i])
                    columnsB.add(a.columns[i])
                    compCount++
                }
            }
        }
        for (i in a.columns.indices) {
            if (!columnsA.contains(a.columns[i])) {
                columnsA.add(a.columns[i])
            }
        }
        for (j in b.columns.indices) {
            if (!columnsB.contains(b.columns[j])) {
                columnsB.add(b.columns[j])
            }
        }
        columns = projection
        val mapping = IntArray(projection.size)
        for (i in projection.indices) {
            for (j in a.columns.indices) {
                if (projection[i] == a.columns[j]) {
                    mapping[i] = j
                }
            }
        }
        buf = IntArray(mapping.size)
        Parallel.runBlocking {
            bIdx = b.next()
            if (bIdx < 0) {
                flag = 1
            }
            close = {
                _close()
                a.close()
                b.close()
            }
            next = {
                var res = -1
                loop@ while (true) {
                    when (flag) {
                        0 -> {
                            break@loop
                        }
                        1 -> { // nothing to remove left
                            aIdx = a.next()
                            if (aIdx < 0) {
                                flag = 0
                                close()
                            } else {
                                res = 0
                                for (i in mapping.indices) {
                                    buf[i] = a.buf[mapping[i] + aIdx]
                                }
                            }
                            break@loop
                        }
                        2 -> {
                            aIdx = a.next()
                            if (aIdx >= 0) {
                                for (i in 0 until compCount) {
                                    if (a.buf[i] < b.buf[i]) {
                                        res = 0
                                        for (k in mapping.indices) {
                                            buf[k] = a.buf[mapping[k] + aIdx]
                                        }
                                        break@loop
                                    } else if (a.buf[i] > b.buf[i]) {
                                        bIdx = b.next()
                                        if (bIdx < 0) {
                                            flag = 1
                                            res = 0
                                            for (k in mapping.indices) {
                                                buf[k] = a.buf[mapping[k] + aIdx]
                                            }
                                            break@loop
                                        }
                                        continue@loop
                                    }
                                }
                            } else {
                                close()
                                flag = 0
                                break@loop
                            }
                        }
                    }
                }
                res
            }
        }
    }
}
