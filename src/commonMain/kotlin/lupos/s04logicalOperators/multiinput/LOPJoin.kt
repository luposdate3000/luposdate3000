package lupos.s04logicalOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPJoin(query:Query,first: OPBase, second: OPBase, @JvmField val optional: Boolean) : LOPBase(query,EOperatorID.LOPJoinID,"LOPJoin",arrayOf(first, second)) {

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPJoin)
            return false
        if (optional != other.optional)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPJoin(children[0].cloneOP(), children[1].cloneOP(), optional)
}
