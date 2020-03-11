package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPLimit(query: Query, @JvmField val limit: Int, child: OPBase) : POPBase(query, EOperatorID.POPLimitID, "POPLimit", ResultSet(query.dictionary), arrayOf(child)) {

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPLimit)
            return false
        if (limit != other.limit)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPLimit(query, limit, children[0].cloneOP())

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPLimit.evaluate" }, {
        val variables = mutableListOf<Pair<Variable, Variable>>()
        for (v in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(v), children[0].resultSet.createVariable(v)))
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                var count = 0
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPLimit }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    if (count >= limit)
                        break
                    for (v in variables)
                        resultSet.copy(rsNew, v.first, rsOld, v.second, children[0].resultSet)
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
return ResultIterator(next={
try{
channel.next()
}catch(e:Throwable){
null
}
},close={
channel.close()
})
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
