package lupos.s04logicalOperators.noinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class OPEmptyRow(query: Query) : LOPBase(query, EOperatorID.OPEmptyRowID, "OPEmptyRow", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean {
        if (other !is OPEmptyRow) {
            return false
        }
        return true
    }

    override fun cloneOP() = this
}
