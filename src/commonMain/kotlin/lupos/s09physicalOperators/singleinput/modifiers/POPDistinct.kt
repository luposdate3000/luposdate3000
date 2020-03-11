package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPDistinct(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPDistinctID, "POPDistinct", ResultSet(query.dictionary), arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPDistinct)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return "{SELECT DISTINCT " + sparql.substring("{SELECT ".length, sparql.length)
        return "{SELECT DISTINCT * {" + sparql + "}}"
    }

    override fun cloneOP() = POPDistinct(query, children[0].cloneOP())
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPDistinct.evaluate" }, {
        var data: MutableList<ResultRow>? = null
        val variables = mutableListOf<Pair<Variable, Variable>>()
        for (name in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, ResultRow>()
                children0Channel.forEach{oldRow->
                    resultFlowConsume({ this@POPDistinct }, { children[0] }, { oldRow })
                    val rsNew = resultSet.createResultRow()
                    var key = ""
                    for (variable in variables) {
                        resultSet.copy(rsNew, variable.first, oldRow, variable.second, children[0].resultSet)
                        key += "-" + children[0].resultSet.getValue(oldRow, variable.second)
                    }
                    tmpMutableMap[key] = rsNew
                }
                for (k in tmpMutableMap.keys)
                    channel.send(resultFlowProduce({ this@POPDistinct }, { tmpMutableMap[k]!! }))
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close()
                children0Channel.close()
            }
        }
        return ResultIterator(next = {
                channel.receive()
        }, close = {
            channel.close()
        })
    })

}
