package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPDistinct(child: OPBase = OPNothing()) : LOPBase() {
    override val operatorID = EOperatorID.LOPDistinctID
    override val classname = "LOPDistinct"
    override val children: Array<OPBase> = arrayOf(child)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPDistinct)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPDistinct(children[0].cloneOP())
}
