package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class OPNothing(query: Query) : LOPBase(query, EOperatorID.OPNothingID, "OPNothing", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean {
        if (other !is OPNothing) {
            return false
        }
        return true
    }

    override fun cloneOP() = this
}
