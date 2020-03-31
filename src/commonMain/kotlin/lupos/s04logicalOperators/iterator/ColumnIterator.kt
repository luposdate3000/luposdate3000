package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorRow(val columns: Map<String, ColumnIterator>) {
    var count = 0
}

open class ColumnIterator() {
    var next: suspend () -> Value? = ::_next
    var close: () -> Unit = ::_close
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements
    fun _close() {
        next = ::_next
        close = ::_close
        onNoMoreElements = ::_onNoMoreElements
    }

    suspend fun _onNoMoreElements() {
        close()
    }

    suspend fun _next(): Value? = null
}

class ColumnIteratorAggregate() : ColumnIterator() {
    var value: ValueDefinition = ResultSetDictionary.undefValue2
    var count = 0
    var evaluate: suspend () -> Unit = ::_evaluate
    suspend fun _evaluate() {
    }
}

class ColumnIteratorRepeatValue(val count: Int, val value: Value) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res: Value?
            if (index == count) {
                res = null
            } else {
                index++
                res = value
            }
/*return*/res
        }
    }
}

class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0
    //TODO use pages instead
    val data = mutableListOf<Value>()

    init {
        require(count > 1)
        next = {
            var res: Value?
            val tmp = child.next()
            if (tmp == null) {
                index = 1
                next = {
                    var res: Value?
                    if (index2 < data.size) {
                        res = data[index2]
                    } else {
                        if (index < count) {
                            index++
                            index2 = 0
                            res = data[index2]
                        } else {
                            res = null
                        }
                    }
                    /*return*/res
                }
                res = next()
            } else {
                data.add(tmp)
                res = tmp
            }
/*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}

class ColumnIteratorMultiValue(val values: List<Value>) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res: Value?
            if (index == values.size) {
                res = null
            } else {
                res = values[index++]
            }
/*return*/res
        }
    }
}

class ColumnIteratorMultiIterator(val childs: List<ColumnIterator>) : ColumnIterator() {
    var index = 0

    init {
        next = {
            var res = childs[index].next()
            while (res == null && index < childs.size) {
                res = childs[++index].next()
            }
/*return*/            res
        }
        close = {
            for (c in childs) {
                c.close()
            }
            _close()
        }
    }
}

class ColumnIteratorChildIterator() : ColumnIterator() {
    val childs = mutableListOf(ColumnIterator())

    init {
        next = {
            require(childs.size > 0)
            var res = childs[0].next()
            if (res == null) {
                childs.removeAt(0)
                if (childs.size == 0)
                    onNoMoreElements()
                if (childs.size == 0)
                    close()
                res = next()
            }
            /*return*/ res
        }
        close = {
            for (child in childs) {
                child.close()
            }
            _close()
        }
    }
}

class ColumnIteratorQueue() : ColumnIterator() {
    var tmp: Value? = null
    val queue = mutableListOf<Value>()
    var onEmptyQueue: suspend () -> Unit = ::_onEmptyQueue
    suspend fun _onEmptyQueue() {
    }

    init {
        next = {
            if (queue.size == 0) {
                onEmptyQueue()
            }
            var res: Value? = null
            if (queue.size > 0) {
                res = queue.removeAt(0)
            }
            /*return*/  res
        }
        close = {
            _close()
        }
    }
}

class ColumnIteratorDistinct(val child: ColumnIterator) : ColumnIterator() {
    //TODO only for_ single column .... replace with sort-distinct
    var last: Value? = null

    init {
        next = {
            var res = child.next()
            while (last != null && last == res) {
                res = child.next()
            }
            last = res
/*return*/            res
        }
        close = {
            child.close()
            _close()
        }
    }
}

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
                                /*return*/    childA.next()
                            }
                            res = childA.next()
                        } else {
                            next = {
                                /*return*/              childB.next()
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
                /*return*/        res
            }
/*return*/            next()
        }
        close = {
            childA.close()
            childB.close()
            _close()
        }
    }
}
