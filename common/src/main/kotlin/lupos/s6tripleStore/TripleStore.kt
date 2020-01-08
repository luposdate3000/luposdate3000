package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultSetIterator
import lupos.s5physicalOperators.POPBase

enum class IndexPattern {
    S, P, O, SP, SO, PO, SPO, SOP, PSO, POS, OSP, OPS
}

expect class TripleStore() {
    fun addData(iterator: ResultSetIterator)
    fun getIterator(): POPBase
    fun getIterator(index: IndexPattern): POPBase //filter using exact matching of 1 to 3 Variables
    // TODO fun getIterator(tripelmuster): ResultSetIterator /*nur selectiv result sets generieren ggf gleichheut einiger variablen prüfen/garantieren*/
    // TODO fun getIterator(tripelmuster,sort): ResultSetIteratorSidewaysInformationPassing /*nur selectiv result sets generieren ggf gleichheut einiger variablen prüfen/garantieren*/
}

val persistentTripleStore = TripleStore()
