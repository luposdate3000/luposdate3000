package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPFilter : LOPBase {
    override val operatorID = EOperatorID.LOPFilterID
    override val classname = "LOPFilter"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    override fun childrenToVerifyCount() = 1

    constructor(filter: AOPBase) : super() {
        children[1] = filter
    }

    constructor(filter: AOPBase, child: OPBase) : this(filter) {
        children[0] = child
    }

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
}
