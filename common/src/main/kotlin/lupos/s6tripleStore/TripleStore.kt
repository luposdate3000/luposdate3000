package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultSetIterator
import lupos.s5physicalOperators.POPBase

expect class TripleStore(){
    fun addData(iterator: ResultSetIterator)
    fun getIterator(): POPBase
    // TODO fun getIterator(tripelmuster): ResultSetIterator /*nur selectiv result sets generieren ggf gleichheut einiger variablen prüfen/garantieren*/
    // TODO fun getIterator(tripelmuster,sort): ResultSetIteratorSidewaysInformationPassing /*nur selectiv result sets generieren ggf gleichheut einiger variablen prüfen/garantieren*/
}

val persistentTripleStore=TripleStore()
