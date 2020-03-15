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

    override fun cloneOP() = POPUnion(query, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} UNION {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPUnion && children[0] == other.children[0] && children[1] == other.children[1]

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPUnion.evaluate" }, {
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        var variablesA = children[0].getProvidedVariableNames().map { Pair(children[0].resultSet.createVariable(it), resultSet.createVariable(it)) }
        var variablesB = children[1].getProvidedVariableNames().map { Pair(children[1].resultSet.createVariable(it), resultSet.createVariable(it)) }
        val res = ResultIterator()
        res.close = {
            childA.close()
            childB.close()
            res._close()
        }
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPUnion.next" }, {
                var outbuf = ResultChunk(resultSet)
                try {
                    val rowsOld = childA.next()
                    for (rowOld in rowsOld) {
                        var row = resultSet.createResultRow()
                        for (v in variablesA)
                            resultSet.copy(row, v.second, rowOld, v.first, children[0].resultSet)
                        outbuf.append(row)
                    }
                } catch (e: Throwable) {
                    childA.close()
                    res.next = {
                        Trace.trace<ResultChunk>({ "POPUnion.next" }, {
                            val outbuf = ResultChunk(resultSet)
                            val rowsOld = childB.next()
                            for (rowOld in rowsOld) {
                                val row = resultSet.createResultRow()
                                for (v in variablesB)
                                    resultSet.copy(row, v.second, rowOld, v.first, children[1].resultSet)
                                outbuf.append(row)
                            }
                            resultFlowProduce({ this@POPUnion }, { outbuf })
                        })
                    }
                }
                resultFlowProduce({ this@POPUnion }, { outbuf })
            })
        }
        return res
    })
}
