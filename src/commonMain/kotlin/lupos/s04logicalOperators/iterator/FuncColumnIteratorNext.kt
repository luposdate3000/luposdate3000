package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
abstract class FuncColumnIteratorNext(@JvmField val classname: String) {
    companion object {
        @JvmField
        val empty = object : FuncColumnIteratorNext("FuncColumnIteratorNextEmpty") {
            override fun invoke(): Value? {
                return null
            }
        }
    }

    abstract operator fun invoke(): Value?
}
