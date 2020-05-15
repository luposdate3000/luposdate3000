package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value

object ColumnIteratorMultiValue {
    operator fun invoke(values: IntArray) = ColumnIteratorMultiValue_3(values)
    operator fun invoke(values: MyListValue) = ColumnIteratorMultiValue_1(values)
    operator fun invoke(iterator: Iterator<Value>) = ColumnIteratorMultiValue_2(iterator)
}

class ColumnIteratorMultiValue_1(val values: MyListValue) : ColumnIterator() {
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

class ColumnIteratorMultiValue_3(val values: IntArray) : ColumnIterator() {
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

class ColumnIteratorMultiValue_2(val iterator: Iterator<Value>) : ColumnIterator() {
    init {
        next = {
            var res: Value? = null
            if (iterator.hasNext()) {
                res = iterator.next()
            }
/*return*/res
        }
    }
}
