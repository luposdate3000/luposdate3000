package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPOffset(@JvmField val offset: Int, child: OPBase = OPNothing()) : LOPBase() {
    override val operatorID = EOperatorID.LOPOffsetID
    override val classname = "LOPOffset"
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement() = super.toXMLElement().addAttribute("offset", "" + offset)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPOffset)
            return false
        if (offset != other.offset)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPOffset(offset, children[0].cloneOP())
}
