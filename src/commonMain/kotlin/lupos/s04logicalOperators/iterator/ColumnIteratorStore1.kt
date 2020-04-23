package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore1(val values: MyListValue, start: Int) : ColumnIterator() {
    var index = start + 1
    val end = start + values[start] + 1

    init {
        println("__A ${index - 1} c2")
        next = {
            var res: Value?
            try {
                if (index == end) {
                    res = null
                } else {
                    println("__A ${index} v2")
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
