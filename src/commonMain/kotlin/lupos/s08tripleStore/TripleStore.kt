package lupos.s08tripleStore

import lupos.s07physicalOperators.POPBase
import lupos.s06resultRepresentation.ResultSetIterator


enum class IndexPattern {
    S, P, O, SP, SO, PO, SPO, SOP, PSO, POS, OSP, OPS
}

abstract class POPTripleStoreIteratorBase() : POPBase() {
    fun getNameForS(): String = "#s" + uuid
    fun getNameForP(): String = "#p" + uuid
    fun getNameForO(): String = "#o" + uuid
}

