package lupos.s09physicalOperators.noinput
import lupos.s12p2p.DistributedTripleStore
import lupos.s04logicalOperators.noinput.ModifyDataType
import lupos.s04logicalOperators.OPBase
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase


class POPModifyData(override val dictionary: ResultSetDictionary, val transactionID: Long, val type: ModifyDataType, val data: List<List<Pair<String, Boolean>>>, val pstore: DistributedTripleStore) : POPBase() {
    override val children: Array<OPBase> = arrayOf()
    private val resultSetNew = ResultSet(dictionary)

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
                val store = pstore.getNamedGraph(t[3].first)
                if (type == ModifyDataType.INSERT)
                    store.addDataVar(transactionID, t)
                else
                    store.deleteDataVar(transactionID, t)
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
            res.addContent(XMLElement("RawTriple")
                    .addAttribute("sv", t[0].first)
                    .addAttribute("pv", t[1].first)
                    .addAttribute("ov", t[2].first)
                    .addAttribute("sconst", "" + t[0].second)
                    .addAttribute("pconst", "" + t[1].second)
                    .addAttribute("oconst", "" + t[2].second)
                    .addAttribute("graph", t[3].first))
        }
        return res
    }
}
