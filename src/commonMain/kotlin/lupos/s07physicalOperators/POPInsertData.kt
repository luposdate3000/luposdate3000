package lupos.s07physicalOperators

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.*
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s08tripleStore.*


class POPInsertData(val data: List<LOPTriple>) : POPBase() {
    private val resultSetNew = ResultSet()

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPEmptyRow.hasNext")
            return first
        } finally {
            Trace.stop("POPEmptyRow.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPEmptyRow.next")
            first = false
            for (t in data) {
                val store = globalStore.getNamedGraph(t.graph)
                store.addData(t)
            }
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
        val res = XMLElement("POPInsertData")
        return res
    }
}
