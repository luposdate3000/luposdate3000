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


class POPInsertData(val transactionID:Long,val data: List<List<String>>, val pstore: PersistentStore) : POPBase() {
    private val resultSetNew = ResultSet()

    private var first = true

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPInsertData.hasNext")
            return first
        } finally {
            Trace.stop("POPInsertData.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPInsertData.next")
            first = false
            for (t in data) {
                val store = pstore.getNamedGraph(t[3])
                store.addData(transactionID,t)
            }
            return resultSetNew.createResultRow()
        } finally {
            Trace.stop("POPInsertData.next")
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
        for (t in data) {
            res.addContent(XMLElement("RawTriple").addAttribute("s", t[0]).addAttribute("p", t[1]).addAttribute("o", t[2]).addAttribute("graph", t[3]))
        }
        return res
    }
}
