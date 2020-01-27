package lupos.s4resultRepresentation
import lupos.s06resultRepresentation.ResultSet

interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}
