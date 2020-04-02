package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorMergeSort(val childA: ColumnIterator, val childB: ColumnIterator, val comparator: Comparator<Value>, val higherPriority: ColumnIteratorMergeSort?, var lowerPriority: ColumnIteratorMergeSort?) : ColumnIterator() {
    //column based sort ... need to propagate sort decision to all other columns to stay synchronized
    var cacheA: Value? = null
    var cacheB: Value? = null
    val queue = mutableListOf<Value>()
    var fastcmp = 0
    suspend fun calculate(): Int {
        var res = 0
        if (higherPriority != null) {
            res = higherPriority.calculate()
        }
        if (res == 0) {
            res = comparator.compare(cacheA, cacheB)
            if (res == 0) {
                if (lowerPriority != null) {
                    res = lowerPriority!!.calculate()
                } else {
                    if (higherPriority != null) {
                        higherPriority.chooseU(res)
                    }
                    choose(res)
                }
            } else {
                if (higherPriority != null) {
                    higherPriority.chooseU(res)
                }
                if (lowerPriority != null) {
                    lowerPriority!!.chooseD(res)
                }
                choose(res)
            }
        }
        return res
    }

    suspend fun chooseU(cmp: Int) {
        choose(cmp)
        if (higherPriority != null) {
            higherPriority.chooseU(cmp)
        }
    }

    suspend fun chooseD(cmp: Int) {
        choose(cmp)
        if (lowerPriority != null) {
            lowerPriority!!.chooseD(cmp)
        }
    }

    suspend fun choose(cmp1: Int) {
        var cmp = cmp1
        if (cmp == 0) {
            cmp = 1
        }
        if (cmp == -1) {
            queue.add(cacheA!!)
            cacheA = childA.next()
            if (cacheA == null) {
                queue.add(cacheB!!)
                fastcmp = 1
            }
        } else {
            require(cmp == 1)
            queue.add(cacheB!!)
            cacheB = childB.next()
            if (cacheB == null) {
                queue.add(cacheA!!)
                fastcmp = -1
            }
        }
    }

    init {
        next = {
            cacheA = childA.next()
            cacheB = childB.next()
            require(cacheA != null)
            require(cacheB != null)
            next = {
                var res: Value? = null
                if (queue.size > 0) {
                    res = queue.removeAt(0)
                }
                if (res == null) {
                    if (fastcmp != 0) {
                        if (fastcmp == -1) {
                            next = {
                                /*return*/childA.next()
                            }
                            res = childA.next()
                        } else {
                            next = {
                                /*return*/childB.next()
                            }
                            res = childB.next()
                        }
                    } else {
                        calculate()
                        if (queue.size > 0) {
                            res = queue.removeAt(0)
                        }
                    }
                }
                /*return*/res
            }
/*return*/next()
        }
        close = {
            childA.close()
            childB.close()
            _close()
        }
    }
}
