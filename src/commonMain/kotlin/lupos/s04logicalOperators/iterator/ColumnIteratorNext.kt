package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
abstract class ColumnIteratorNext(@JvmField val classname: String) {
    companion object {
        @JvmField
        val empty = object : ColumnIteratorNext("ColumnIteratorNextEmpty") {
            override fun invoke(): Value? {
                return null
            }
        }
    }

    abstract operator fun invoke(): Value?
}
