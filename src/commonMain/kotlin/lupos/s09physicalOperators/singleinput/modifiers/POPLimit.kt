package lupos.s09physicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

class POPLimit(query: Query, @JvmField val limit: Int, child: OPBase) : POPBase(query, EOperatorID.POPLimitID, "POPLimit", child.resultSet, arrayOf(child)) {
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        return "{SELECT * {" + sparql + "} LIMIT " + limit + "}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP() = POPLimit(query, limit, children[0].cloneOP())
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPLimit.evaluate" }, {
        val child = children[0].evaluate()
        val res = ResultIteratorImpl()
        res.count = limit
        if (limit > 0)
            res.next = {
                Trace.traceSuspend<ResultChunk>({ "POPLimit.next" }, {
                    val outbuffer = resultFlowProduce({ this@POPLimit }, { resultFlowConsume({ this@POPLimit }, { children[0] }, { child.next() }) })
                    val available = outbuffer.availableRead()
                    if (res.count <= available) {
                        outbuffer.skipSize(-res.count)
                        res.close()
                    }
                    res.count -= available
                    outbuffer
                })
            }
        res.close = {
            child.close()
            res._close()
        }
        return res
    })

    class ResultIteratorImpl() : ResultIterator() {
        var count = 0
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
}
