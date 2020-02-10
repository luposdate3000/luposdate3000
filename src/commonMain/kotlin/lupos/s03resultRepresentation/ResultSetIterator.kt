package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet


interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}
