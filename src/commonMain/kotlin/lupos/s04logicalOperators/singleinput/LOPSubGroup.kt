package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class LOPSubGroup(query: Query, child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPSubGroupID, "LOPSubGroup", arrayOf(child)) {

    override fun equals(other: Any?): Boolean {
        if (other !is LOPSubGroup)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPSubGroup(query, children[0].cloneOP())
}
