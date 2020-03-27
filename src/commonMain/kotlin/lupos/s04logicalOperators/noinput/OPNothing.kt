package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class OPNothing(query: Query) : LOPBase(query, EOperatorID.OPNothingID, "OPNothing", arrayOf()) {
    override fun equals(other: Any?): Boolean {
        if (other !is OPNothing)
            return false
        return true
    }

    override fun cloneOP() = this
}
