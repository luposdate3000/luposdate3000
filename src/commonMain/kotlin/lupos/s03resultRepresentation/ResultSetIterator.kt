package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable


interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}
