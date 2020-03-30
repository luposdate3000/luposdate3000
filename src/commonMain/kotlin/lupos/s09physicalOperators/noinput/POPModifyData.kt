package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPModifyData(query: Query, @JvmField val type: EModifyType, @JvmField val data: List<LOPTriple>) : POPBase(query, EOperatorID.POPModifyDataID, "POPModifyData", ResultSet(query.dictionary), arrayOf()) {
    override fun equals(other: Any?): Boolean = other is POPModifyData && type == other.type && data == other.data
    override fun cloneOP() = POPModifyData(query, type, data)
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = ""
        when (type) {
            EModifyType.INSERT -> res += "INSERT"
            EModifyType.DELETE -> res += "DELETE"
        }
        res += " DATA {"
        for (c in data) {
            SanityCheck.checkFalse({ c.graphVar })
            if (c.graph == PersistentStoreLocal.defaultGraphName)
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPModifyData.evaluate" }, {
        //row based
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPModifyData.next" }, {
                try {
                    for (t in data)
                        if (type == EModifyType.INSERT)
                            DistributedTripleStore.getNamedGraph(query, t.graph, true).addData(Array(3) { (t.children[it] as AOPConstant).value })
                        else
                            DistributedTripleStore.getNamedGraph(query, t.graph, false).deleteData(Array(3) { (t.children[it] as AOPConstant).value })
                } finally {
                    res.close()
                }
                val outbuffer = ResultChunk(resultSet)
                outbuffer.append(resultSet.createResultRow())
                resultFlowProduce({ this@POPModifyData }, { outbuffer })
            })
        }
        return res
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPModifyData")
        res.addAttribute("uuid", "" + uuid)
        for (t in data)
            res.addContent(t.toXMLElement())
        return res
    }
}
