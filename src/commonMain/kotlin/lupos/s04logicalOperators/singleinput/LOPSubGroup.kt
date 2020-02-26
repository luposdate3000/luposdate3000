package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.noinput.OPNothing


class LOPSubGroup() : LOPBase() {
    override val operatorID = EOperatorID.LOPSubGroupID
    override val classname = "LOPSubGroup"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(child: OPBase) : this() {
        this.children[0] = child
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPSubGroup)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }
}
