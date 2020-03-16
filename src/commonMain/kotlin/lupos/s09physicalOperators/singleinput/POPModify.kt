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
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
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

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPModify.evaluate" }, {
        val child = children[0].evaluate()
        val res = ResultIterator()
        res.close = {
            res._close()
            child.close()
        }
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPModify.next" }, {
                println("POPModify.next")
                try {
                    child.forEach { rowsOld ->
                        resultFlowConsume({ this@POPModify }, { children[0] }, { rowsOld })
                        for (i in insert) {
                            try {
                                if (i.graphVar) {
                                    val variable = children[0].resultSet.createVariable(i.graph)
                                    val storenames = rowsOld.getColumn(variable)
                                    val data = Array(3) { (i.children[it] as AOPBase).calculate(children[0].resultSet, rowsOld) }
                                    for (i in rowsOld.pos until rowsOld.size) {
                                        val store = DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueObject(storenames.data[i]).valueToString()!!, true)
                                        store.addData(Array(3) { data[it].data[i] })
                                    }
                                } else {
                                    val store = DistributedTripleStore.getNamedGraph(query, i.graph, true)
                                    val data = Array(3) { (i.children[it] as AOPBase).calculate(children[0].resultSet, rowsOld) }
                                    for (i in rowsOld.pos until rowsOld.size)
                                        store.addData(Array(3) { data[it].data[i] })
                                }
                            } catch (e: Throwable) {
                            }
                        }
                        for (i in delete) {
                            try {
                                if (i.graphVar) {
                                    val variable = children[0].resultSet.createVariable(i.graph)
                                    val storenames = rowsOld.getColumn(variable)
                                    val data = Array(3) { (i.children[it] as AOPBase).calculate(children[0].resultSet, rowsOld) }
                                    for (i in rowsOld.pos until rowsOld.size) {
                                        val store = DistributedTripleStore.getNamedGraph(query, children[0].resultSet.getValueObject(storenames.data[i]).valueToString()!!, false)
                                        store.deleteData(Array(3) { data[it].data[i] })
                                    }
                                } else {
                                    val store = DistributedTripleStore.getNamedGraph(query, i.graph, false)
                                    val data = Array(3) { (i.children[it] as AOPBase).calculate(children[0].resultSet, rowsOld) }
                                    for (i in rowsOld.pos until rowsOld.size)
                                        store.deleteData(Array(3) { data[it].data[i] })
                                }
                            } catch (e: Throwable) {
                            }
                        }
                    }
                } finally {
                    res.close()
                }
                var outbuf = ResultChunk(resultSet)
                outbuf.append(resultSet.createResultRow())
                resultFlowProduce({ this@POPModify }, { outbuf })
            })
        }
        return res
    })
}
