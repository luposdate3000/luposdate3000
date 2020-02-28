package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPReduced(child:OPBase=OPNothing()) : LOPBase() {
    override val operatorID = EOperatorID.LOPReducedID
    override val classname = "LOPReduced"
    override val children: Array<OPBase> = arrayOf(child)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPReduced)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
override fun cloneOP()=LOPReduced(children[0].cloneOP())
}
