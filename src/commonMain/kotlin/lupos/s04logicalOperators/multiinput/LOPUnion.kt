package lupos.s04logicalOperators.multiinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPUnion(query:Query,first: OPBase, second: OPBase) : LOPBase(query,EOperatorID.LOPUnionID,"LOPUnion", arrayOf(first, second)) {

    override fun equals(other: Any?): Boolean {
        if (other !is LOPUnion)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPUnion(query,children[0].cloneOP(), children[1].cloneOP())
}
