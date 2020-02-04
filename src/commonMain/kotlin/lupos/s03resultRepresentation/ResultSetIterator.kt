package lupos.s03resultRepresentation
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.Variable



interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}
