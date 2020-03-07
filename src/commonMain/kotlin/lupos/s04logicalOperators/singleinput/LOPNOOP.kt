package lupos.s04logicalOperators.singleinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPNOOP(query:Query,child: OPBase = OPNothing()) : LOPBase(query,EOperatorID.LOPNOOPID,"LOPNOOP",arrayOf(child)) {

    override fun equals(other: Any?): Boolean {
        if (other !is LOPNOOP)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPNOOP(children[0].cloneOP())
}

