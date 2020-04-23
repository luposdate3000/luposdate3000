package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore1(val values: MyListValue, start: Int) : ColumnIterator() {
    var index = start + 1
    val end = start + values[start]

    init {
        next = {
            var res: Value?
            try {
                if (index == end) {
                    res = null
                } else {
                    res = values[index++]
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                throw e
            }
/*return*/res
        }
    }
}
