package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModify(query: Query, @JvmField val insert: List<LOPTriple>, @JvmField val delete: List<LOPTriple>, child: OPBase) : POPBase(query, EOperatorID.POPModifyID, "POPModify", ResultSet(query.dictionary), arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPModify)
            return false
        if (!insert.equals(other.insert))
            return false
        if (!delete.equals(other.delete))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>()

    override fun cloneOP() = POPModify(query, insert, delete, children[0].cloneOP())

    fun evaluateRow(node: OPBase, row: ResultRow): AOPConstant {
        return (node as AOPBase).calculate(children[0].resultSet, row)
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPModify.evaluate" }, {
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (row in children0Channel) {
                    resultFlowConsume({ this@POPModify }, { children[0] }, { row })
                    for (i in insert) {
                        try {
                            val store = if (i.graph == PersistentStoreLocal.defaultGraphName)
                                DistributedTripleStore.getDefaultGraph(query)
                            else {
                                if (i.graphVar)
                                    DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueString(row, i.graph)!!, true)
                                else
                                    DistributedTripleStore.getNamedGraph(query, i.graph, true)
                            }
                            store.addData(Array(3) { evaluateRow(i.children[it], row) })
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    for (i in delete) {
                        try {
                            val store = if (i.graph == PersistentStoreLocal.defaultGraphName)
                                DistributedTripleStore.getDefaultGraph(query)
                            else {
                                if (i.graphVar)
                                    DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueString(row, i.graph)!!, true)
                                else
                                    DistributedTripleStore.getNamedGraph(query, i.graph, false)
                            }
                            store.deleteData(Array(3) { evaluateRow(i.children[it], row) })
                        } catch (e: Throwable) {
//ignore unbound variables
                        }
                    }
                    channel.send(resultFlowProduce({ this@POPModify }, { resultSet.createResultRow() }))
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


}
