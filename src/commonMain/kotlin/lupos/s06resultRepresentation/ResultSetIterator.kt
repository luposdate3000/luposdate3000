package lupos.s4resultRepresentation
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet

interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}
