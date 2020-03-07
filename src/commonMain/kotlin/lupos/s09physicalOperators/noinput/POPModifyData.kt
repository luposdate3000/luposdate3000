package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModifyData(query: Query, @JvmField val type: EModifyType, @JvmField val data: List<LOPTriple>) : POPBase(query, EOperatorID.POPModifyDataID, "POPModifyData", ResultSet(query.dictionary), arrayOf()) {
    var first = true

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
            require(c.graphVar == false)
            if (c.graph == PersistentStoreLocal.defaultGraphName)
                res += c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "."
            res += "GRAPH <${c.graph}> {" + c.children[0].toSparql() + " " + c.children[1].toSparql() + " " + c.children[2].toSparql() + "}."
        }
        res += "}"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPModifyData)
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

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPModifyData.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (t in data) {
                    if (type == EModifyType.INSERT) {
                        val store = DistributedTripleStore.getNamedGraph(query, t.graph, true)
                        var l = mutableListOf<AOPConstant>()
                        for (i in 0 until 3)
                            l.add(t.children[i] as AOPConstant)
                        store.addData(l)
                    } else {
                        val store = DistributedTripleStore.getNamedGraph(query, t.graph, false)
                        var l = mutableListOf<AOPBase>()
                        for (i in 0 until 3)
                            l.add(t.children[i] as AOPBase)
                        store.deleteDataVar(l)
                    }
                }
                channel.send(resultFlowProduce({ this@POPModifyData }, { resultSet.createResultRow() }))
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPInsertData")
        res.addAttribute("uuid", "" + uuid)
        for (t in data)
            res.addContent(t.toXMLElement())
        return res
    }
}
