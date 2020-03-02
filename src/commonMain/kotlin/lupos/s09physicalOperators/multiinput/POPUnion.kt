package lupos.s09physicalOperators.multiinput

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


class POPUnion(override val dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPUnionID
    override val classname = "POPUnion"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(childA, childB)

override fun toSparql()="{"+children[0].toSparql()+"} UNION {"+children[1].toSparql()+"}"

    override fun equals(other: Any?): Boolean {
        if (other !is POPUnion)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPUnion.evaluate" }, {
        val variablesOld = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf())
        val variablesOldMissing = arrayOf(mutableListOf<Variable>(), mutableListOf())
        var variablesA = children[0].getProvidedVariableNames()
        var variablesB = children[1].getProvidedVariableNames()
        for (name in variablesA) {
            variablesOld[0].add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesB.contains(name))
                variablesOldMissing[1].add(resultSet.createVariable(name))
        }
        for (name in variablesB) {
            variablesOld[1].add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
            if (!variablesA.contains(name))
                variablesOldMissing[0].add(resultSet.createVariable(name))
        }
        val channels = children.map { it.evaluate() }
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (idx in channels.indices) {
                    val c = children[idx]
                    for (rsOld in channels[idx]) {
                        resultFlowConsume({ this@POPUnion }, { c }, { rsOld })
                        val rsNew = resultSet.createResultRow()
                        for (p in variablesOldMissing[idx])
                            resultSet.setUndefValue(rsNew, p)
                        for (p in variablesOld[idx])
                            rsNew[p.second] = rsOld[p.first]
                        channel.send(resultFlowProduce({ this@POPUnion }, { rsNew }))
                    }
                }
                channel.close()
                for (c in channels)
                    c.close()
            } catch (e: Throwable) {
                channel.close(e)
                for (c in channels)
                    c.close(e)
            }
        }
        return channel
    })

    override fun cloneOP() = POPUnion(dictionary, children[0].cloneOP(), children[1].cloneOP())
}
