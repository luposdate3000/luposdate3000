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
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPOffset(query: Query, @JvmField val offset: Int, child: OPBase) : POPBase(query, EOperatorID.POPOffsetID, "POPOffset", child.resultSet, arrayOf(child)) {
    override fun equals(other: Any?): Boolean {
        if (other !is POPOffset)
            return false
        if (offset != other.offset)
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
            return sparql.substring(0, sparql.length - 1) + " OFFSET " + offset + "}"
        return "{SELECT * {" + sparql + "} OFFSET " + offset + "}"
    }

    override fun cloneOP() = POPOffset(query, offset, children[0].cloneOP())

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPOffset.evaluate" }, {
        val res = ResultIterator()
        res.next = {
            val childIterator = children[0].evaluate()
            try {
                for (count in 0 until offset)
                    childIterator.next()
                res.next = childIterator.next
                res.close = childIterator.close
            } catch (e: Throwable) {
                res.close()
            }
            res.next.invoke()
        }
        return res
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)
}
