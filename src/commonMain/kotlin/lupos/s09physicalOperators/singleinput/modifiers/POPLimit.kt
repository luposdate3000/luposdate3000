package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s03resultRepresentation.*
import kotlinx.coroutines.channels.Channel

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPLimit(override val dictionary: ResultSetDictionary, val limit: Int, child: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPLimitID
    override val classname = "POPLimit"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(child)

    override fun equals(other: Any?): Boolean {
        if (other !is POPLimit)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (limit != other.limit)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPLimit(dictionary, limit, children[0].cloneOP())

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPLimit.evaluate" }, {
        val variables = mutableListOf<Pair<Variable, Variable>>()
        for (v in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(v), children[0].resultSet.createVariable(v)))
        val children0Channel=children[0].evaluate()
val channel=Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                var count = 0
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPLimit }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    if (count >= limit)
                        break
                    for (v in variables)
                        rsNew[v.first] = rsOld[v.second]
                    count++
                    channel.send(resultFlowProduce({ this@POPLimit }, { rsNew }))
                }
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children0Channel.close(e)
            }
        }
return channel
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
