package lupos.s04logicalOperators.multiinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPUnion : LOPBase {
    override val operatorID=EOperatorID.LOPUnionID
    override val classname = "LOPUnion"

    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())

    constructor(first: OPBase, second: OPBase) : super() {
        children[0] = first
        children[1] = second
    }


    override fun equals(other: Any?): Boolean {
        if (other !is LOPUnion)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
