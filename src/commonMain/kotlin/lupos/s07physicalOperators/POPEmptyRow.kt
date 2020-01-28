package lupos.s07physicalOperators

import lupos.s00misc.*
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


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
        try {
            Trace.start("POPEmptyRow.next")
            first = false
            return resultSetNew.createResultRow()
        } finally {
            Trace.stop("POPEmptyRow.next")
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPEmptyRow")
        return res
    }
}
