package lupos.s04logicalOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class OPNothing : LOPBase() {
    override val operatorID = EOperatorID.OPNothingID
    override val classname = "OPNothing"
    override val children: Array<OPBase> = arrayOf()

    override fun equals(other: Any?): Boolean {
        if (other !is OPNothing)
            return false
        return true
    }

    override fun cloneOP() = this
}
