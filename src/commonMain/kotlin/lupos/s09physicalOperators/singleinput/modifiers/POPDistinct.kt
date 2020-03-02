package lupos.s09physicalOperators.singleinput.modifiers

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPDistinct(override val dictionary: ResultSetDictionary, child: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPDistinctID
    override val classname = "POPDistinct"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(child)
    override fun equals(other: Any?): Boolean {
        if (other !is POPDistinct)
            return false
        if (dictionary !== other.dictionary)
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

    override fun cloneOP() = POPDistinct(dictionary, children[0].cloneOP())
    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPDistinct.evaluate" }, {
        var data: MutableList<ResultRow>? = null
        val variables = mutableListOf<Pair<Variable, Variable>>()
        for (name in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, ResultRow>()
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPDistinct }, { children[0] }, { rsOld })
                    val rsNew = resultSet.createResultRow()
                    var key = ""
                    for (variable in variables) {
                        rsNew[variable.first] = rsOld[variable.second]
                        key += "-" + rsOld[variable.second]
                    }
                    tmpMutableMap[key] = rsNew
                }
                for (k in tmpMutableMap.keys)
                    channel.send(resultFlowProduce({ this@POPDistinct }, { tmpMutableMap[k]!! }))
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children0Channel.close(e)
            }
        }
        return channel
    })

}
