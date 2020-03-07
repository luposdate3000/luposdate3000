package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPLimit(query:Query,@JvmField val limit: Int, child: OPBase = OPNothing()) : LOPBase(query,EOperatorID.LOPLimitID,"LOPLimit",arrayOf(child)) {

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

    override fun cloneOP() = LOPLimit(limit, children[0].cloneOP())
}
