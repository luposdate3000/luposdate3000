package lupos.s09physicalOperators.singleinput

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
import lupos.s09physicalOperators.POPBase


class POPTemporaryStore(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPTemporaryStoreID, "POPTemporaryStore", ResultSet(query.dictionary), arrayOf(child)) {
    @JvmField
    val data = mutableListOf<ResultRow>()

    override fun equals(other: Any?): Boolean {
        if (other !is POPTemporaryStore)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPTemporaryStore(query, children[0].cloneOP())

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPTemporaryStore.evaluate" }, {
        val variables = mutableListOf<Pair<Variable, Variable>>()
        for (name in children[0].getProvidedVariableNames()) {
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        }
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPTemporaryStore }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (variable in variables)
                        rsNew[variable.first] = rsOld[variable.second]
                    data.add(rsNew)
                    channel.send(resultFlowProduce({ this@POPTemporaryStore }, { rsNew }))
                }
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

    suspend fun reset() = Trace.trace<Channel<ResultRow>>({ "POPTemporaryStore.reset" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        for (c in data)
            channel.send(resultFlowProduce({ this@POPTemporaryStore }, { c }))
        return channel
    })

}
