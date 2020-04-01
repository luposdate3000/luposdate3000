package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0
    //TODO use pages instead
    val data = mutableListOf<Value>()

    init {
        require(count > 0)
        next = {
            var res: Value?
            val tmp = child.next()
            if (tmp == null) {
                if (data.size == 0) {
                    next = {
                        /*return*/null
                    }
                } else {
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
