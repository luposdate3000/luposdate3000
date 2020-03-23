package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

class POPDistinct(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPDistinctID, "POPDistinct", child.resultSet, arrayOf(child)) {
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
        val child = children[0].evaluate()
        var data: ResultChunk? = null
        var dataLast: ResultChunk? = null
        CoroutinesHelper.runBlock {
            child.forEach { chunk ->
                val next = resultFlowConsume({ this@POPDistinct }, { children[0] }, { chunk })
                SanityCheck.checkEQ({next.prev}, {next})
                SanityCheck.checkEQ({next.next}, {next})
                if (next.availableRead() > 0) {
                    if (dataLast == null) {
                        data = next
                        dataLast = next.prev
                    } else
                        dataLast = ResultChunk.append(dataLast!!, next)
                }
            }
        }
        if (data == null)
            return ResultIterator()
        val fastcomparator = ValueComparatorFast()
        data = ResultChunk.sort(
                Array(data!!.columns) { fastcomparator },
                Array(data!!.columns) { it.toLong() },
                data!!)
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPDistinct.next" }, {
                var result = data!!
                data = ResultChunk.removeFirst(data!!)
                if (data == null)
                    res.close()
                result.makeDistinct()
                resultFlowProduce({ this@POPDistinct }, { result })
            })
        }
        return res
    })
}
