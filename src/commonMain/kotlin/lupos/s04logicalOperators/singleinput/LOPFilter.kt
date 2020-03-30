package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPFilter(query: Query, filter: AOPBase, child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPFilterID, "LOPFilter", arrayOf(child, filter)) {
    override fun childrenToVerifyCount() = 1
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames().distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[1].getRequiredVariableNamesRecoursive()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPFilter)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
}
