package lupos.s5physicalOperators

import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

class POPEmptyRow() : POPBase() {
    private val resultSetNew = ResultSet()

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        return first
    }

    override fun next(): ResultRow {
        first = false
        return resultSetNew.createResultRow()
    }

    override fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n"
}
