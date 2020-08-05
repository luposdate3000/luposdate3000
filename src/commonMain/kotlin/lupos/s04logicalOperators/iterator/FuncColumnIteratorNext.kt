package lupos.s04logicalOperators.iterator

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value

abstract class FuncColumnIteratorNext(@JvmField val classname: String) {
    abstract operator fun invoke(): Value?
}
