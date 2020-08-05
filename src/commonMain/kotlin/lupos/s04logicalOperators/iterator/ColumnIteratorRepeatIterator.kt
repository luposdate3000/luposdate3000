package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIteratorNext

class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0

    //TODO use pages instead
    val data = MyListValue()

    init {
        SanityCheck.check { count > 0 }
        next = object : ColumnIteratorNext("ColumnIteratorRepeatIterator.next") {
            override fun invoke(): Value? {
                var res: Value?
                val tmp = child.next()
                if (tmp == null) {
                    child.close()
                    if (data.size == 0 || count == 1) {
                        next = object : ColumnIteratorNext("ColumnIteratorRepeatIterator.next") {
                            override fun invoke(): Value? {
                                return null
                            }
                        }
                    } else {
                        index = 2
                        next = object : ColumnIteratorNext("ColumnIteratorRepeatIterator.next") {
                            override fun invoke(): Value? {
                                var res2: Value?
                                if (index2 < data.size) {
                                    res2 = data[index2++]
                                } else {
                                    if (index < count) {
                                        index++
                                        index2 = 0
                                        res2 = data[index2++]
                                    } else {
                                        res2 = null
                                    }
                                }
                                return res2
                            }
                        }
                    }
                    res = next()
                } else {
                    data.add(tmp)
                    res = tmp
                }
                return res
            }
        }
        close = {
            child.close()
            _close()
        }
    }
}
