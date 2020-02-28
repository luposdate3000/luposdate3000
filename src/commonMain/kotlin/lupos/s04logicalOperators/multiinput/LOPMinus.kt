package lupos.s04logicalOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPMinus(first: OPBase, second: OPBase) : LOPBase() {
    override val operatorID = EOperatorID.LOPMinusID
    override val classname = "LOPMinus"
    override val children: Array<OPBase> = arrayOf(first, second)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPMinus)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
override fun cloneOP()=LOPMinus(children[0].cloneOP(),children[1].cloneOP())
}
