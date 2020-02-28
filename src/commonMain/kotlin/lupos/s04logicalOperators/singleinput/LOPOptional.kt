package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPOptional(child: OPBase = OPNothing()) : LOPBase() {
    override val operatorID = EOperatorID.LOPOptionalID
    override val classname = "LOPOptional"
    override val children: Array<OPBase> = arrayOf(child)

    override fun equals(other: Any?): Boolean {
        if (other !is LOPOptional)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPOptional(children[0].cloneOP())
}
