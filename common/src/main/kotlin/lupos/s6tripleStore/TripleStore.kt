package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultSetIterator

expect class TripleStore() {
    fun addData(iterator: ResultSetIterator)
    fun getIterator(): ResultSetIterator
    //TODO fun getIterator(filter:FilterImpl): ResultSetIterator /*nur selectiv result sets generieren ggf gleichheut einiger variablen prüfen/garantieren*/
}