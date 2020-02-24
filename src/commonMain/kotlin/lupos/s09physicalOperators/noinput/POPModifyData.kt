package lupos.s09physicalOperators.noinput
import lupos.s04arithmetikOperators.*

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModifyData(override val dictionary: ResultSetDictionary, val transactionID: Long, val type: EModifyType, val data: List<List<Pair<String, Boolean>>>) : POPBase() {
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf()
    private var first = true

    override fun equals(other: Any?): Boolean {
        if (other !is POPModifyData)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (type != other.type)
            return false
        if (data != other.data)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPModifyData.evaluate" }, {
        CoroutinesHelper.run {
            try {
                for (t in data) {
                    if (type == EModifyType.INSERT) {
                        val store = DistributedTripleStore.getNamedGraph(t[3].first, true)
                        store.addDataVar(transactionID, t)
                    } else {
                        val store = DistributedTripleStore.getNamedGraph(t[3].first, false)
                        store.deleteDataVar(transactionID, t)
                    }
                }
                channel.send(resultSet.createResultRow())
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

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
