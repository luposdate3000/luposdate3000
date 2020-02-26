package lupos.s04logicalOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPJoin : LOPBase {
    override val operatorID = EOperatorID.LOPJoinID
    override val classname = "LOPJoin"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    val optional: Boolean

    constructor(first: OPBase, second: OPBase, optional: Boolean) : super() {
        children[0] = first
        children[1] = second
        this.optional = optional
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPJoin)
            return false
        if (optional != other.optional)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
