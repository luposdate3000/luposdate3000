package lupos.s6tripleStore

import lupos.s4resultRepresentation.ResultSetIterator

expect class TripleStore() {
    fun addData(iterator: ResultSetIterator)
    fun getIterator(): ResultSetIterator
}