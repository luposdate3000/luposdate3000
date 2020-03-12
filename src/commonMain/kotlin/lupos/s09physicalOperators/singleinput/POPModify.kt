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
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s05tripleStore.*
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class POPModify(query: Query, @JvmField val insert: List<LOPTriple>, @JvmField val delete: List<LOPTriple>, child: OPBase) : POPBase(query, EOperatorID.POPModifyID, "POPModify", ResultSet(query.dictionary), arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPModify && insert.equals(other.insert) && delete.equals(other.delete) && children[0] == other.children[0]
    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>()
    override fun cloneOP() = POPModify(query, insert, delete, children[0].cloneOP())
    fun evaluateRow(node: OPBase, row: ResultRow): ValueDefinition = (node as AOPBase).calculate(children[0].resultSet, row)

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPModify.evaluate" }, {
        val child = children[0].evaluate()
        val res = ResultIterator()
        res.close = {
            child.close()
            res._close()
        }
        res.next = {
            Trace.traceSuspend<ResultRow>({ "POPModify.next" }, {
                try {
                    child.forEach { rowOld ->
                        resultFlowConsume({ this@POPModify }, { children[0] }, { rowOld })
                        for (i in insert) {
                            try {
                                val store = if (i.graphVar)
                                    DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueObject(rowOld, i.graph)!!.valueToString()!!, true)
                                else
                                    DistributedTripleStore.getNamedGraph(query, i.graph, true)
                                store.addData(Array(3) { evaluateRow(i.children[it], rowOld) })
                            } catch (e: Throwable) {
//ignore unbound variables
                            }
                        }
                        for (i in delete) {
                            try {
                                val store = if (i.graphVar)
                                    DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueObject(rowOld, i.graph)!!.valueToString()!!, true)
                                else
                                    DistributedTripleStore.getNamedGraph(query, i.graph, false)
                                store.deleteData(Array(3) { evaluateRow(i.children[it], rowOld) })
                            } catch (e: Throwable) {
//ignore unbound variables
                            }
                        }
                    }
                } finally {
                    res.close()
                }
                resultFlowProduce({ this@POPModify }, { resultSet.createResultRow() })
            })
        }
        return res
    })
}
