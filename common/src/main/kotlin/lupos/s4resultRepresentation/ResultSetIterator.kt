package lupos.s4resultRepresentation

interface ResultSetIterator : Iterator<ResultRow> {
    fun getResultSet(): ResultSet
}