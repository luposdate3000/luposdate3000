package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultSetIterator
import lupos.s5physicalOperators.POPBase

enum class IndexPattern {
    S, P, O, SP, SO, PO, SPO, SOP, PSO, POS, OSP, OPS
}

abstract class POPTripleStoreIteratorBase() : POPBase() {
    fun getNameForS(): String = "#s" + uuid
    fun getNameForP(): String = "#p" + uuid
    fun getNameForO(): String = "#o" + uuid
}

