package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value



object ColumnIteratorMultiValue {
    operator fun invoke(values: MyListValue) = ColumnIteratorMultiValue_1(values)
    operator fun invoke(iterator: Iterator<Value>) = ColumnIteratorMultiValue_2(iterator)
}

class ColumnIteratorMultiValue_1(val values: MyListValue) : ColumnIterator() {
    var index = 0
    val end = values.size

    init {
        next = {
            var res: Value?
            if (index == end) {
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
