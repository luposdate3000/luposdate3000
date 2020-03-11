package lupos.s09physicalOperators.multiinput

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


class POPUnion(query: Query, childA: OPBase, childB: OPBase) : POPBase(query, EOperatorID.POPUnionID, "POPUnion", ResultSet(query.dictionary), arrayOf(childA, childB)) {

    override fun toSparql() = "{" + children[0].toSparql() + "} UNION {" + children[1].toSparql() + "}"

    override fun equals(other: Any?): Boolean {
        if (other !is POPUnion)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPUnion.evaluate" }, {
        val variablesOld = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf())
        var variablesA = children[0].getProvidedVariableNames()
        var variablesB = children[1].getProvidedVariableNames()
        for (name in variablesA)
            variablesOld[0].add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
        for (name in variablesB)
            variablesOld[1].add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
        val channels = children.map { it.evaluate() }
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (idx in channels.indices) {
                    val c = children[idx]
                    channels[idx].forEach { rowOld ->
                        resultFlowConsume({ this@POPUnion }, { c }, { rowOld })
                        val rsNew = resultSet.createResultRow()
                        for (p in variablesOld[idx])
                            resultSet.copy(rsNew, p.second, rowOld, p.first, children[idx].resultSet)
                        channel.send(resultFlowProduce({ this@POPUnion }, { rsNew }))
                    }
                }
                channel.close()
                for (c in channels)
                    c.close()
            } catch (e: Throwable) {
                channel.close()
                for (c in channels)
                    c.close()
            }
        }
        return ResultIterator(next = {
            channel.receive()
        }, close = {
            channel.close()
        })
    })

    override fun cloneOP() = POPUnion(query, children[0].cloneOP(), children[1].cloneOP())
}
