package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPMakeBooleanResult() : LOPBase() {
    override val operatorID = EOperatorID.LOPMakeBooleanResultID
    override val classname = "LOPMakeBooleanResult"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(child: OPBase) : this() {
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf("?boolean")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPMakeBooleanResult)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPMakeBooleanResult(children[0].cloneOP())
}
