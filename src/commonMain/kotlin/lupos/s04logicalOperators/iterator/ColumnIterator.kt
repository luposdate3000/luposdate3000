package lupos.s04logicalOperators.iterator

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
            if (index == count) {
                null
            } else {
                index++
                value
            }
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
            val tmp = child.next()
            if (tmp == null) {
                index = 1
                next = {
                    if (index2 < data.size) {
                        data[index2]
                    } else {
                        if (index < count) {
                            index++
                            index2 = 0
                            data[index2]
                        } else {
                            null
                        }
                    }
                }
                next()
            } else {
                data.add(tmp)
                tmp
            }
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
            if (index == values.size) {
                null
            } else {
                values[index++]
            }
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
            res
        }
        close = {
            for (c in childs) {
                c.close()
            }
            _close()
        }
    }
}

class ColumnIteratorChildIterator(var child: ColumnIterator) : ColumnIterator() {
    init {
        next = {
            var res = child.next()
            if (res == null) {
                onNoMoreElements()
                res = child.next()
            }
            res
        }
        close = {
            child.close()
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
            res
        }
        close = {
            _close()
        }
    }
}

class ColumnIteratorDistinct(val child: ColumnIterator) : ColumnIterator() {
    //TODO only for single column .... replace with sort-distinct
    var last: Value? = null

    init {
        next = {
            var res = child.next()
            while (last != null && last == res) {
                res = child.next()
            }
            last = res
            res
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
                    if (higherPriority != null)
                        higherPriority.chooseU(res)
                    choose(res)
                }
            } else {
                if (higherPriority != null)
                    higherPriority.chooseU(res)
                if (lowerPriority != null)
                    lowerPriority!!.chooseD(res)
                choose(res)
            }
        }
        return res
    }

    suspend fun chooseU(cmp: Int) {
        choose(cmp)
        if (higherPriority != null)
            higherPriority.chooseU(cmp)
    }

    suspend fun chooseD(cmp: Int) {
        choose(cmp)
        if (lowerPriority != null)
            lowerPriority!!.chooseD(cmp)
    }

    suspend fun choose(cmp1: Int) {
        var cmp = cmp1
        if (cmp == 0)
            cmp = 1
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
                                childA.next()
                            }
                            res = childA.next()
                        } else {
                            next = {
                                childB.next()
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
                res
            }
            next()
        }
        close = {
            childA.close()
            childB.close()
            _close()
        }
    }
}
