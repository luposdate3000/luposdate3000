package lupos.s09physicalOperators.noinput
import lupos.s03resultRepresentation.*
import kotlinx.coroutines.channels.Channel

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModifyData(override val dictionary: ResultSetDictionary, val transactionID: Long, val type: EModifyType, val data: List<List<Pair<String, Boolean>>>) : POPBase() {
    override val operatorID = EOperatorID.POPModifyDataID
    override val classname = "POPModifyData"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf()
    private var first = true

    override fun cloneOP() = POPModifyData(dictionary, transactionID, type, data)

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

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPModifyData.evaluate" }, {
val channel=Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (t in data) {
                    if (type == EModifyType.INSERT) {
                        val store = DistributedTripleStore.getNamedGraph(t[3].first, true)
                        var l = mutableListOf<AOPConstant>()
                        for (i in 0 until 3)
                            l.add(AOPVariable.calculate(t[i].first))
                        store.addData(transactionID, l)
                    } else {
                        val store = DistributedTripleStore.getNamedGraph(t[3].first, false)
                        var l = mutableListOf<AOPBase>()
                        for (i in 0 until 3) {
                            if (t[i].second)
                                l.add(AOPVariable.calculate(t[i].first))
                            else
                                l.add(AOPVariable(t[i].first))
                        }
                        store.deleteDataVar(transactionID, l)
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
