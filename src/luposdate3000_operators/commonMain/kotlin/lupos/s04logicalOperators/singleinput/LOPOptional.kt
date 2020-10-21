package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPOptional(query: Query, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPOptionalID, "LOPOptional", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?) = other is LOPOptional && children[0] == other.children[0]
    override fun cloneOP() :IOPBase= LOPOptional(query, children[0].cloneOP())
    suspend override fun calculateHistogram() = children[0].getHistogram()
}
