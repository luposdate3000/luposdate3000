package lupos.s04logicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID

import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class LOPLimit(query: Query, @JvmField val limit: Int, child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPLimitID, "LOPLimit", arrayOf(child)) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("limit", "" + limit)
    override fun equals(other: Any?): Boolean {
        if (other !is LOPLimit)
            return false
        if (limit != other.limit)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPLimit(query, limit, children[0].cloneOP())
}
