package lupos.s04logicalOperators.singleinput.modifiers
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPReduced(query:Query,child: OPBase = OPNothing(query)) : LOPBase(query,EOperatorID.LOPReducedID,"LOPReduced",arrayOf(child)) {

    override fun equals(other: Any?): Boolean {
        if (other !is LOPReduced)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPReduced(query,children[0].cloneOP())
}
