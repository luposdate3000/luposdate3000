package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID


import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*



import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
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
        //column based
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        val var0A = children[0].getProvidedVariableNames().map { children[0].resultSet.createVariable(it) }.toTypedArray()
        val var0B = children[1].getProvidedVariableNames().map { children[1].resultSet.createVariable(it) }.toTypedArray()
        val var1A = children[0].getProvidedVariableNames().map { resultSet.createVariable(it) }.toTypedArray()
        val var1B = children[1].getProvidedVariableNames().map { resultSet.createVariable(it) }.toTypedArray()
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
                    val inbuf = resultFlowConsume({ this@POPUnion }, { children[0] }, { childA.next() })
                    outbuf.copy(var1A, inbuf, var0A, inbuf.availableRead())
                } catch (e: Throwable) {
                    childA.close()
                    res.next = {
                        Trace.trace<ResultChunk>({ "POPUnion.next" }, {
                            val outbuf = ResultChunk(resultSet)
                            val inbuf = resultFlowConsume({ this@POPUnion }, { children[1] }, { childB.next() })
                            outbuf.copy(var1B, inbuf, var0B, inbuf.availableRead())
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
